package com.jas.edu.views;

import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import java.util.stream.Stream;

public class RegistrationForm extends FormLayout {

    private H3 title;

    private TextField firstName;
    private TextField lastName;

    private EmailField email;
    private TextField contactNo;
    private TextField userName;
    private PasswordField password;
    private PasswordField passwordConfirm;

    private Checkbox allowMarketing;

    private Span errorMessageField;

    private Button submitButton;
    private Button cancelButton;
    private TextArea address;
    private TextField postalCode;


    public RegistrationForm() {
        title = new H3("Signup Form");
        firstName = new TextField("First Name :");
        lastName = new TextField("Last Name :");
        email = new EmailField("E-mail Address :");
        contactNo = new TextField("Mobile Number :");
        address = new TextArea(" Contact Address:");
        postalCode = new TextField("Postal Code:");
        allowMarketing = new Checkbox("Allow Marketing Emails?/Whatsapp Messages?");
        allowMarketing.getStyle().set("margin-top", "10px");
        userName = new TextField("User Name");
        password = new PasswordField("Password");
        passwordConfirm = new PasswordField("Confirm password");

        setRequiredIndicatorVisible(firstName, lastName, email, contactNo, password,
                passwordConfirm);

        errorMessageField = new Span();

        submitButton = new Button("Join the community");
        cancelButton = new Button( "Cancel Registration");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        add(title, firstName, lastName, email, address, postalCode, contactNo, userName, password,
                passwordConfirm, allowMarketing, errorMessageField,
                submitButton, cancelButton);

        // Max width of the Form
        setMaxWidth("500px");

        // Allow the form layout to be responsive.
        // On device widths 0-490px we have one column.
        // Otherwise, we have two columns.
        setResponsiveSteps(
                new ResponsiveStep("0", 1, ResponsiveStep.LabelsPosition.TOP),
                new ResponsiveStep("490px", 2, ResponsiveStep.LabelsPosition.TOP));

        // These components always take full width
        setColspan(title, 2);
        setColspan(email, 2);
        setColspan(address, 2);
        setColspan(errorMessageField, 2);
        //setColspan(submitButton, 2);
    }

    public PasswordField getPasswordField() { return password; }

    public PasswordField getPasswordConfirmField() { return passwordConfirm; }

    public Span getErrorMessageField() { return errorMessageField; }

    public Button getSubmitButton() { return submitButton; }

    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }

}
