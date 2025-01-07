package com.jas.edu.views;

import com.jas.edu.JConstants;
import com.jas.edu.data.entity.UserDetails;
import com.jas.edu.data.entity.Usersfeed;
import com.jas.edu.data.services.FeedbackDetailsService;
import com.jas.edu.data.services.UserDetailsService;
import com.jas.edu.utility.CreateWord;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import jakarta.annotation.security.PermitAll;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import java.util.Optional;

@PageTitle("Feedback/Testimonials")
@Route(value = "feedbackview",layout = MainLayout.class)
@AnonymousAllowed
//@PermitAll
@RegisterReflectionForBinding({Usersfeed.class})
public class Feedbackview extends Div implements BeforeEnterObserver{
    private final Grid<Usersfeed> ufgrid = new Grid<>(Usersfeed.class, false);
    private TextField firstName;
    private TextField lastName;
    private EmailField email;
    private TextArea address;
    private TextArea suggestions;
    private TextField postalCode;
    private TextField contactNo;
    ComboBox<String> ratingGroup;
    private Button submit;
    private Button cancel;
    private final FeedbackDetailsService service;
    private UserDetailsService userDetailsService;
    private Usersfeed currentusersfeed;
    //private BeanValidationBinder<Usersfeed> binder = new BeanValidationBinder<Usersfeed>(Usersfeed.class);
    private BeanValidationBinder<Usersfeed> binder;
    FormLayout formLayout;


    public Feedbackview(@Autowired FeedbackDetailsService service,UserDetailsService userDetailsService) {
        this.service = service;
        this.userDetailsService = userDetailsService;

        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setOrientation(SplitLayout.Orientation.HORIZONTAL);

        createTestimonialLayout(splitLayout);
        createFeedbackLayout(splitLayout);

        Footer footer = new Footer();
        footer.setText(CreateWord.getFooterText());
        footer.addClassName("footer");
        add(splitLayout,footer);

        ufgrid.setMinHeight("90%");
        ufgrid.setMinWidth("55%");
        ufgrid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        ufgrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        ufgrid.addThemeVariants(GridVariant.LUMO_COMPACT,GridVariant.LUMO_WRAP_CELL_CONTENT);
        ufgrid.addColumn("firstName").setHeader("Name:");
        ufgrid.addColumn("lastName").setVisible(false);
        ufgrid.addColumn("email").setVisible(false);
        ufgrid.addColumn("contactNo").setVisible(false);
        ufgrid.addColumn("ratingGroup").setHeader("Rating:");
        ufgrid.addColumn("address").setVisible(false);
        ufgrid.addColumn("postalCode").setVisible(false);
        ufgrid.addColumn("suggestions").setHeader("Testimonials:");
        ufgrid.setItems(query -> service.list(
                        PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());


    }
    private void createFeedbackLayout(SplitLayout splitLayout) {
        Div wrapper1 = new Div();
        splitLayout.addToSecondary(wrapper1);
        wrapper1.setMinWidth("45%");
        wrapper1.setMinHeight("90%");

        Div editorLayoutDiv = new Div();
        Div editorDiv = new Div();
        editorLayoutDiv.add(editorDiv);

        formLayout = new FormLayout();

        HorizontalLayout titleBar = new HorizontalLayout();
        H4 title = new H4("Submit Your Feedback  Here:");
        titleBar.add(title);
        formLayout.add(titleBar);
        formLayout.getStyle().setBorderLeft("0");

        firstName  = new TextField(" First Name:");
        firstName.setValueChangeMode(ValueChangeMode.EAGER);
        lastName = new TextField(" Last Name:");
        lastName.setValueChangeMode(ValueChangeMode.EAGER);
        email = new EmailField ("E-mail Address:");
        email.setValueChangeMode(ValueChangeMode.EAGER);
        contactNo = new TextField("Contact Number:");
        contactNo.setValueChangeMode(ValueChangeMode.EAGER);
        ratingGroup = new ComboBox<>();

        address = new TextArea(" Contact Address:");
        postalCode = new TextField("Postal Code:");
        suggestions = new TextArea("Tell Your Opinions:");
        submit = new Button("Submit");
        cancel = new Button( "Cancel");

        firstName.setClearButtonVisible(true);

        lastName.setClearButtonVisible(true);

        email.setClearButtonVisible(true);

        contactNo.setClearButtonVisible(true);

        ratingGroup.setLabel("This portal is :");
        ratingGroup.setItems("Excellent", "Good", "Average", "poor");
        ratingGroup.setValue("Good");

        address.setClearButtonVisible(true);

        postalCode.setClearButtonVisible(true);

        suggestions.setClearButtonVisible(true);

        formLayout.add(firstName,lastName,email,ratingGroup,address,postalCode,contactNo,suggestions);

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("300px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP));

        formLayout.setColspan(title, 2);
        formLayout.setColspan(email, 2);
        formLayout.setColspan(ratingGroup, 2);
        formLayout.setColspan(address, 2);
        formLayout.setColspan(suggestions, 2);
        formLayout.setColspan(titleBar, 2);

        Span errorMessage = new Span();
        errorMessage.getStyle().set("color", "var(--lumo-error-text-color)");
        errorMessage.getStyle().set("padding", "15px 0");

        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);
        wrapper1.add(editorLayoutDiv);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        //binder.bindInstanceFields(this);

        submit.addClickListener(e -> {
            try{
                binder = new BeanValidationBinder<Usersfeed>(Usersfeed.class);
                binder.forField(firstName).asRequired().bind(Usersfeed::getFirstName, Usersfeed::setFirstName);
                binder.forField(lastName).asRequired().bind(Usersfeed::getLastName, Usersfeed::setLastName);
                binder.forField(email).asRequired().bind(Usersfeed::getEmail, Usersfeed::setEmail);
                binder.forField(contactNo).asRequired().bind(Usersfeed::getContactNo, Usersfeed::setContactNo);
                binder.forField(ratingGroup).asRequired().bind(Usersfeed::getRatingGroup, Usersfeed::setRatingGroup);
                binder.forField(address).asRequired().bind(Usersfeed::getAddress, Usersfeed::setAddress);
                binder.forField(postalCode).asRequired().bind(Usersfeed::getPostalCode, Usersfeed::setPostalCode);
                binder.forField(suggestions).asRequired().bind(Usersfeed::getSuggestions, Usersfeed::setSuggestions);


                //Usersfeed currentusersfeed =  new Usersfeed();
                Usersfeed currentusersfeed = new Usersfeed(firstName.getValue(),lastName.getValue(),email.getValue(),contactNo.getValue(),ratingGroup.getValue(),address.getValue(),postalCode.getValue(),suggestions.getValue());
                //binder.bindInstanceFields(currentusersfeed);
                //binder.setStatusLabel(errorMessage);
                //binder.writeBean(currentusersfeed);
                Optional<UserDetails> currentuser = userDetailsService.findUser(currentusersfeed.getFirstName(),currentusersfeed.getEmail());
                if(currentuser.isPresent()){
                    service.update(currentusersfeed);
                    clearForm();
                    refreshGrid();
                    Notification success = Notification.show("Feedback Data updated!");
                    success.setPosition(Notification.Position.MIDDLE);
                    success.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    UI.getCurrent().navigate(Feedbackview.class);}
                else{
                    Notification failure = Notification.show("Only registered users can submit feedback details! Please Sign Up to continue.");
                    failure.setPosition(Notification.Position.MIDDLE);
                    failure.addThemeVariants(NotificationVariant.LUMO_WARNING);
                }
            } catch (ObjectOptimisticLockingFailureException exception) {
                Notification error = Notification.show("Error updating the data. Somebody else has updated the record while you were making changes.");
                error.setPosition(Notification.Position.MIDDLE);
                error.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } catch (Exception error) {
                Notification err = Notification.show( " : Validation Fails - Fields should not be empty");
                err.setPosition(Notification.Position.MIDDLE);
                err.addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
    }
    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        submit.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(submit, cancel );
        editorLayoutDiv.add(buttonLayout);
    }
    private void createTestimonialLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        splitLayout.addToPrimary(wrapper);
        H3 testimonial = new H3("Know the Testimonials :");
        wrapper.add(testimonial);
        wrapper.add(ufgrid);
        wrapper.setMinWidth("55%");
        wrapper.setMinHeight("90%");
        wrapper.setVisible(true);
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(Usersfeed value) {
        this.currentusersfeed = value;
        binder.readBean(this.currentusersfeed);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        refreshGrid();
        event.forwardTo(Feedbackview.class);
    }

    private void refreshGrid() {
        ufgrid.select(null);
        ufgrid.getDataProvider().refreshAll();
    }

}
