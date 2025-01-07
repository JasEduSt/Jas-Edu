package com.jas.edu.views;

import com.jas.edu.JConstants;
import com.jas.edu.utility.CreateWord;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.PermitAll;

@PageTitle("Online Mock Test")
@Route(value = "mocktestview",layout = MainLayout.class)
@AnonymousAllowed
//@PermitAll
public class Mocktestview extends VerticalLayout {

    public Mocktestview(){

        Footer footer = new Footer();
        footer.setText(CreateWord.getFooterText());
        footer.addClassName("footer");
        add(footer);
    }
}
