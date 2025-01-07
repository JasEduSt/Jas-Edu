package com.jas.edu.views;

import com.jas.edu.JConstants;
import com.jas.edu.data.entity.Chapter;
import com.jas.edu.data.entity.GeneralScience;
import com.jas.edu.data.entity.Standard;
import com.jas.edu.data.entity.Tamil;
import com.jas.edu.data.services.ChapterService;
import com.jas.edu.data.services.GeneralScienceService;
import com.jas.edu.data.services.StandardService;
import com.jas.edu.data.services.TamilService;
import com.jas.edu.utility.CreateWord;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.contextmenu.GridContextMenu;
import com.vaadin.flow.component.grid.contextmenu.GridMenuItem;
import com.vaadin.flow.component.grid.contextmenu.GridSubMenu;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import jakarta.annotation.security.PermitAll;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.vaadin.addons.tatu.TabSheet;

import java.util.List;

@PageTitle("Downloads")
@Route(value = "downloadview",layout = MainLayout.class)
@AnonymousAllowed
//@PermitAll
public class DownloadView extends VerticalLayout {

    public static final String VIEW_NAME = "Downloads";
    private final TamilService tamilDetailsViewService;
    private final GeneralScienceService generalScienceService;
    private final StandardService standardService;
    private final ChapterService chapterService;
    TabSheet tabSheet;

        public DownloadView(TamilService tamilDetailsViewService, GeneralScienceService generalScienceService, StandardService standardService, ChapterService chapterService){
            this.tamilDetailsViewService = tamilDetailsViewService;
            this.generalScienceService = generalScienceService;
            this.standardService = standardService;
            this.chapterService = chapterService;

            tabSheet = new TabSheet();
            tabSheet.addThemeVariants(TabSheet.TabSheetVariant.LUMO_CENTERED);
            tabSheet.setOrientation(Tabs.Orientation.VERTICAL);
            tabSheet.setWidth("90%");
            tabSheet.setMinHeight("90%");

            tabSheet.addTab("TNPSC Tamil guides Downloads",getTnpsctamilgrid());
            tabSheet.addTab("TNPSC Science guides Downloads",getTnpscsciencegrid());
            tabSheet.addTab("NEET guides Downloads",getNeetgrid());
            tabSheet.addTab("BANKING Guides Downloads",getBankinggrid());
            tabSheet.addTab("RAILWAYS Guides Downloads",getRailwaysgrid());
            tabSheet.addTab("LIC Guides Downloads",getLicgrid());
            tabSheet.addTab("JEE Guides Downloads",getJeegrid());
            tabSheet.addTab("Other Downloads",getOthersgrid());

            Footer footer = new Footer();
            footer.setText(CreateWord.getFooterText());
            footer.addClassName("footer");

            this.add(tabSheet, footer);
    }

    private Component getOthersgrid() {
        VerticalLayout layout = new VerticalLayout();
        layout.add("IN OTHERS");
        return layout;
    }

    private Component getJeegrid() {
        VerticalLayout layout = new VerticalLayout();
        layout.add("IN JEE");
        return layout;
    }

    private Component getLicgrid() {
        VerticalLayout layout = new VerticalLayout();
        layout.add("IN LIC");
        return layout;
    }

    private Component getRailwaysgrid() {
        VerticalLayout layout = new VerticalLayout();
        layout.add("IN RAILWAYS");
        return layout;
    }

    private Component getBankinggrid() {
        VerticalLayout layout = new VerticalLayout();
        layout.add("IN BANKING");
        return layout;
    }

    private Component getNeetgrid() {
        VerticalLayout layout = new VerticalLayout();
        layout.add("IN NEET");
        return layout;
    }

    private Component getTnpsctamilgrid() {

            Grid<Chapter> gridc = new Grid<>(Chapter.class, false);

            VerticalLayout layout = new VerticalLayout();

            //tabSheet.setCaption(tabSheet.getTab(0),"Download Tamil documents Chapterwise :");

            H2 hc= new H2("Download Tamil documents Chapterwise");

            ComboBox<Standard> comboBox = new ComboBox<>("Choose Standard");
            comboBox.setItems(standardService.listStandards());
            comboBox.setItemLabelGenerator(Standard::getStandard_value);
            comboBox.getStyle().set("--vaadin-combo-box-overlay-width", "16em");
            comboBox.getStyle().set("--vaadin-combo-box-overlay-width", "450px");

            comboBox.addValueChangeListener(e -> {
                Standard std  = e.getValue();
                String selectedStd = std.getStandard_value();
                String selectedId = std.getStd_id();
                if(std != null){
                    updateGrid(gridc, selectedId);
                }
            });
            gridc.addColumn("ch_id").setHeader(JConstants.SLNO).setSortable(false);
            gridc.addColumn("chapter_value").setHeader(JConstants.CHAPTER);
            gridc.setMinWidth("70%");
            gridc.setMinHeight("90%");

            layout.add( hc,comboBox,gridc);
            return  layout;
    }

    private Component getTnpscsciencegrid() {
        //Grid<GeneralScience> gridgc = new Grid<>(GeneralScience.class, false);
        VerticalLayout layout = new VerticalLayout();
        //tabSheet.setCaption(tabSheet.getTab(0),"Download Science documents Chapterwise :");
        H2 hc= new H2("Download Science documents Chapterwise");
        ComboBox cb = new ComboBox("");
        cb.setItems(generalScienceService.getChapters());

        cb.addValueChangeListener(e -> {
            //GeneralScience scienceSelected = e.getValue();

            String scienceSelected = e.getValue().toString();
            //Long selectedId = science.getGs_id();
            Notification.show(scienceSelected );
            List<GeneralScience> chapterDown = generalScienceService.getAllByName(scienceSelected);
            String filename = scienceSelected + ".docx";
            CreateWord.generateScienceWord(filename,chapterDown,scienceSelected);

        });
        layout.add(hc,cb);
        return  layout;

    }
    private Grid updateGrid(Grid grid, String id){
        grid.setItems(query -> chapterService.listChapters(
                        PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)),id)
                .stream());

        GridContextMenu<Chapter> cmenu = grid.addContextMenu();
        GridMenuItem<Chapter> cdownload = cmenu.addItem("Download");
        GridSubMenu<Chapter> cdownloadSubMenu = cdownload.getSubMenu();
        cdownloadSubMenu.addItem("Microsoft Word (.docx)", event -> {
            Chapter selected = event.getItem().get();
            String selectedChapter = selected.getChapter_value();
            String filename = selectedChapter + ".docx";
            String selectedId = selected.getCh_id();
            Notification.show(String.valueOf(selectedId));
            //List<Tamil> chapterRes = (List<Tamil>) tamilDetailsViewService.getChaptersById(selectedId);
            List<Tamil> chapterRes = tamilDetailsViewService.getChaptersById(selectedId);
            if(chapterRes.isEmpty())
                Notification.show("No data available to download for the selected chapter");
            else {
                CreateWord.generateWord(filename, chapterRes, selectedChapter);
                Notification.show(String.valueOf(chapterRes.size()));
            }

        });

            return grid;
    }

}