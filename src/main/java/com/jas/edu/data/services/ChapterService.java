package com.jas.edu.data.services;

import com.jas.edu.data.entity.Chapter;
import com.jas.edu.data.entity.Standard;
import com.jas.edu.data.repository.ChapterRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChapterService implements ExcelChapterService{
    private final ChapterRepository chapterRepository;
    Workbook workbook;

    public ChapterService(ChapterRepository chapterRepository) {
        this.chapterRepository = chapterRepository;
    }
    public Page<Chapter> list(Pageable pageable) {
        return chapterRepository.findAll(pageable);
    }
    public Page<Chapter> listChapters(Pageable pageable, String Stdid){
        return  chapterRepository.findById(pageable, Stdid);
    }

    /*public void saveChapter(String file) {
        List<Chapter> chapter = ExcelHelperChapter.excelToTable(file);
        chapterRepository.saveAll(chapter);
    }*/

    /*@Override
    public List<Chapter> getExcelDataAsList(String file) {
        List<String> list = new ArrayList<String>();
        DataFormatter dataFormatter = new DataFormatter();
        try{
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String SHEET = "chapter";
        Sheet sheet = workbook.getSheet(SHEET);
        int noOfColumns = sheet.getRow(0).getLastCellNum();
        for (Row row : sheet) {
            for (Cell cell : row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                list.add(cellValue);
            }
        }
        List<Chapter> chList = createList(list, noOfColumns);
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chList;
    }*/

    private List<Chapter> createList(List<String> excelData, int noOfColumns) {

        ArrayList<Chapter> chList = new ArrayList<Chapter>();

        int i = noOfColumns;
        do {
            Chapter ch = new Chapter();

            ch.setCh_id(excelData.get(i));
            ch.setChapter_value(excelData.get(i + 1));
            ch.setStd_id(excelData.get(i+2));
            chList.add(ch);
            i = i + (noOfColumns);

        } while (i < excelData.size());
        return chList;
    }

    @Override
    public int saveExcelData(List<Chapter> chapters) {
        chapters = chapterRepository.saveAll(chapters);
        return chapters.size();
    }

    @Override
    public List<Chapter> getExcelDataAsList(InputStream file) {
        List<String> list = new ArrayList<String>();
        DataFormatter dataFormatter = new DataFormatter();
        try{
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String SHEET = "chapter";
        Sheet sheet = workbook.getSheet(SHEET);
        int noOfColumns = sheet.getRow(0).getLastCellNum();
        for (Row row : sheet) {
            for (Cell cell : row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                list.add(cellValue);
            }
        }
        List<Chapter> chList = createList(list, noOfColumns);
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chList;
    }
}

