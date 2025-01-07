package com.jas.edu.views;

import com.jas.edu.JConstants;
import com.jas.edu.data.services.UserDetailsService;
import com.jas.edu.utility.CreateWord;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Registration ")
@Route(value = "register", layout = MainLayout.class)
@AnonymousAllowed
public class RegistrationView extends VerticalLayout  {

    public static final String VIEW_NAME = "Register";
    private final UserDetailsService userDetailsService;

    public RegistrationView(@Autowired UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        RegistrationForm registrationForm = new RegistrationForm();
        // Center the RegistrationForm
        setHorizontalComponentAlignment(Alignment.CENTER, registrationForm);

        add(registrationForm);

        Footer footer = new Footer();
        footer.setText(CreateWord.getFooterText());
        footer.addClassName("footer");
        add(footer);


        RegistrationFormBinder registrationFormBinder = new RegistrationFormBinder(registrationForm,userDetailsService);
        registrationFormBinder.addBindingAndValidation();
    }

}