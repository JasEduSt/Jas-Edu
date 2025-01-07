package com.jas.edu.data.services;

import com.jas.edu.data.entity.Part;
import com.jas.edu.data.repository.PartRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class PartService implements ExcelPartService{
    @Autowired
    private final PartRepository partRepository;
    Workbook workbook;

    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    private List<Part> createList(List<String> excelData, int noOfColumns) {

        ArrayList<Part> ptList = new ArrayList<Part>();

        int i = noOfColumns;
        do {
            Part pt = new Part();

            pt.setPart_id(excelData.get(i));
            pt.setPart_value(excelData.get(i + 1));
            ptList.add(pt);
            i = i + (noOfColumns);

        } while (i < excelData.size());
        return ptList;
    }
    @Override
    public int saveExcelData(List<Part> parts) {
        parts = partRepository.saveAll(parts);
        return parts.size();
    }
    @Override
    public List<Part> getExcelDataAsList(InputStream partStream) {
        List<String> list = new ArrayList<String>();
        DataFormatter dataFormatter = new DataFormatter();
        try{
            workbook = new XSSFWorkbook(partStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String SHEET = "Part";
        Sheet sheet = workbook.getSheet(SHEET);
        int noOfColumns = sheet.getRow(0).getLastCellNum();
        for (Row row : sheet) {
            for (Cell cell : row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                list.add(cellValue);
            }
        }
        List<Part> ptList = createList(list, noOfColumns);
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ptList;}
}
