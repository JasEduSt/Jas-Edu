package com.jas.edu.data.services;

import com.jas.edu.data.entity.Category;

import java.io.InputStream;
import java.util.List;

public interface ExcelCategoryService {
    //List<Category> getExcelDataAsList(String file);
    List<Category> getExcelDataAsList(InputStream stream);
    int saveExcelData(List<Category> categories);
}
