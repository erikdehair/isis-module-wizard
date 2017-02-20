/**
 *
 */
package org.isisaddons.module.wizard.webapp.wicket.components;

import org.apache.isis.viewer.wicket.viewer.registries.components.ComponentFactoryRegistrarDefault;
import org.isisaddons.module.wizard.webapp.wicket.components.breadcrumb.BreadcrumbPanelFactory;

/**
 * @author "Erik de Hair <erik@pocos.nl>"
 */
public class WizardDemoComponentFactoryRegistrar extends ComponentFactoryRegistrarDefault {
    @Override
    public void addComponentFactories(ComponentFactoryList componentFactories) {
        componentFactories.add(new BreadcrumbPanelFactory());
        super.addComponentFactories(componentFactories);
    }
}
