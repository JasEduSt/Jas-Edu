package com.jas.edu.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.PermitAll;

@PageTitle("aboutview")
@Route(value = "aboutview",layout = MainLayout.class)
@AnonymousAllowed
//@PermitAll
public class AboutView extends VerticalLayout {
    public static final String VIEW_NAME = "About";
    Button t;
    AboutView(){

        t = new Button("Under Construction");
        add(t);

    }

}