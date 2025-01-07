package com.jas.edu.data.services;

import com.jas.edu.data.entity.Standard;
import com.jas.edu.data.entity.Tamil;
import com.jas.edu.data.repository.StandardRepository;
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
public class StandardService implements ExcelStandardService{
    private final StandardRepository standardRepository;

    Workbook workbook;

    public StandardService(StandardRepository standardRepository) {
        this.standardRepository = standardRepository;
    }
    public Page<Standard> list(Pageable pageable) {
        return standardRepository.findAll(pageable);
    }
    public List<Standard> listStandards(){
        return  standardRepository.findAll();
    }

    /*public void saveStandard(String file) {
        List<Standard> standard = ExcelHelperStandard.excelToTable(file);
        standardRepository.saveAll(standard);
    }*/

    /*@Override
    public List<Standard> getExcelDataAsList(String file) {
        List<String> list = new ArrayList<String>();
        DataFormatter dataFormatter = new DataFormatter();
        try{
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String SHEET = "Standard";
        Sheet sheet = workbook.getSheet(SHEET);
        int noOfColumns = sheet.getRow(0).getLastCellNum();
        for (Row row : sheet) {
            for (Cell cell : row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                list.add(cellValue);
            }
        }
        List<Standard> stdlist = createList(list, noOfColumns);
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stdlist;
    }*/
    private List<Standard> createList(List<String> excelData, int noOfColumns) {

        ArrayList<Standard> stdList = new ArrayList<Standard>();

        int i = noOfColumns;
        do {
            Standard std = new Standard();
            std.setStd_id(excelData.get(i));
            std.setStandard_value(excelData.get(i + 1));
            stdList.add(std);
            i = i + (noOfColumns);

        } while (i < excelData.size());
        return stdList;
    }
    @Override
    public int saveExcelData(List<Standard> stds) {
        stds = standardRepository.saveAll(stds);
        return stds.size();
    }
    @Override
    public List<Standard> getExcelDataAsList(InputStream file) {
        List<String> list = new ArrayList<String>();
        DataFormatter dataFormatter = new DataFormatter();
        try{
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String SHEET = "Standard";
        Sheet sheet = workbook.getSheet(SHEET);
        int noOfColumns = sheet.getRow(0).getLastCellNum();
        for (Row row : sheet) {
            for (Cell cell : row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                list.add(cellValue);
            }
        }
        List<Standard> stdlist = createList(list, noOfColumns);
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stdlist;
    }
}

