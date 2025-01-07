package com.jas.edu.views;

import com.jas.edu.authentication.AccessControl;
import com.jas.edu.authentication.AccessControlFactory;
import com.jas.edu.data.entity.UserDetails;
import com.jas.edu.data.services.UserDetailsService;
import com.jas.edu.utility.CreateWord;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Login | JASEDU")
@Route(value = "login",layout = MainLayout.class)
//@AnonymousAllowed
public class LoginView extends FlexLayout {
    private AccessControl accessControl;
    private UserDetailsService userDetailsService;
    private UserDetails userDetails;
    NotificationView noteview;
    PasswordRecoveryDialog passwordRecoveryDialog;

    public LoginView(@Autowired UserDetailsService userDetailsService) {
        accessControl = AccessControlFactory.getInstance().createAccessControl();
        this.userDetailsService = userDetailsService;
        passwordRecoveryDialog = new PasswordRecoveryDialog(userDetailsService);
        buildUI();
    }

    private void buildUI() {
        setSizeFull();
        setClassName("login-screen");

        // login form, centered in the available part of the screen
        LoginForm loginForm = new LoginForm();
        loginForm.addLoginListener(this::login);
        loginForm.getElement().getThemeList().add("light");
        //passwordRecoveryDialog = new PasswordRecoveryDialog();
        loginForm.addForgotPasswordListener(event -> passwordRecoveryDialog.open());
        //loginForm.addAttachListener(event-> new Button("register"));

        // layout to center login form when there is sufficient screen space
        FlexLayout centeringLayout = new FlexLayout();
        centeringLayout.setSizeFull();
        centeringLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        centeringLayout.setAlignItems(Alignment.CENTER);
        centeringLayout.add(loginForm);
        //Button register = new Button("Register");
        //centeringLayout.add(register);
        //register.addClickListener(buttonClickEvent -> UI.getCurrent().navigate("register"));
        // information text about logging in
        Component loginInformation = buildLoginInformation();
        Footer footer = new Footer();
        footer.setText(CreateWord.getFooterText());
        footer.addClassName("footer");

        add(loginInformation);
        add(centeringLayout);
        add(footer);
    }

    private Component buildLoginInformation() {
        VerticalLayout loginInformation = new VerticalLayout();
        loginInformation.setClassName("login-information");
        H1 loginInfoHeader = new H1("Login Information");
        loginInfoHeader.setWidth("100%");
        Span loginInfoText = new Span(
                "New User? Please Register to utilize the complete Portal and the complete Resources " +
                        "like Study materials, Mock Exams , Documents downloads , video tutorials etc. " +
                        "Regular updates & Exam Notifications reach out the Registered users Instantly. ");
        loginInfoText.setWidth("100%");
        loginInformation.add(loginInfoHeader);
        loginInformation.add(loginInfoText);

        return loginInformation;
    }

    private void login(LoginForm.LoginEvent event) {
        String username = event.getUsername();
        String password = event.getPassword();
        userDetails = userDetailsService.findByUsernameAndPassword(username,password);
        if (userDetails!= null && accessControl.signIn(username, password)) {
            System.out.println("inside user login");
            UI.getCurrent().navigate(HomeView.class);

        }
        else if(username.equals("admin") && password.equals("pepsil") && accessControl.signIn(username, password)){
            System.out.println("inside admin login");
            UI.getCurrent().navigate(HomeView.class);

        }
        else
            event.getSource().setError(true);
        }

}
