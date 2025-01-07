package com.jas.edu.data.services;

import com.jas.edu.data.entity.Category;
import com.jas.edu.data.entity.GeneralScience;

import java.io.InputStream;
import java.util.List;

public interface ExcelScienceService {
    List<GeneralScience> getExcelDataAsList(InputStream stream);
    int saveExcelData(List<GeneralScience> categories);
}
