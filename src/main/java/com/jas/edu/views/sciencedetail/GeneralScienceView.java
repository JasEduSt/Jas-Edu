package com.jas.edu.views.sciencedetail;

import com.jas.edu.JConstants;
import com.jas.edu.data.entity.GeneralScience;
import com.jas.edu.data.services.GeneralScienceService;
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
import org.springframework.data.domain.PageRequest;

import java.util.stream.Stream;

import static com.vaadin.flow.spring.data.VaadinSpringDataHelpers.toSpringDataSort;

@PageTitle(JConstants.GENERALSCIENCEHEADER)
@Route(value = JConstants.LGENERALSCIENCE,layout = MainLayout.class)
//@RouteAlias(value = "/:generalstudiesDetailsViewID?/:action?(edits)", layout = MainLayout.class)
@AnonymousAllowed
//@PermitAll
public class GeneralScienceView extends VerticalLayout {

    private final GeneralScienceService scienceDetailsViewService;
    public static final String VIEW_NAME = "General Science";

    TextField searchField = new TextField();

    public GeneralScienceView(GeneralScienceService scienceDetailsViewService) {
        this.scienceDetailsViewService = scienceDetailsViewService;

        Div wrapper = new Div();
        wrapper.setWidthFull();
        //wrapper.setMinHeight("90%");

        Grid toadd = populateGrid();


        Footer footer = new Footer();
        footer.setText(CreateWord.getFooterText());
        footer.addClassName("footer");

        toadd.addAttachListener(
                a -> {
                    toadd
                            .getElement()
                            .executeJs(
                                    "this.addEventListener('keyup', function(e) {" +
                                            // ignore Space as it can still be used to
                                            // (de)select items
                                            "if (e.keyCode == 32) return;" +
                                            "let grid = $0;" +
                                            "if (grid.selectedItems){" +
                                            "grid.activeItem=this.getEventContext(e).item;" +
                                            "grid.selectedItems=[this.getEventContext(e).item];" +
                                            "grid.$server.select(this.getEventContext(e).item.key);}} )",
                                    toadd.getElement()
                            );
                }
        );

        wrapper.add(toadd);
        searchField.setWidth("50%");
        searchField.setPlaceholder("Search");
        searchField.setPrefixComponent(new Icon(VaadinIcon.SEARCH));
        this.add(searchField,wrapper, footer);
    }

    private Grid populateGrid() {
        //VerticalLayout layout = new VerticalLayout();
        Grid<GeneralScience> grid = new Grid<>(GeneralScience.class, false);
        grid.setMinHeight("95%");
        //grid.setWidth("95%");
        grid.addColumn(GeneralScience::getGs_id).setHeader("SL NO").setAutoWidth(true).setFlexGrow(0).setVisible(false);
        grid.addColumn(GeneralScience::getGs_chapter).setHeader("Chapter").setSortable(true);
        grid.addColumn(GeneralScience::getGs_question).setHeader("Question");
        grid.addColumn(GeneralScience::getGs_answer).setHeader("Answer");
        grid.setItemDetailsRenderer(createGeneralScienceRenderer());

        grid.setItems(query -> scienceDetailsViewService.list(
                        PageRequest.of(query.getPage(), query.getPageSize(), toSpringDataSort(query)))
                .stream());


        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.addThemeVariants(GridVariant.LUMO_COMPACT);

        return grid;

    }

    private static ComponentRenderer<GeneralScienceView.GeneralScienceDetailsFormLayout, GeneralScience> createGeneralScienceRenderer() {
        return new ComponentRenderer<>(GeneralScienceView.GeneralScienceDetailsFormLayout::new,
                GeneralScienceView.GeneralScienceDetailsFormLayout::setGeneralScience);
    }

    private static class GeneralScienceDetailsFormLayout extends FormLayout {
        private final TextArea gc_question = new TextArea("Question");
        private final TextArea gc_answer = new TextArea("Answer");

        public GeneralScienceDetailsFormLayout() {
            Stream.of(gc_question,gc_answer).forEach(field -> {
                field.setReadOnly(true);
                add(field);
            });

        }

        public void setGeneralScience(GeneralScience generalScience) {

            gc_question.setValue(generalScience.getGs_question());
            gc_answer.setValue(generalScience.getGs_answer());
        }
    }
}
