package org.isisaddons.module.wizard.webapp.wicket.components.breadcrumb;

import org.apache.isis.viewer.wicket.model.models.EntityCollectionModel;
import org.apache.isis.viewer.wicket.ui.ComponentFactoryAbstract;
import org.apache.isis.viewer.wicket.ui.ComponentType;
import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.isisaddons.module.wizard.applib.BreadCrumb;

public class BreadcrumbPanelFactory extends ComponentFactoryAbstract {
    private static final long serialVersionUID = 1L;

    public BreadcrumbPanelFactory(){
        super(ComponentType.COLLECTION_CONTENTS, BreadcrumbPanel.class);
    }

    @Override
    public ApplicationAdvice appliesTo(final IModel<?> model) {
        EntityCollectionModel ecm = (EntityCollectionModel) model;
        Class<?> clazz = ecm.getTypeOfSpecification().getCorrespondingClass();
        return appliesIf(BreadCrumb.class.isAssignableFrom(clazz));
    }

    @Override
    public Component createComponent(final String id, final IModel<?> model){
        EntityCollectionModel ecm = (EntityCollectionModel) model;
        return new BreadcrumbPanel(id, ecm);
    }
}
