package com.jas.edu.data.services;

import com.jas.edu.data.entity.Tamil;
import com.jas.edu.data.repository.TamilRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.apache.poi.ss.usermodel.*;

@Service
public  class TamilService implements ExcelTamilService{

    private final TamilRepository tamilRepository;
    Workbook workbook;
    public TamilService(TamilRepository tamilRepository){
        this.tamilRepository = tamilRepository;
    }
    public Optional<Tamil> get(Long id) {
        return tamilRepository.findById(id);
    }
    public Tamil update(Tamil entity) {
        return tamilRepository.save(entity);
    }
    public void delete(Long id) {
        tamilRepository.deleteById(id);
    }
    /*public Page<Tamil> list(Pageable pageable) {
        return tamilRepository.findAllGuides(pageable);
    }*/

    public Page<Tamil> list(Pageable pageable) {
        return tamilRepository.findAllGuides(pageable);
    }
    public List<Tamil> findAll(){
        return tamilRepository.findAllGuides();
    }
    public Optional<Tamil> findById(Long id) {
        return tamilRepository.findById(id);
    }
    public List<Tamil> getChaptersById(String id) {return tamilRepository.findChapters(id);}
    public int count() {
        return (int) tamilRepository.count();
    }

    private List<Tamil> createList(List<String> excelData, int noOfColumns) {
        ArrayList<Tamil> tgList = new ArrayList<Tamil>();

        int i = noOfColumns;
        do {
            Tamil tg = new Tamil();
            tg.setTg_id(Long.valueOf(excelData.get(i)));
            tg.setTg_question(excelData.get(i + 1));
            tg.setTg_answer(excelData.get(i + 2));
            tg.setCat_id(excelData.get(i + 3));
            tg.setCh_id(excelData.get(i + 4));
            tg.setStd_id(excelData.get(i + 5));
            tg.setPart_id(excelData.get(i + 6));
            tgList.add(tg);
            i = i + (noOfColumns);

        } while (i < excelData.size());
        return tgList;
    }

    @Override
    public int saveExcelData(List<Tamil> tamilguides) {
        tamilguides = tamilRepository.saveAll(tamilguides);
        return tamilguides.size();
    }

    @Override
    public List<Tamil> getExcelDataAsList(InputStream file) {
        List<String> list = new ArrayList<String>();
        DataFormatter dataFormatter = new DataFormatter();
        try{
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String SHEET = "Tamil";
        Sheet sheet = workbook.getSheet(SHEET);
        int noOfColumns = sheet.getRow(0).getLastCellNum();
        for (Row row : sheet) {
            for (Cell cell : row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                list.add(cellValue);
            }
        }
        List<Tamil> tgList = createList(list, noOfColumns);
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tgList;
    }

}
