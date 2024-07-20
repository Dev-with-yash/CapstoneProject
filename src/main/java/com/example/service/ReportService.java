package com.example.service;
import java.io.IOException;
//import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.model.Configuration;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService {
    
    public byte[] generateReport(List<Configuration> configs) throws JRException, IOException {

        try {
        	String reportTemplatePath = "C:\\Users\\akasa\\Desktop\\Capstone\\report_template.jrxml";
            System.out.println("Loading report template...");
            
            JasperReport jasperReport = JasperCompileManager.compileReport(reportTemplatePath);
            System.out.println("Report template compiled.");
            
           

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(configs);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);
            System.out.println("Report data filled.");

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            System.err.println("Error generating report: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}




// Convert configurations to a map with formatted JSON

//List<Map<String, Object>> dataSourceList = new ArrayList<>();
//for (Configuration config : configs) {
//  Map<String, Object> dataSourceMap = new HashMap<>();
//  dataSourceMap.put("deviceId", config.getDeviceId());
//  dataSourceMap.put("configData", config.getConfigData());
//  dataSourceMap.put("version", config.getVersion());
//  dataSourceMap.put("createdAt", config.getCreatedAt());
//  dataSourceList.add(dataSourceMap);
//}
