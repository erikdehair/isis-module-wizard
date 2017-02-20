package org.isisaddons.module.wizard.dom.wizard;

import lombok.Getter;
import org.isisaddons.module.wizard.applib.BreadcrumbHolder;
import org.isisaddons.module.wizard.applib.State;

/**
 * Created by erik on 11/1/16.
 */
public enum WizardDemoObjectState implements State {
    GENERAL("General display title"),
    SHIPMENT_AND_CONDITIONS("Shipment and conditions display title"),
    CONTACT("Contact display title"),
    SUMMARY_PAGE("Overview display title");

    @Getter
    final String title;
    WizardDemoObjectState(String title){
        this.title = title;
    }

    public boolean hideGeneral(){ return this != GENERAL && this != SUMMARY_PAGE; }
    public boolean hideShipmentAndConditions() { return this != SHIPMENT_AND_CONDITIONS && this != SUMMARY_PAGE; }
    public boolean hideContact() { return this != CONTACT && this != SUMMARY_PAGE; }
    public boolean hideSummary() { return this != SUMMARY_PAGE; }

    @Override
    public WizardDemoObjectState next() {
        switch (this) {
            case GENERAL: return SHIPMENT_AND_CONDITIONS;
            case SHIPMENT_AND_CONDITIONS: return CONTACT;
            case CONTACT: return SUMMARY_PAGE;
            default: return null;
        }
    }

    public String disableNext(BreadcrumbHolder w) {
        return w.getState().next() == null ? "There's no more step" : null;
    }

    @Override
    public WizardDemoObjectState previous() {
        switch (this) {
            case SUMMARY_PAGE: return CONTACT;
            case CONTACT: return SHIPMENT_AND_CONDITIONS;
            case SHIPMENT_AND_CONDITIONS: return GENERAL;
            default: return null;
        }
    }

    public String disablePrevious(BreadcrumbHolder w) {
        return w.getState().previous() == null ? "There's no previous step" : null;
    }
}
