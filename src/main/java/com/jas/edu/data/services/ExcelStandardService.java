package com.jas.edu.data.services;

import com.jas.edu.data.entity.Standard;

import java.io.InputStream;
import java.util.List;

public interface ExcelStandardService {
    //List<Standard> getExcelDataAsList(String file);
    List<Standard> getExcelDataAsList(InputStream stream);
    int saveExcelData(List<Standard> stds);
}

