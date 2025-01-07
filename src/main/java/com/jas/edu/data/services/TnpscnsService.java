package com.jas.edu.data.services;

import com.jas.edu.data.entity.Tnpscns;
import com.jas.edu.data.repository.TnpscnsRepository;
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
public class TnpscnsService implements ExcelTnpscnsService{
    private final TnpscnsRepository tnpscnsRepository;
    Workbook workbook;

    public TnpscnsService(TnpscnsRepository tnpscnsRepository) {
        this.tnpscnsRepository = tnpscnsRepository;
    }
    public Page<Tnpscns> list(Pageable pageable) {
        return tnpscnsRepository.findAll(pageable);
    }

    /*@Override
    public List<Tnpscns> getExcelDataAsList(String file) {
        List<String> list = new ArrayList<String>();
        DataFormatter dataFormatter = new DataFormatter();
        try{
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String SHEET = "Tnpscns";
        Sheet sheet = workbook.getSheet(SHEET);
        int noOfColumns = sheet.getRow(0).getLastCellNum();
        for (Row row : sheet) {
            for (Cell cell : row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                list.add(cellValue);
            }
        }
        List<Tnpscns> tnpscnsList = createList(list, noOfColumns);
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tnpscnsList;
    }*/

    private List<Tnpscns> createList(List<String> excelData, int noOfColumns) {

        ArrayList<Tnpscns> tnpscnsList = new ArrayList<Tnpscns>();

        int i = noOfColumns;
        do {
            Tnpscns tnns = new Tnpscns();

            tnns.setTnnotify_id(excelData.get(i));
            tnns.setTnnotify_subject(excelData.get(i + 1));
            tnns.setRegPeriod(excelData.get(i +2));
            tnns.setExamDate(excelData.get(i+3));
            tnns.setUrl(excelData.get(i+4));
            tnpscnsList.add(tnns);
            i = i + (noOfColumns);

        } while (i < excelData.size());
        return tnpscnsList;
    }

    @Override
    public int saveExcelData(List<Tnpscns> tnpscns) {
        tnpscns = tnpscnsRepository.saveAll(tnpscns);
        return tnpscns.size();
    }

    @Override
    public List<Tnpscns> getExcelDataAsList(InputStream file) {
        List<String> list = new ArrayList<String>();
        DataFormatter dataFormatter = new DataFormatter();
        try{
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String SHEET = "Tnpscns";
        Sheet sheet = workbook.getSheet(SHEET);
        int noOfColumns = sheet.getRow(0).getLastCellNum();
        for (Row row : sheet) {
            for (Cell cell : row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                list.add(cellValue);
            }
        }
        List<Tnpscns> tnpscnsList = createList(list, noOfColumns);
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tnpscnsList;
    }

}
