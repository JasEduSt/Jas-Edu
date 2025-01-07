package com.jas.edu.data.services;

import com.jas.edu.data.entity.Category;
import com.jas.edu.data.repository.CategoryRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ExcelCategoryService{
    private final CategoryRepository categoryRepository;
    Workbook workbook;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    private List<Category> createList(List<String> excelData, int noOfColumns) {
        ArrayList<Category> catList = new ArrayList<Category>();

        int i = noOfColumns;
        do {
            Category cat = new Category();

            cat.setCat_id(excelData.get(i));
            cat.setCategory_name(excelData.get(i + 1));
            //cat.setPart_id(excelData.get(i + 2));
            catList.add(cat);
            i = i + (noOfColumns);

        } while (i < excelData.size());
        return catList;
    }

    @Override
    public int saveExcelData(List<Category> categories) {
        categories = categoryRepository.saveAll(categories);
        return categories.size();
    }
    @Override
    public List<Category> getExcelDataAsList(InputStream file) {
        List<String> list = new ArrayList<String>();
        DataFormatter dataFormatter = new DataFormatter();
        try{
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String SHEET = "category";
        Sheet sheet = workbook.getSheet(SHEET);
        int noOfColumns = sheet.getRow(0).getLastCellNum();
        for (Row row : sheet) {
            for (Cell cell : row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                list.add(cellValue);
            }
        }
        List<Category> catList = createList(list, noOfColumns);
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return catList;
    }

}

