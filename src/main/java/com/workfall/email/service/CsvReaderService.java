package com.workfall.email.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;

@Service
public class CsvReaderService {

    public List<String[]> readCsv() {
    	
        List<String[]> records = new ArrayList<>();
        
        InputStream inputStream = getClass().getResourceAsStream("/emails.csv");
        
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream))) {
            String[] values;
            boolean skipHeader = true;
            while ((values = csvReader.readNext()) != null) {
                if (skipHeader) { 
                    skipHeader = false; 
                    continue; 
                }
                records.add(values);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }
}