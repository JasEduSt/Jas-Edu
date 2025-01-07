package com.jas.edu.data.services;

import com.jas.edu.data.entity.Chapter;
import com.jas.edu.data.entity.Standard;

import java.io.InputStream;
import java.util.List;

public interface ExcelChapterService {
    //List<Chapter> getExcelDataAsList(String file);
    List<Chapter> getExcelDataAsList(InputStream stream);
    int saveExcelData(List<Chapter> chapters);
}