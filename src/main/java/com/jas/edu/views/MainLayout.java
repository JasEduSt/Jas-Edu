package com.jas.edu.views;

import com.jas.edu.authentication.AccessControl;
import com.jas.edu.authentication.AccessControlFactory;
import com.jas.edu.authentication.CurrentUser;
import com.jas.edu.views.sciencedetail.CurrentEventsView;
import com.jas.edu.views.sciencedetail.GeneralScienceView;
import com.jas.edu.views.tamildetail.GeneralTamilView;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Link;

/**
 * The main layout is a top-level placeholder for other views.
 */
@PageTitle("Mainlayout- Online Learning Portal")
@Route(value = "")
@RouteAlias(value = "home")
@AnonymousAllowed
//@PermitAll

public class MainLayout extends AppLayout implements RouterLayout {

    private H1 viewTitle;

    private final Button logoutButton;

    public MainLayout() {


        this.setPrimarySection(Section.NAVBAR );
        addHeaderContent();
        addDrawerContent();

        Footer footer = new Footer();
        footer.setText(com.jas.edu.utility.CreateWord.getFooterText());
        footer.addClassName("footer");

        final VerticalLayout contentLayout = new VerticalLayout();
        contentLayout.add(new HomeView(), footer);

        setContent(contentLayout);

        logoutButton = createMenuButton("Logout", VaadinIcon.SIGN_OUT.create());
        logoutButton.addClickListener(e -> logout());
        logoutButton.getElement().setAttribute("title", "Logout (Ctrl+L)");

    }

    private Button createMenuButton(String caption, Icon icon) {
        final Button routerButton = new Button();
        routerButton.setClassName("menu-button");
        //routerButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        routerButton.setIcon(icon);
        routerButton.setSuffixComponent(new Span(caption));
        icon.setSize("24px");
        return routerButton;
    }

    private void addHeaderContent() {
        //String user = securityService.getAuthenticatedUser().getUsername();
        DrawerToggle toggle = new DrawerToggle();
        Div span = new Div("");
        span.setMinWidth("8%");
        //setDrawerOpened(false);
        toggle.setMaxWidth("10%");
        toggle.getElement().setAttribute("theme", Lumo.DARK);
        toggle.setTooltipText("CLICK TO GET FULL SCREEN VIEW");
        toggle.addClassNames( LumoUtility.Padding.MEDIUM,LumoUtility.Background.BASE);

        viewTitle = new H1("ONLINE LEARNING PORTAL FOR COMPETETIVE EXAMS");
        viewTitle.setMinWidth("40%");
        //Slide slide1 = new Slide(createSlide1Layout());

        //addToNavbar(true, toggle, viewTitle, span, getMenuBar());
        addToNavbar(true, toggle, viewTitle, span);


        addToNavbar(createMenuLink(AboutView.class, AboutView.VIEW_NAME,
                VaadinIcon.INFO_CIRCLE.create()));

        addToNavbar(createMenuLink(HomeView.class, HomeView.VIEW_NAME,
                VaadinIcon.HOME.create()));

        addToNavbar(createMenuLink(DownloadView.class, DownloadView.VIEW_NAME,
                VaadinIcon.DATABASE.create()));

        addToNavbar(createMenuLink(NotificationView.class, NotificationView.VIEW_NAME,
                VaadinIcon.ALARM.create()));

        addToNavbar(createMenuLink(RegistrationView.class, RegistrationView.VIEW_NAME,
                VaadinIcon.SIGN_IN.create()));


    }

    private RouterLink createMenuLink(Class<? extends Component> viewClass,
                                      String caption, Icon icon) {
        final RouterLink routerLink = new RouterLink(viewClass);
        routerLink.setClassName("menu-link");
        routerLink.add(icon);
        routerLink.add(new Span(caption));
        icon.setSize("24px");
        return routerLink;
    }

    private void addDrawerContent() {
        H2 appName = new H2("Jas Edu...");
        //Animator animator = Animator.init(UI.getCurrent());
        //AnimatedComponent animatedComponent = animator.prepareComponent(appName);
        //animatedComponent.animate(AnimationBuilder.createBuilder().create(AnimationTypes.TextAnimation.class).withEffect(TextDisplayEffect.LetterJump));
        appName.addClassNames(LumoUtility.Background.SUCCESS_50,LumoUtility.TextColor.PRIMARY,LumoUtility.FontSize.XLARGE,LumoUtility.BorderColor.CONTRAST);
        Header header = new Header(appName);

        SideNav nav = createNavigation();

        addToDrawer(header, nav);

    }

    private void logout() {

        AccessControlFactory.getInstance().createAccessControl().signOut();
    }


    private SideNav getSideNavigation(SideNav nav) {
        SideNavItem tnpsc = new SideNavItem("TNPSC");
        getTnpscSubs(tnpsc);
        SideNavItem banking = new SideNavItem("BANKING");
        banking.setExpanded(false);
        SideNavItem railways = new SideNavItem("RAILWAYS");
        railways.setExpanded(false);
        SideNavItem lic = new SideNavItem("LIC");
        lic.setExpanded(false);
        SideNavItem jee = new SideNavItem("JEE");
        jee.setExpanded(false);
        SideNavItem neet = new SideNavItem("NEET");
        neet.setExpanded(false);
        SideNavItem Utils = new SideNavItem("CUSTOMER RELATIONS");
        getUtilSubs(Utils);
        nav.addItem(tnpsc,banking,railways,lic,jee,neet,Utils);
        return nav;
    }

    private SideNavItem getUtilSubs(SideNavItem utils) {
        SideNavItem utilSubfb = new SideNavItem("Feedback/Testimonials ", Feedbackview.class);
        utils.addItem(utilSubfb);
        SideNavItem utilSubme = new SideNavItem("Mock Test ", Mocktestview.class);
        utils.addItem(utilSubme);
        return  utils;
    }

    private SideNavItem getTnpscSubs(SideNavItem tnpsc) {
        SideNavItem tnpscsubgo = new SideNavItem("Group I");
        tnpscsubgo.setVisible(false);
        SideNavItem tnpscsubgt = new SideNavItem("Group II");
        getTnpscGrouptwoSubs(tnpscsubgt);
        SideNavItem tnpscsubgta = new SideNavItem("Group IIA");
        getTnpscGrouptwoaSubs(tnpscsubgta);
        SideNavItem tnpscsubgf = new SideNavItem("Group IV");
        getTnpscGroupfourSubs(tnpscsubgf);
        String urls = "https://www.tnpsc.gov.in/english/syllabus.html";
        SideNavItem tnpscsubsy = new SideNavItem("TNPSC Syllabus Link", urls);

        tnpsc.addItem(tnpscsubgo,tnpscsubgt,tnpscsubgta,tnpscsubgf,tnpscsubsy);
        return tnpsc;
    }

    private SideNavItem getTnpscGrouptwoaSubs(SideNavItem tnpscsubgta) {
        getTnpscGroupfourSubs(tnpscsubgta);
        return tnpscsubgta;
    }

    private SideNavItem getTnpscGrouptwoSubs(SideNavItem tnpscsubgt) {
        getTnpscGroupfourSubs(tnpscsubgt);
        return tnpscsubgt;
    }

    private SideNavItem getTnpscGroupfourSubs(SideNavItem tnpscsubgf) {

        //String url = "https://tnpsc.gov.in/static_pdf/syllabus/31122024_Group_VI_Mains_Syllabus.pdf";
        //SideNavItem group4syllabus = new SideNavItem("Group IV Syllabus - TNPSC", url);
        SideNavItem tnpscsubgt = new SideNavItem("General Tamil ",GeneralTamilView.class);
        SideNavItem tnpscsubgst = new SideNavItem("General Studies");
        getTnpscsubgs(tnpscsubgst);
        SideNavItem tnpscsubga = new SideNavItem("General Aptitude ");
        tnpscsubga.setVisible(false);
        tnpscsubgf.addItem(tnpscsubgt,tnpscsubgst,tnpscsubga);
        return tnpscsubgf;
    }

    private SideNavItem getTnpscsubgs(SideNavItem tnpscsubgst) {
        SideNavItem tnpscsubgsc = new SideNavItem("General Science ", GeneralScienceView.class);
        SideNavItem tnpscsubce = new SideNavItem("Current Events", CurrentEventsView.class);
        SideNavItem tnpscsubg = new SideNavItem("Geography");
        SideNavItem tnpscsubi = new SideNavItem("History,Culture,Polity,Economy of India");
        SideNavItem tnpscsubt = new SideNavItem("History,Culture,Polity,Heritage,Admin of TamilNadu");
        tnpscsubgst.addItem(tnpscsubgsc,tnpscsubce,tnpscsubg,tnpscsubi,tnpscsubt);
        return tnpscsubgst;
    }


    private SideNav createNavigation() {
        SideNav nav = new SideNav();
        getSideNavigation(nav);
        return nav;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title;
        String user = null;
        try {
            title = getContent().getClass().getAnnotation(PageTitle.class);
            user = CurrentUser.get();
        }
        catch(Exception e){
            Class<MainLayout> lay = MainLayout.class;
            title =  lay.getAnnotation(PageTitle.class);
            user = CurrentUser.get();
        }
        return title == null && user== null ? "" : title.value() + "     :- Welcome to JasEdu   " + user;
    }

    private void registerAdminViewIfApplicable(AccessControl accessControl) {
        // register the admin view dynamically only for any admin user logged in
        if (accessControl.isUserInRole(AccessControl.ADMIN_ROLE_NAME) && accessControl.getPrincipalName().equals("admin")
                && !RouteConfiguration.forSessionScope()
                .isRouteRegistered(NotificationView.class)) {
            RouteConfiguration.forSessionScope().setRoute(NotificationView.VIEW_NAME,
                    NotificationView.class, MainLayout.class);
            // as logout will purge the session route registry, no need to
            // unregister the view on logout
        }
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);

        // User can quickly activate logout with Ctrl+L
        attachEvent.getUI().addShortcutListener(() -> logout(), Key.KEY_L,
                KeyModifier.CONTROL);

        // add the admin view menu item if user has admin role
        final AccessControl accessControl = AccessControlFactory.getInstance()
                .createAccessControl();
        if (accessControl.isUserInRole(AccessControl.ADMIN_ROLE_NAME)) {
            System.out.println("admin check");
            // Create extra navigation target for admins
            registerAdminViewIfApplicable(accessControl);

            // The link can only be created now, because the RouterLink checks
            // that the target is valid.
            addToDrawer(createMenuLink(NotificationView.class, NotificationView.VIEW_NAME,
                    VaadinIcon.INFO.create()));
        }

        // Finally, add logout button for all users
        addToNavbar(logoutButton);
    }

}
