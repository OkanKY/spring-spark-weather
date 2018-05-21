package com.okanky.weather.stream.excell.impl;

import com.okanky.weather.stream.excell.ExcelService;
import com.okanky.weather.stream.model.WeatherData;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {
    private static final String FILE_NAME = "src\\main\\resources\\temp\\veri.xlsx";

    @Override
    public List<WeatherData> getDataFromExcell() {
        List<WeatherData> dataList = new ArrayList<>();
        try {
            FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            if (iterator.hasNext()) {
                iterator.next();
                while (iterator.hasNext()) {
                    dataList.add(createModel(iterator.next()));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataList;
    }

    /**
     *
     * @param currentRow
     * @return
     */
    private WeatherData createModel(Row currentRow) {
        WeatherData weatherData = new WeatherData(currentRow.getCell(0).getStringCellValue(), currentRow.getCell(1).getStringCellValue(), currentRow.getCell(2).getStringCellValue(), currentRow.getCell(3).getStringCellValue(), currentRow.getCell(4).getStringCellValue());
        return weatherData;
    }

}
