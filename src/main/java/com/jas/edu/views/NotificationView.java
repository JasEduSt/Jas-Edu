package com.jas.edu.views;

import com.jas.edu.JConstants;
import com.jas.edu.authentication.AccessControl;
import com.jas.edu.authentication.AccessControlFactory;
import com.jas.edu.data.entity.Tnpscns;
import com.jas.edu.data.services.TnpscnsService;
import com.jas.edu.utility.CreateWord;
import com.jas.edu.webpush.WebPushService;
import com.jas.edu.webpush.WebPushToggle;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.renderer.LitRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import jakarta.annotation.security.PermitAll;
import org.apache.poi.hslf.record.InteractiveInfoAtom;
import org.springframework.data.domain.PageRequest;
import org.vaadin.addons.tatu.TabSheet;


@PageTitle("Notifications")
@Route(value = "notificationview",layout = MainLayout.class)
@AnonymousAllowed
//@PermitAll
public class NotificationView extends VerticalLayout {


    public static final String VIEW_NAME = "Notification";

    Button notify = new Button("Notify all users!");
    TabSheet notificationTab;
    private TnpscnsService tnpscnsService;
    private WebPushService webPushService;
    TextArea messageInput ;
    private Tnpscns tnpscns;
    final AccessControl accessControl = AccessControlFactory.getInstance()
            .createAccessControl();

    private static final String LIT_TEMPLATE_HTML = """
            <vaadin-button title="Go to ..."
                           @click="${clickHandler}"
                           theme="tertiary-inline small link">
                ${item.id}
            </vaadin-button>""";

    public NotificationView(WebPushService webPushService, TnpscnsService tnpscnsService) {
        this.webPushService = webPushService;
        this.tnpscnsService = tnpscnsService;
        notificationTab = new TabSheet();
        notificationTab.addThemeVariants(TabSheet.TabSheetVariant.LUMO_CENTERED);
        notificationTab.setOrientation(Tabs.Orientation.HORIZONTAL);
        notificationTab.setWidthFull();
        notificationTab.setMinHeight("90%");

        notificationTab.addTab("TNPSC Notifications",getTnpscgrid());
        notificationTab.addTab("NEET Notifications",getNeetgrid());
        notificationTab.addTab("BANKING Notifications",getBankinggrid());
        notificationTab.addTab("RAILWAYS Notifications",getRailwaysgrid());
        notificationTab.addTab("LIC Notifications",getLicgrid());
        notificationTab.addTab("JEE Notifications",getJeegrid());
        notificationTab.addTab("Other Notifications",getOthersgrid());

        var toggle = new WebPushToggle(webPushService.getPublicKey());
        messageInput = new TextArea("Message:");
        messageInput.setWidth("600px");
        var messageLayout = new HorizontalLayout(messageInput, notify);
        notify.setTooltipText("Only Admin can Notify");
        if(accessControl.isUserInRole("admin"))
            notify.setEnabled(true);
        else
            notify.setEnabled(false);
        messageLayout.setDefaultVerticalComponentAlignment(Alignment.BASELINE);

        /*add(
                new H1("Notifications : "),
                toggle,
                messageLayout
        );*/

        toggle.addSubscribeListener(e -> {
            webPushService.subscribe(e.getSubscription());
        });
        toggle.addUnsubscribeListener(e -> {
            webPushService.unsubscribe(e.getSubscription());
        });

        notify.addClickListener(e -> webPushService.notifyAll("Message from JasEdu", messageInput.getValue()));

        Footer footer = new Footer();
        footer.setText(CreateWord.getFooterText());
        footer.addClassName("footer");

        this.add(notificationTab, toggle, messageLayout, notify, footer);
    }

    private Component getOthersgrid() {
        VerticalLayout layout = new VerticalLayout();
        layout.add("IN OTHERS");
        return layout;
    }
    //var sendButton = new Button("Notify all users!");

    private Component getJeegrid() {
        VerticalLayout layout = new VerticalLayout();
        layout.add("IN JEE");
        return layout;
    }

    private Component getLicgrid() {
        VerticalLayout layout = new VerticalLayout();
        layout.add("IN LIC");
        return layout;
    }

    private Component getRailwaysgrid() {
        VerticalLayout layout = new VerticalLayout();
        layout.add("IN RAILWAYS");
        return layout;
    }

    private Component getBankinggrid() {
        VerticalLayout layout = new VerticalLayout();
        layout.add("IN BANKING");
        return layout;
    }

    private Component getNeetgrid() {
        VerticalLayout layout = new VerticalLayout();
        layout.add("IN NEET");
        return layout;
    }
    private Component getTnpscgrid() {
        tnpscns = new Tnpscns();
        VerticalLayout layout = new VerticalLayout();
        Grid<Tnpscns> gridtn = new Grid<>(Tnpscns.class, false);
        gridtn.addThemeVariants(GridVariant.LUMO_COMPACT,GridVariant.LUMO_WRAP_CELL_CONTENT);
        gridtn.addColumn("tnnotify_id").setHeader(JConstants.ID).setSortable(false);
        gridtn.addColumn("tnnotify_subject").setHeader(JConstants.SUBJECT);
        gridtn.addColumn("regPeriod").setHeader(JConstants.REGPERIOD);
        gridtn.addColumn("examDate").setHeader(JConstants.EXAMDATE);
        gridtn.addColumn(LitRenderer.<Tnpscns>of(LIT_TEMPLATE_HTML)
                                .withProperty("id", Tnpscns::getUrl)
                                .withFunction("clickHandler", tnpscns -> {
                                    UI.getCurrent().getPage().open(tnpscns.getUrl(),"_blank");
                                    Notification.show("Link was clicked for Person #" + tnpscns.getUrl());
                                }))
                .setHeader("Know More").setAutoWidth(true);

        gridtn.setWidthFull();
        gridtn.setMinHeight("60%");
        gridtn.setItems(query -> tnpscnsService.list(
                        PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());

        gridtn.asSingleSelect().addValueChangeListener(event -> {
            String tnnsMsg = event.getValue().getTnnotify_subject() + " Reg Period ::" + event.getValue().getRegPeriod() + " Exam Date :: " + event.getValue().getExamDate();
            messageInput.setValue("Alert Message :: " + tnnsMsg );

        });
        layout.add(gridtn);
        return layout;
    }

}