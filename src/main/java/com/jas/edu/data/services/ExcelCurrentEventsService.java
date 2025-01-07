package com.jas.edu.data.services;

import com.jas.edu.data.entity.Currentevents;

import java.io.InputStream;
import java.util.List;

public interface ExcelCurrentEventsService {
    List<Currentevents> getExcelDataAsList(InputStream stream);

    int saveExcelData(List<Currentevents> currenteventss);
}
