package org.isisaddons.module.wizard.applib;

import java.util.List;

/**
 * Created by erik on 11/1/16.
 */
public interface BreadcrumbHolder {
    List<BreadCrumb> getBreadCrumbs();

    State getState();
}
