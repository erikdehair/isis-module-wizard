package org.isisaddons.module.wizard.dom.wizard;

import org.apache.isis.applib.annotation.DomainService;
import org.apache.isis.applib.annotation.NatureOfService;

/**
 * Created by E. de Hair <e.dehair@pocos.nl> on 2/20/17.
 */
@DomainService(nature = NatureOfService.VIEW_MENU_ONLY)
public class DemoObjectWizardService {

    public DemoObjectWizard startWizard(){
        return new DemoObjectWizard();
    }
}
