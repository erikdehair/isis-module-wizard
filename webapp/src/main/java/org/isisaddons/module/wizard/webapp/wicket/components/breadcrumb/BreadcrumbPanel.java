package org.isisaddons.module.wizard.webapp.wicket.components.breadcrumb;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.Buttons;
import org.apache.isis.core.metamodel.adapter.ObjectAdapter;
import org.apache.isis.viewer.wicket.model.models.ActionModel;
import org.apache.isis.viewer.wicket.model.models.EntityCollectionModel;
import org.apache.isis.viewer.wicket.ui.components.widgets.linkandlabel.ActionLinkFactoryAbstract;
import org.apache.isis.viewer.wicket.ui.components.widgets.linkandlabel.AjaxDeferredBehaviour;
import org.apache.isis.viewer.wicket.ui.util.CssClassAppender;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.resource.CssResourceReference;
import org.isisaddons.module.wizard.applib.BreadCrumb;
import org.isisaddons.module.wizard.applib.BreadcrumbHolder;

public class BreadcrumbPanel extends Panel implements IHeaderContributor {
	private static final long serialVersionUID = 1L;

    private static final String CRUMBS_LIST_ID = "crumbsList";
    private static final String STATE_TITLE_ID = "stateTitle";
	
	public BreadcrumbPanel(String id, EntityCollectionModel model){
		super(id, model);

        addCrumbs();
        ActionLinkFactoryAbstract a;
	}

    private void addCrumbs(){
        RepeatingView view = new RepeatingView(CRUMBS_LIST_ID);

        add(view);

        EntityCollectionModel ecm = (EntityCollectionModel) getDefaultModel();

        BreadCrumb crumb;
        WebMarkupContainer listItem;
        BreadcrumbHolder holder;
        for (ObjectAdapter adapter : ecm.getObject()){
            crumb = (BreadCrumb) adapter.getObject();
            holder = crumb.getHolder();
            listItem = new WebMarkupContainer(view.newChildId());
            listItem.add(new Label(STATE_TITLE_ID, crumb.getTitle()));
            if(crumb.isNavigable()) {
                CssClassAppender.appendCssClassTo(listItem, "picked");
            }
            if(crumb.isActive()){
                CssClassAppender.appendCssClassTo(listItem, "active");
            }
            view.add(listItem);
        }
    }

    // poging tot clickbaar maken van de breadcrumbs
    protected AbstractLink newLink(final String linkId, final ObjectAdapter objectAdapter) {

        // see {@see ActionLinkFactoryAbstract}
        final AjaxDeferredBehaviour ajaxDeferredBehaviour = createAjaxDeferredBehaviour(objectAdapter);

        final AbstractLink link = new AjaxLink<Object>(linkId) {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                ajaxDeferredBehaviour.initiate(target);
            }

            @Override
            protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
                super.updateAjaxAttributes(attributes);

                // allow the event to bubble so the menu is hidden after click on an item
                attributes.setEventPropagation(AjaxRequestAttributes.EventPropagation.BUBBLE);
            }

            @Override
            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);

                Buttons.fixDisabledState(this, tag);
            }
        };

        link.add(ajaxDeferredBehaviour);

        link.add(new CssClassAppender("noVeil"));

        return link;
    }

    private AjaxDeferredBehaviour createAjaxDeferredBehaviour(ObjectAdapter breadcrumbHolderAdapter) {
        return new AjaxDeferredBehaviour(AjaxDeferredBehaviour.OpenUrlStrategy.NEW_WINDOW) {

            private static final long serialVersionUID = 1L;

            @Override
            protected IRequestHandler getRequestHandler() {
                ObjectAdapter resultAdapter = breadcrumbHolderAdapter;
                final Object value = resultAdapter.getObject();
                return ActionModel.redirectHandler(value);
            }
        };
    }

    @Override
    public void renderHead(final IHeaderResponse response){
        super.renderHead(response);
        response.render(CssHeaderItem.forReference(new CssResourceReference(BreadcrumbPanel.class, "breadcrumb-panel.css")));
    }
}
