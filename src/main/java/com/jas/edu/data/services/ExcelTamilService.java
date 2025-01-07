package com.jas.edu.data.services;

import com.jas.edu.data.entity.Tamil;

import java.io.InputStream;
import java.util.List;

public interface ExcelTamilService {
    //List<Tamil> getExcelDataAsList(String file);
    List<Tamil> getExcelDataAsList(InputStream stream);
    int saveExcelData(List<Tamil> scienceguides);
}
