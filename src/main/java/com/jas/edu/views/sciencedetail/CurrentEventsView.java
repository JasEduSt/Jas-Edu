package com.jas.edu.views.sciencedetail;

import com.jas.edu.JConstants;
import com.jas.edu.data.entity.Currentevents;
import com.jas.edu.data.services.CurrentEventsService;
import com.jas.edu.utility.CreateWord;
import com.jas.edu.views.MainLayout;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import org.springframework.data.domain.PageRequest;

import java.util.stream.Stream;
@PageTitle("Current Events")
@Route(value = JConstants.LCURRENTEVENTS,layout = MainLayout.class)
@AnonymousAllowed
public class CurrentEventsView extends VerticalLayout {

    private final CurrentEventsService currentEventsService;
    public static final String VIEW_NAME = "CurrentEvents";

    TextField searchField = new TextField();
        public CurrentEventsView(CurrentEventsService currentEventsService) {
            this.currentEventsService = currentEventsService;

            Div wrapper = new Div();
            wrapper.setWidthFull();
            wrapper.setMinHeight("90%");

            Grid toadd = populateGrid();
;

            Footer footer = new Footer();
            footer.setText(CreateWord.getFooterText());
            footer.addClassName("footer");

            toadd.setMinHeight("90%");
            toadd.addThemeVariants(GridVariant.LUMO_COMPACT);
            toadd.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
            toadd.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);

            wrapper.add(toadd);
            searchField.setWidth("50%");
            searchField.setPlaceholder("Search");
            searchField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
            this.add(searchField,wrapper, footer);
        }

    private Grid populateGrid() {

            Grid<Currentevents> grid = new Grid<>(Currentevents.class, false);
            //grid.setHeight("95%");
            //grid.setWidth("95%");
            grid.addColumn(Currentevents::getCe_id).setHeader("SL NO").setAutoWidth(true).setFlexGrow(0).setVisible(false);
            grid.addColumn(Currentevents::getYearmonth).setHeader("Year/Month").setSortable(true).setAutoWidth(true).setFlexGrow(0);;
            grid.addColumn(Currentevents::getCe_question).setHeader("Question");
            grid.addColumn(Currentevents::getCe_answer).setHeader("Answer");
            grid.setItemDetailsRenderer(createCurrenteventsRenderer());

            grid.setItems(query -> currentEventsService.list(
                        PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
            grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
            grid.addClassName("my-grid");

            return grid;

    }

    private static ComponentRenderer<CurrenteventsDetailsFormLayout, Currentevents> createCurrenteventsRenderer() {
            return new ComponentRenderer<>(CurrenteventsDetailsFormLayout::new,
                    CurrenteventsDetailsFormLayout::setCurrentevents);
        }

        private static class CurrenteventsDetailsFormLayout extends FormLayout {
            private final TextArea ce_question = new TextArea("Question");
            private final TextArea ce_answer = new TextArea("Answer");

            public CurrenteventsDetailsFormLayout() {
                Stream.of(ce_question,ce_answer).forEach(field -> {
                    field.setReadOnly(true);
                    add(field);
                });

            }

            public void setCurrentevents(Currentevents currentevents) {
                ce_question.setValue(currentevents.getCe_question());
                ce_answer.setValue(currentevents.getCe_answer());
            }
        }
}
