package com.jas.edu.data.services;

import com.jas.edu.data.entity.Part;

import java.io.InputStream;
import java.util.List;

public interface ExcelPartService {
    //List<Part> getExcelDataAsList(String file);
    List<Part> getExcelDataAsList(InputStream stream);

    int saveExcelData(List<Part> parts);
}