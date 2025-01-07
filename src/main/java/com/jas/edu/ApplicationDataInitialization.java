package com.jas.edu;

import com.jas.edu.data.entity.*;
import com.jas.edu.data.services.*;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * This init listener is run once whenever the Vaadin context starts. As such,
 * it is a great place to create dummy data.
 * <p>
 * See the <code>application.properties</code> file for database connection
 * properties.
 */
@Service
public class ApplicationDataInitialization implements VaadinServiceInitListener {

    private final PartService partService;
    private final StandardService standardService;
    private final CategoryService categoryService;
    private final ChapterService chapterService;
    private final TamilService tamilService;
    private final TnpscnsService tnpscnsService;
    private final GeneralScienceService tnpscgsService;
    private final CurrentEventsService currentEventsService;

    public ApplicationDataInitialization(PartService partService, StandardService standardService, CategoryService categoryService, ChapterService chapterService, TamilService tamilService, TnpscnsService tnpscnsService, GeneralScienceService tnpscgsService, CurrentEventsService currentEventsService) {
        this.partService = partService;
        this.standardService = standardService;
        this.categoryService = categoryService;
        this.chapterService = chapterService;
        this.tamilService = tamilService;
        this.tnpscnsService = tnpscnsService;
        this.tnpscgsService = tnpscgsService;
        this.currentEventsService = currentEventsService;
    }

    @Override
    public void serviceInit(ServiceInitEvent serviceInitEvent) {
        System.out.println(JConstants.DBSTART);

        try {
            populateData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(JConstants.DBEND);
    }

    private void populateData() throws FileNotFoundException , IOException {

        //String partfilelocation = "D:/PEPSILJAS/jas-edu/src/main/resources/part.xlsx";
        //String standardfilelocation = "D:/PEPSILJAS/jas-edu/src/main/resources/standard.xlsx";
        //String categoryfilelocation = "D:/PEPSILJAS/jas-edu/src/main/resources/category.xlsx";
        //String chapterfilelocation = "D:/PEPSILJAS/jas-edu/src/main/resources/chapter.xlsx";
        //String tamilfilelocation = "D:/PEPSILJAS/jas-edu/src/main/resources/tamil.xlsx";
        //String tnpscnsfilelocation = "D:/PEPSILJAS/jas-edu/src/main/resources/tnpscns.xlsx";

        InputStream partStream= getClass().getClassLoader().getResourceAsStream("part.xlsx");
        List<Part> excelDataPartAsList = partService.getExcelDataAsList(partStream);

        /*ClassPathResource partResource = new ClassPathResource("part.xlsx");
        File partFile = partResource.getFile();
        String partfilelocation = partFile.toString();*/

        InputStream standardStream= getClass().getClassLoader().getResourceAsStream("standard.xlsx");
        List<Standard> excelDataStandardAsList = standardService.getExcelDataAsList(standardStream);

        /*ClassPathResource standardResource = new ClassPathResource("standard.xlsx");
        File standardFile = standardResource.getFile();
        String standardfilelocation = standardFile.toString();*/

        InputStream categoryStream= getClass().getClassLoader().getResourceAsStream("category.xlsx");
        List<Category> excelDataCategoryAsList = categoryService.getExcelDataAsList(categoryStream);

        /*ClassPathResource categoryResource = new ClassPathResource("category.xlsx");
        File categoryFile = categoryResource.getFile();
        String categoryfilelocation = categoryFile.toString();*/

        InputStream chapterStream= getClass().getClassLoader().getResourceAsStream("chapter.xlsx");
        List<Chapter> excelDataChapterAsList = chapterService.getExcelDataAsList(chapterStream);

        /*ClassPathResource chapterResource = new ClassPathResource("chapter.xlsx");
        File chapterFile = chapterResource.getFile();
        String chapterfilelocation = chapterFile.toString();*/

        InputStream tamilStream= getClass().getClassLoader().getResourceAsStream("tamil.xlsx");
        List<Tamil> excelDataTamilAsList = tamilService.getExcelDataAsList(tamilStream);

        /*ClassPathResource tamilResource = new ClassPathResource("tamil.xlsx");
        File tamilFile = tamilResource.getFile();
        String tamilfilelocation = tamilFile.toString();*/

        InputStream tnpscnsStream= getClass().getClassLoader().getResourceAsStream("tnpscns.xlsx");
        List<Tnpscns> excelDataTnpscnsList = tnpscnsService.getExcelDataAsList(tnpscnsStream);

        /*ClassPathResource tnpscnsResource = new ClassPathResource("tnpscns.xlsx");
        File tnpscnsFile = tnpscnsResource.getFile();
        String tnpscnsfilelocation = tnpscnsFile.toString();*/


        //List<Part> excelDataPartAsList = partService.getExcelDataAsList(partfilelocation.toString());
        int noOfRecordspt = partService.saveExcelData(excelDataPartAsList);
        System.out.println(JConstants.DBPART + noOfRecordspt + JConstants.AVAILABLE);

        //List<Standard> excelDataStandardAsList = standardService.getExcelDataAsList(standardfilelocation);
        int noOfRecordsstd = standardService.saveExcelData(excelDataStandardAsList);
        System.out.println(JConstants.DBSTD + noOfRecordsstd + JConstants.AVAILABLE);

        //List<Category> excelDataCategoryAsList = categoryService.getExcelDataAsList(categoryfilelocation);
        int noOfRecordscat = categoryService.saveExcelData(excelDataCategoryAsList);
        System.out.println(JConstants.DBCAT + noOfRecordscat + JConstants.AVAILABLE);

        //List<Chapter> excelDataChapterAsList = chapterService.getExcelDataAsList(chapterfilelocation);
        int noOfRecordsch = chapterService.saveExcelData(excelDataChapterAsList);
        System.out.println(JConstants.DBCHAPTER + noOfRecordsch + JConstants.AVAILABLE);

        //List<Tamil> excelDataTamilAsList = tamilService.getExcelDataAsList(tamilfilelocation);
        int noOfRecordstamil = tamilService.saveExcelData(excelDataTamilAsList);
        System.out.println(JConstants.DBTAMIL + noOfRecordstamil + JConstants.AVAILABLE);

        //List<Tnpscns> excelDataTnpscnsList = tnpscnsService.getExcelDataAsList(tnpscnsfilelocation);
        int noOfRecordstnpscns = tnpscnsService.saveExcelData(excelDataTnpscnsList);
        System.out.println(JConstants.DBTNNS + noOfRecordstnpscns + JConstants.AVAILABLE);

        InputStream generalScienceStream = getClass().getClassLoader().getResourceAsStream("generalscience.xlsx");
        List<GeneralScience> excelDataScienceAsList = tnpscgsService.getExcelDataAsList(generalScienceStream);
        int noOfRecordstnpscgs = tnpscgsService.saveExcelData(excelDataScienceAsList);
        System.out.println(JConstants.DBTNGS + noOfRecordstnpscgs + JConstants.AVAILABLE);

        InputStream currentEventsStream = getClass().getClassLoader().getResourceAsStream("currentevents.xlsx");
        List<Currentevents> excelDataCurrentEventsAsList = currentEventsService.getExcelDataAsList(currentEventsStream);
        int noOfRecordscurrentevents = currentEventsService.saveExcelData(excelDataCurrentEventsAsList);
        System.out.println(JConstants.DBCE + noOfRecordscurrentevents + JConstants.AVAILABLE);

    }

}
