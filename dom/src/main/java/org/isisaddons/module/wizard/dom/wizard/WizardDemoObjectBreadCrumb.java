package org.isisaddons.module.wizard.dom.wizard;

import org.apache.isis.applib.annotation.ViewModel;
import org.isisaddons.module.wizard.applib.BreadcrumbHolder;

/**
 * Created by E. de Hair <e.dehair@pocos.nl> on 10/28/16.
 */
@ViewModel
public class WizardDemoObjectBreadCrumb extends org.isisaddons.module.wizard.applib.BreadCrumb {

    public WizardDemoObjectBreadCrumb(){

    }

    public WizardDemoObjectBreadCrumb(BreadcrumbHolder wizard, WizardDemoObjectState state, boolean navigable, boolean active){
        setHolder(wizard);
        setState(state);
        setNavigable(navigable);
        setActive(active);
    }

    public String title(){
        return getTitle();
    }

    public String getTitle(){
        return getState().getTitle();
    }

    @Override
    public Object clone() {
        WizardDemoObjectBreadCrumb clone = new WizardDemoObjectBreadCrumb();
        return super.clone(clone);
    }
}
