package com.jas.edu.views;

import com.jas.edu.data.services.UserDetailsService;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.util.Properties;
import java.util.stream.Stream;
@AnonymousAllowed
public class PasswordRecoveryDialog extends FormLayout {
    private TextField userName = new TextField("Enter UserName");
    Button recoveryButton = new Button("Get Password");
    Button okButton = new Button("CLOSE");
    private String password;
    private Span errorMessageField;
    private UserDetailsService userDetailsService;
    Dialog recoverydialog = new Dialog();
    public PasswordRecoveryDialog(){}

    public PasswordRecoveryDialog(@Autowired UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
        errorMessageField = new Span("");
    }

    public void open(){

        recoverydialog.setMinWidth("250");
        recoverydialog.setMinHeight("250");
        recoverydialog.setHeaderTitle("PASSWORD RETRIEVAL");
        userName.setWidthFull();
        recoveryButton.setWidthFull();
        errorMessageField.setWidthFull();
        errorMessageField.addClassName(LumoUtility.TextColor.ERROR);
        recoverydialog.add(userName);

        recoverydialog.add(recoveryButton);
        recoverydialog.add(okButton);
        recoverydialog.add(errorMessageField);
        setResponsiveSteps(
                new ResponsiveStep("0", 1, ResponsiveStep.LabelsPosition.TOP),
                new ResponsiveStep("490px", 2, ResponsiveStep.LabelsPosition.TOP));

        // These components always take full width
        setColspan(userName, 2);
        setColspan(errorMessageField, 2);
        //setColspan(submitButton, 2);

        recoverydialog.open();
        setRequiredIndicatorVisible(userName);
        errorMessageField.setText("");
        recoveryButton.addClickListener(event -> getPassWord());
        okButton.addClickListener(event -> recoverydialog.close());

    }

    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }

    private void getPassWord() {
        if(userName.getValue().isEmpty())
            errorMessageField.setText("Username must not be Empty");
        else {
            errorMessageField.setText("");
            try{
                password = userDetailsService.findPassword(userName.getValue());
                if(password.isEmpty()) {
                    errorMessageField.setText("");
                    errorMessageField.setText("Invalid User Name");
                }
                else {
                    Notification.show(password);
                    Properties props = new Properties();
                    props.put("mail.transport.protocol", "smtp");
                    props.put("mail.smtp.user", "pojas32@gmail.com");
                    props.put("mail.smtp.password", "UDAYA568POLY");
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.port", "587");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.enable","true");
                    props.put("mail.smtp.EnableSSL.enable","true");

                    Authenticator auth = new SMTPAuthenticator();
                    Session session = Session.getDefaultInstance(props, auth);

                    props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                    props.setProperty("mail.smtp.socketFactory.fallback", "false");
                    props.setProperty("mail.smtp.port", "587");
                    props.setProperty("mail.smtp.socketFactory.port", "587");

                    JavaMailSenderImpl sender = new JavaMailSenderImpl();
                    sender.setHost("smtp.gmail.com");
                    sender.setUsername("pojas32@gmail.com");

                    sender.setPassword("UDAYA568POLY");
                    MimeMessage message = sender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(message);
                    helper.setTo("pojas32@gmail.com");
                    helper.setText("Your password with JASEDU is : " + password);
                    sender.send(message);
                    recoverydialog.close();
                }
            }
            catch(Exception e){
                if(e.getMessage().contains("connection") || e.getMessage().contains("mail"))
                    errorMessageField.setText("Unable to connect to mail account! the password associated with JASEDU is : " + password);
                else if(e.getMessage().contains("password") || e.getMessage().contains("null"))
                    errorMessageField.setText("Invalid User Name");

            }
        }

    }

    private class SMTPAuthenticator extends jakarta.mail.Authenticator {



        public PasswordAuthentication getPasswordAuthentication() {

            String username = "pojas32@gmail.com";

            String password = "UDAYA568POLY";

            return new PasswordAuthentication(username, password);

        }

    }
}
