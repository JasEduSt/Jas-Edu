package com.jas.edu.views.tamildetail;

import com.jas.edu.JConstants;
import com.jas.edu.data.entity.Tamil;
import com.jas.edu.data.services.TamilService;
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
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.data.domain.PageRequest;

import java.util.stream.Stream;

import static com.vaadin.flow.spring.data.VaadinSpringDataHelpers.toSpringDataSort;

@AnonymousAllowed
//@PermitAll
@PageTitle(JConstants.TAMILHEADER)
@Route(value = JConstants.LTAMIL,layout = MainLayout.class)

@RegisterReflectionForBinding({Tamil.class})

public class GeneralTamilView extends VerticalLayout {

    public static final String VIEW_NAME = "General Tamil";
    TextField searchField = new TextField();

    private final TamilService tamilDetailsViewService;

    public GeneralTamilView(TamilService tamilDetailsViewService) {
        this.tamilDetailsViewService = tamilDetailsViewService;

        Footer footer = new Footer();
        footer.setText(CreateWord.getFooterText());
        footer.addClassName("footer");

        Div wrapper = new Div();
        wrapper.setWidthFull();


        Grid toadd = populateGrid();

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
        Grid<Tamil> grid = new Grid<>(Tamil.class, false);
        grid.setMinHeight("95%");
        //grid.setWidth("95%");
        grid.addColumn(Tamil::getTg_id).setHeader("SL NO").setAutoWidth(true).setFlexGrow(0).setVisible(false);
        grid.addColumn(Tamil::getStandard_value).setHeader("Standard").setSortable(true);
        grid.addColumn(Tamil::getPart_value).setHeader("Part");
        grid.addColumn(Tamil::getChapter_value).setHeader("Chapter");
        grid.addColumn(Tamil::getCategory_name).setHeader("Category");
        grid.addColumn(Tamil::getTg_question).setHeader("Question");
        grid.addColumn(Tamil::getTg_answer).setHeader("Answer");

        grid.setItemDetailsRenderer(createGeneralTamilRenderer());

        grid.setItems(query -> tamilDetailsViewService.list(
                        PageRequest.of(query.getPage(), query.getPageSize(), toSpringDataSort(query)))
                .stream());


        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.addThemeVariants(GridVariant.LUMO_COMPACT);
        //layout.add(grid);
        return grid;

    }

    private static ComponentRenderer<GeneralTamilView.GeneralTamilDetailsFormLayout, Tamil> createGeneralTamilRenderer() {
        return new ComponentRenderer<>(GeneralTamilView.GeneralTamilDetailsFormLayout::new,
                GeneralTamilView.GeneralTamilDetailsFormLayout::setGeneralTamil);
    }

    private static class GeneralTamilDetailsFormLayout extends FormLayout {
        //private final TextArea gt_category = new TextArea("Category");
        private final TextArea gt_question = new TextArea("Question");
        private final TextArea gt_answer = new TextArea("Answer");

        public GeneralTamilDetailsFormLayout() {
            Stream.of(gt_question,gt_answer).forEach(field -> {
                field.setReadOnly(true);
                add(field);
            });

        }

        public void setGeneralTamil(Tamil tamil) {
            //gt_category.setValue(tamil.getCategory_name());
            gt_question.setValue(tamil.getTg_question());
            gt_answer.setValue(tamil.getTg_answer());
        }

}}
