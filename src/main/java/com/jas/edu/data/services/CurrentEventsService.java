package com.jas.edu.data.services;

import com.jas.edu.data.entity.Currentevents;
import com.jas.edu.data.entity.GeneralScience;
import com.jas.edu.data.repository.CurrentEventsrepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CurrentEventsService implements ExcelCurrentEventsService{
    @Autowired
    private final CurrentEventsrepository currentEventsrepository;
    Workbook workbook;

    public CurrentEventsService(CurrentEventsrepository currentEventsrepository) {
        this.currentEventsrepository = currentEventsrepository;
    }

    @Override
    public List<Currentevents> getExcelDataAsList(InputStream cestream) {
        List<String> list = new ArrayList<String>();
        DataFormatter dataFormatter = new DataFormatter();
        try{
            workbook = new XSSFWorkbook(cestream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String SHEET = "CurrentEvents";
        Sheet sheet = workbook.getSheet(SHEET);
        int noOfColumns = sheet.getRow(0).getLastCellNum();
        for (Row row : sheet) {
            for (Cell cell : row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                list.add(cellValue);
            }
        }
        List<Currentevents> ceList = createList(list, noOfColumns);
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ceList;
    }

    private List<Currentevents> createList(List<String> excelData, int noOfColumns) {

        ArrayList<Currentevents> ceList = new ArrayList<Currentevents>();

        int i = noOfColumns;
        do {
            Currentevents ce = new Currentevents();

            ce.setCe_id(Long.valueOf(excelData.get(i)));
            ce.setYearmonth(excelData.get(i + 1));
            ce.setCe_question(excelData.get(i + 2));
            ce.setCe_answer(excelData.get(i + 3));
            ceList.add(ce);
            i = i + (noOfColumns);

        } while (i < excelData.size());
        return ceList;
    }

    @Override
    public int saveExcelData(List<Currentevents> currenteventss) {
        currenteventss = currentEventsrepository.saveAll(currenteventss);
        return currenteventss.size();
    }

    public Page<Currentevents> list(Pageable pageable) {
        return currentEventsrepository.findAll(pageable);
    }
}
