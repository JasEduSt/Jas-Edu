package com.jas.edu.data.services;

import com.jas.edu.data.entity.Tnpscns;

import java.io.InputStream;
import java.util.List;

public interface ExcelTnpscnsService {

    //List<Tnpscns> getExcelDataAsList(String file);
    List<Tnpscns> getExcelDataAsList(InputStream stream);
    int saveExcelData(List<Tnpscns> parts);
}
