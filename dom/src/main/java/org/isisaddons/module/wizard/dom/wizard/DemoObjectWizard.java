package org.isisaddons.module.wizard.dom.wizard;

import lombok.Getter;
import lombok.Setter;
import org.apache.isis.applib.ApplicationException;
import org.apache.isis.applib.annotation.*;
import org.apache.isis.applib.services.i18n.TranslatableString;
import org.isisaddons.module.wizard.applib.BreadCrumb;
import org.isisaddons.module.wizard.applib.BreadcrumbHolder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by E. de Hair <e.dehair@pocos.nl> on 10/7/16.
 */
@ViewModel
public class DemoObjectWizard implements BreadcrumbHolder, org.apache.isis.applib.ViewModel.Cloneable {

    private static final TranslatableString NEXT_DISABLED_REQUIRED_FIELDS_MSG = TranslatableString.tr("Some required fields are not set.");

    public DemoObjectWizard() {
        setState(WizardDemoObjectState.GENERAL);
    }

    public String title() {
        return "Demo object wizard";
    }

    @Getter
    @Setter
    private String someText;
    public boolean hideSomeText(){
        return getState().hideGeneral();
    }

    @Getter
    @Setter
    private String shipment;
    public boolean hideShipment(){
        return getState().hideShipmentAndConditions();
    }

    @Getter
    @Setter
    private String contact;
    public boolean hideContact(){
        return getState().hideContact();
    }

    @Property(editing = Editing.DISABLED) // otherwise an edit button will be displayed
    public String getSomeSummaryText(){
        return "This is just for display purposes.";
    }
    public boolean hideSomeSummaryText(){
        return getState().hideSummary();
    }

    // <editor-fold desc="Buttons">
    @Action(semantics = SemanticsOf.IDEMPOTENT_ARE_YOU_SURE)
    @ActionLayout(cssClassFa = "ban", cssClass = "btn-warning")
    public Object cancel(){
        return null;
    }

    @Property(hidden = Where.EVERYWHERE)
    @Getter @Setter
    private WizardDemoObjectState state;

    @ActionLayout(named = "Next", cssClassFa = "angle-double-right", cssClassFaPosition = ActionLayout.CssClassFaPosition.RIGHT)
    @MemberOrder(sequence = "1")
    public DemoObjectWizard next() {
        setState(getState().next());
        return this;
    }
    public boolean hideNext(){
        return getState().next() == null;
    }
    public TranslatableString disableNext(){
        return validate();
    }

    @ActionLayout(named = "Previous", cssClassFa = "angle-double-left", cssClassFaPosition = ActionLayout.CssClassFaPosition.LEFT)
    @MemberOrder(sequence = "1")
    public DemoObjectWizard previous() {
        setState(getState().previous());
        return this;
    }
    public String disablePrevious() {
        return getState().disablePrevious(this);
    }

    @ActionLayout(cssClassFa = "angle-double-right", cssClassFaPosition = ActionLayout.CssClassFaPosition.RIGHT)
    public Object finish(){
        throw new ApplicationException("Configuration error: implemented in sub class.");
    }
    public boolean hideFinish(){
        return getState().next() != null;
    }
    // </editor-fold>

    // <editor-fold desc="Validation">
    private TranslatableString validate(){
        switch (getState()){
            case GENERAL:
                return validateGeneral();
            case SHIPMENT_AND_CONDITIONS:
                return validateShipmentAndConditions();
            case CONTACT:
                return validateContact();
            default:
                return null; // no validation is needed for the other states
        }
    }

    protected TranslatableString validateGeneral(){
        if(getSomeText() == null){
            return NEXT_DISABLED_REQUIRED_FIELDS_MSG;
        }
        return null;
    }

    protected TranslatableString validateShipmentAndConditions(){
        if(getShipment() == null){
            return NEXT_DISABLED_REQUIRED_FIELDS_MSG;
        }
        return null;
    }

    protected TranslatableString validateContact(){
        if(getContact() == null){
            return NEXT_DISABLED_REQUIRED_FIELDS_MSG;
        }
        return null;
    }
    // </editor-fold>

    public List<BreadCrumb> getBreadCrumbs(){
        return Arrays.stream(WizardDemoObjectState.values())
                .map(s -> new WizardDemoObjectBreadCrumb(this, s, s.ordinal() < getState().ordinal(), s.equals(getState())))
                .collect(Collectors.toList());
    }

    @Property(editing = Editing.DISABLED)
    @PropertyLayout(named = "Error", multiLine = 2, labelPosition = LabelPosition.NONE)
    @Getter @Setter
    private String errorMessage;
    public boolean hideErrorMessage(){
        return getErrorMessage() == null;
    }
    protected void clearErrorMessage(){
        setErrorMessage(null);
    }

    /**
     * Init general properties for clone
     */
    public DemoObjectWizard clone(){
        DemoObjectWizard clone = new DemoObjectWizard();
        clone.setState(getState());
        clone.setErrorMessage(getErrorMessage());

        clone.setSomeText(getSomeText());
        clone.setContact(getContact());
        clone.setShipment(getShipment());

        return clone;
    }
}
