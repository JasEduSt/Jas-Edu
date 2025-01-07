package com.jas.edu.views;

import com.jas.edu.JConstants;
import com.jas.edu.utility.CreateWord;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;


@PageTitle("Home")
@Route(value = "homeview",layout = MainLayout.class)
@AnonymousAllowed
//@PermitAll
//@JsModule("./carousel.js")

public class HomeView extends VerticalLayout {
    public static final String VIEW_NAME = "Home";

    public HomeView() {
        ConfirmDialog dialog = new ConfirmDialog();
        dialog.setHeader(JConstants.DIALOGINFOTITLE);
        dialog.setText(JConstants.DIALOGINFOCONTENTT + '\n' + JConstants.DIALOGINFOCONTENTE);
        dialog.setConfirmText("OK");
        dialog.open();

        setMargin(true);
        setSpacing(true);
        setPadding(true);


        Image welcome = new Image("images/welcome.png", "Welcome to jasedu");
        Image tnpsc = new Image("images/tnpsc.png","Welcome to Tnpsc");
        Image neet = new Image("images/neet.png","Welcome to Neet");
        Image banking = new Image("images/banking.png","Welcome to Banking");
        Image railways = new Image("images/railways.png","Welcome to Railways");
        Image lic = new Image("images/lic.png","Welcome to Lic");
        Image jee = new Image("images/jee.png","Welcome to Jee");

        /*StreamResource imgResource = new StreamResource("welcome.JPG",
                () -> VaadinService.getCurrent().getResourceAsStream("/welcome.JPG"));
        Image welcome = new Image(imgResource,"Welcome to Jasedu");

        StreamResource tnpscResource = new StreamResource("tnpsc.PNG",
                () -> VaadinService.getCurrent().getResourceAsStream("/images/tnpsc.PNG"));
        Image tnpsc = new Image(tnpscResource,"Welcome to Tnpsc");

        StreamResource neetResource = new StreamResource("neet.PNG",
                () -> VaadinService.getCurrent().getResourceAsStream("/images/neet.PNG"));
        Image neet = new Image(neetResource,"Welcome to Neet");

        StreamResource bankingResource = new StreamResource("banking.PNG",
                () -> VaadinService.getCurrent().getResourceAsStream("/images/banking.PNG"));
        Image banking = new Image(bankingResource,"Welcome to Banking");

        StreamResource railwaysResource = new StreamResource("railways.PNG",
                () -> VaadinService.getCurrent().getResourceAsStream("/images/railways.PNG"));
        Image railways = new Image(railwaysResource,"Welcome to Railways");

        StreamResource licResource = new StreamResource("lic.PNG",
                () -> VaadinService.getCurrent().getResourceAsStream("/images/lic.PNG"));
        Image lic = new Image(licResource,"Welcome to Lic");

        StreamResource jeeResource = new StreamResource("jee.PNG",
                () -> getClass().getResourceAsStream("/images/jee.PNG"));
        Image jee = new Image(jeeResource,"Welcome to Jee");*/


        HorizontalLayout container = new HorizontalLayout();
        container.setSpacing(false);
        container.getStyle().set("flex-wrap", "wrap");
        container.setSizeFull();
        container.getStyle().set("padding-left", "2%");
        container.getStyle().set("padding-right", "2%");

        String infoCardHeader = "Info";
        String infoCardContent = "This Web Site is an initiative for succeed in Competetive Examinations . Our platform offers "
                + "best online study materials, video tutorials, previous questions and answers , model questions with answers "
                + "and Online mock examination portal to empower aspiring candidates for various Competetive Examinations like "
                + "TNPSC, NEET, BANKING, LIC, RAILWAYS, JEE and more." ;

        Component infoCard = createinfoCard(infoCardHeader, infoCardContent);

        String tnpscCardHeader = "TNPSC";
        String TNPSCURL = "https://www.tnpsc.gov.in/home.aspx";
        String tnpscCardContent = "Tamil Nadu Public Service Commission[TNPSC] is a Government Department in Tamil Nadu, "
                + " which is responsible for the recruitment of eligible candidates into the State’s Public Service. "
                + " TNPSC conducts Group 1, Group 2, Group 3, Group 4 Examinations annually to recruit candidates for "
                + " various posts under TamilNadu State Government. Group 1 is high profile job posts like District   "
                + " Deputy Collector, Deputy Superintendent of Police, Assistant Commissioner etc. " ;

        Component tnpscCard = createCard(tnpscCardHeader, tnpscCardContent,TNPSCURL);

        String neetCardHeader = "NEET";
        String NEETURL = "https://neet.nta.nic.in";
        String neetCardContent = "National Eligibility cum Entrance Test[NEET] is an Indian  nationwide entrance "
                + " Examination conducted by the National Testing Agency for admission in Undergraduate Medical "
                + " Programs. NEET exam brings Uniform Examination pattern for Admission to Undergraduate Medical "
                + " Courses in Central and State Government and private colleges all over India. NEET Exam brings "
                + " about the uniform admission process to the Medical Colleges in India. " ;

        Component neetCard = createCard(neetCardHeader, neetCardContent,NEETURL);

        String bankingCardHeader = "BANKING";
        String BANKURL = "https://sbi.co.in/web/careers";
        String bankingCardContent = "Indian Government Banking system consists of above 10 public sector banks. "
                + " According to survey Banking Sector is one of the fastest growing  sector in India. Almost "
                + " around 40,000 vacancies have been created in public sector banks this year due to retirements &  "
                + " Business Expansion. Many vacancies exists in Officer Cadre and Clerical Posts . Every  candidates "
                + " has to clear the written examination[CBT] to attend the final Interview process." ;

        Component bankingCard = createCard(bankingCardHeader, bankingCardContent,BANKURL);

        String railwaysCardHeader = "RALIWAYS";
        String RAILURL = "https://www.rrbapply.gov.in";
        String railwaysCardContent = " Indian  Railways is one of the Biggest Organization in India and under the Ministry "
                + "  of Railways. Every year Railway Recruitment Board(RRB) recruits huge  number of Employees to fill the  "
                + " vacant positions in Junior Engineers(JE), Technician, Assistant Loco Pilot, NTPC, Paramedical  Staffs etc. "
                + " The selection process will be mainly based on Written Examination[CBT] followed by Document verification "
                + " and Medical fitness. " ;

        Component railwaysCard = createCard(railwaysCardHeader, railwaysCardContent,RAILURL);

        String licCardHeader = "LIC";
        String LICURL = "https://licindia.in/careers";
        String licCardContent = "Life Insurance Corporation(LIC) is Indian state-owned Insurance Group and Investment "
                + " Company. Every year LIC issues notifications for the recruitment of Assistant Administrative Officer,"
                + " Supervisor, Assistant, IT officer etc. All Exams are two stage process . Prelims and  Main . "
                + " Prelims and Main Exams are in Online Mode[CBT]. LIC Exam pattern and Sellection process  is almost "
                + "  similar to Banking Exams.  " ;

        Component licCard = createCard(licCardHeader, licCardContent,LICURL);

        String jeeCardHeader = "JEE";
        String JEEURL = "https://jeemain.nta.ac.in";
        String jeeCardContent = "Joint Entrance Examinations(JEE) is a National Level entrance exam in India conducted "
                + " for admission to Undergraduate Engineering and Architecture Program in Renowned  institutions like "
                + " IITs, NITs and other top Engineering Colleges across the Country. JEE main paper 1 exam has  "
                + " questions from physics, chemistry and Mathematics. JEE main paper 2  exam has questions from"
                + " Maths, General aptitude and Drawing.";

        Component jeeCard = createCard(jeeCardHeader, jeeCardContent,JEEURL);
        container.add(welcome, infoCard, tnpscCard, tnpsc, neet, neetCard, bankingCard, banking, railways, railwaysCard, licCard, lic, jee, jeeCard);

        Footer footer = new Footer();
        footer.setText(CreateWord.getFooterText());
        footer.addClassName("footer");

        add(container,footer);

    }

    private Component createCard(String cardHeader, String cardContent, String URL) {
        VerticalLayout layout = new VerticalLayout();
        //layout.setWidth("30%");
        layout.setWidth("50%");
        layout.setMinWidth("250px");

        H2 header = new H2(cardHeader);
        Div content = new Div();
        content.setText(cardContent);

        /*Button cardbutton = new Button("View details",
                new Icon(VaadinIcon.ANGLE_DOUBLE_RIGHT));
        cardbutton.addThemeVariants(ButtonVariant.LUMO_SMALL);
        if(cardHeader.equals("TNPSC"))
            cardbutton.addClickListener(event -> cardbutton.getUI().ifPresent(o -> o.navigate("https://www.tnpsc.gov.in/home.aspx")));
        else if(cardHeader.equals("NEET"))
            cardbutton.addClickListener(event -> cardbutton.getUI().ifPresent(o -> o.navigate("https://neet.nta.nic.in")));
        else if(cardHeader.equals("BANKING"))
            cardbutton.addClickListener(event -> cardbutton.getUI().ifPresent(o -> o.navigate("https://sbi.co.in/web/careers")));
        else if(cardHeader.equals("RALIWAYS"))
            cardbutton.addClickListener(event -> cardbutton.getUI().ifPresent(o -> o.navigate("https://www.rrbapply.gov.in")));
        else if(cardHeader.equals("LIC"))
            cardbutton.addClickListener(event -> cardbutton.getUI().ifPresent(o -> o.navigate("https://licindia.in/careers")));
        else if(cardHeader.equals("JEE"))
            cardbutton.addClickListener(event -> cardbutton.getUI().ifPresent(o -> o.navigate("https://jeemain.nta.ac.in")));*/

        Anchor link = new Anchor(URL, "View details");
        link.addClassName(LumoUtility.FontSize.MEDIUM);
        link.setRouterIgnore(true);
        link.setTarget( "_blank" );
        layout.getElement().getStyle().set("flex-grow", "0");
        layout.add(header, content, link);
        return layout;
    }

    private Component createinfoCard(String cardHeader, String cardContent) {
        VerticalLayout layout = new VerticalLayout();
        //layout.setWidth("30%");
        layout.setWidth("100%");
        layout.setMinWidth("250px");

        H2 header = new H2(cardHeader);
        Div content = new Div();
        content.setText(cardContent);

        Button infobutton = new Button("Join for free",
                new Icon(VaadinIcon.USER));
        infobutton.addThemeVariants(ButtonVariant.LUMO_SMALL);
        infobutton.addClickListener(event -> infobutton.getUI().ifPresent(o -> o.navigate("register")));
        layout.getElement().getStyle().set("flex-grow", "0");
        layout.add(header, content, infobutton);
        return layout;
    }
}
