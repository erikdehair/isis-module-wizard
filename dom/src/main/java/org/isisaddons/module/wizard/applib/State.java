package org.isisaddons.module.wizard.applib;

/**
 * Created by erik on 11/1/16.
 */
public interface State {
    String getTitle();

    State next();

    String disableNext(BreadcrumbHolder w);

    State previous();

    String disablePrevious(BreadcrumbHolder w);
}
