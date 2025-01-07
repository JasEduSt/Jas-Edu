package com.jas.edu.data.services;


import com.jas.edu.data.entity.GeneralScience;
import com.jas.edu.data.entity.Tamil;
import com.jas.edu.data.repository.GeneralScienceRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GeneralScienceService implements ExcelScienceService{
    Workbook workbook;
    private final GeneralScienceRepository generalScienceRepository;

    public GeneralScienceService(GeneralScienceRepository repository){
        this.generalScienceRepository = repository;
    }

    public Optional<GeneralScience> get(Long id) {
        return generalScienceRepository.findById(id);
    }

    public GeneralScience update(GeneralScience entity) {
        return generalScienceRepository.save(entity);
    }

    public void delete(Long id) {
        generalScienceRepository.deleteById(id);
    }

    public Page<GeneralScience> list(Pageable pageable) {
        return generalScienceRepository.findAll(pageable);
    }

    public Page<GeneralScience> list(Pageable pageable, Specification<GeneralScience> filter) {
        return generalScienceRepository.findAll(filter, pageable);
    }

    public List<GeneralScience> getAllByName(String sciencechapter) {return generalScienceRepository.getAllByName(sciencechapter);}

    public int count() {
        return (int) generalScienceRepository.count();
    }

    @Override
    public List<GeneralScience> getExcelDataAsList(InputStream sciencestream) {
        List<String> list = new ArrayList<String>();
        DataFormatter dataFormatter = new DataFormatter();
        try{
            workbook = new XSSFWorkbook(sciencestream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String SHEET = "Science";
        Sheet sheet = workbook.getSheet(SHEET);
        int noOfColumns = sheet.getRow(0).getLastCellNum();
        for (Row row : sheet) {
            for (Cell cell : row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                list.add(cellValue);
            }
        }
        List<GeneralScience> gsList = createList(list, noOfColumns);
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gsList;}

    private List<GeneralScience> createList(List<String> excelData, int noOfColumns) {
        ArrayList<GeneralScience> gsList = new ArrayList<GeneralScience>();

        int i = noOfColumns;
        do {
            GeneralScience gs = new GeneralScience();

            gs.setGs_id(Long.valueOf(excelData.get(i)));
            gs.setGs_chapter(excelData.get(i+1));
            gs.setGs_question(excelData.get(i+2));
            gs.setGs_answer(excelData.get(i+3));
            gsList.add(gs);
            i = i + (noOfColumns);

        } while (i < excelData.size());
        return gsList;
    }
    @Override
    public int saveExcelData(List<GeneralScience> sciences) {
        sciences = generalScienceRepository.saveAll(sciences);
        return sciences.size();
    }

    public List getChapters() {
        List chapters = generalScienceRepository.getChapters();
        return  chapters;
    }
}






