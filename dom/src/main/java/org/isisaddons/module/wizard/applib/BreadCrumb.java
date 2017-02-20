package org.isisaddons.module.wizard.applib;

import lombok.Getter;
import lombok.Setter;
import org.apache.isis.applib.annotation.Property;
import org.apache.isis.applib.annotation.ViewModel;
import org.apache.isis.applib.annotation.Where;

/**
 * Created by E. de Hair <e.dehair@pocos.nl> on 10/28/16.
 */
@ViewModel
public abstract class BreadCrumb implements org.apache.isis.applib.ViewModel.Cloneable {

    public abstract String getTitle();

    @Getter @Setter
    private BreadcrumbHolder holder;

    @Getter @Setter
    private boolean navigable;

    @Getter @Setter
    private boolean active;

    @Property(hidden = Where.EVERYWHERE)
    @Getter @Setter
    private State state;

    @Override
    public abstract Object clone();

    protected BreadCrumb clone(BreadCrumb breadcrumb){
        breadcrumb.setHolder(getHolder());
        breadcrumb.setState(getState());
        breadcrumb.setActive(isActive());
        breadcrumb.setNavigable(isNavigable());
        return breadcrumb;
    }
}
