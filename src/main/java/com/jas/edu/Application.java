package com.jas.edu;


import com.jas.edu.authentication.AccessControl;
import com.jas.edu.authentication.AccessControlFactory;
import com.jas.edu.views.LoginView;
import com.jas.edu.views.RegistrationView;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
@NpmPackage(value = "line-awesome", version = "1.3.0")
@Theme(value = "jas-edu")
//@Theme(variant = Lumo.DARK)
@PWA(
        name = "jas-edu",
        shortName = "jas-edu",
        offlinePath="offline.html",
        offlineResources = { "images/offline.png"}
)

public class Application extends SpringBootServletInitializer implements AppShellConfigurator, VaadinServiceInitListener {

    @Override
    public void serviceInit(ServiceInitEvent initEvent) {
        final AccessControl accessControl = AccessControlFactory.getInstance()
                .createAccessControl();

        initEvent.getSource().addUIInitListener(uiInitEvent -> {
            uiInitEvent.getUI().addBeforeEnterListener(enterEvent -> {
                if (!accessControl.isUserSignedIn() && !LoginView.class
                        .equals(enterEvent.getNavigationTarget()) && !RegistrationView.class
                        .equals(enterEvent.getNavigationTarget()))
                    enterEvent.rerouteTo(LoginView.class);

            });
        });
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}