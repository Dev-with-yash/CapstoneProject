package com.example.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private ComplianceService complianceService;

    public byte[] generateReport(List<Configuration> configs) throws JRException, IOException {
        try {
            System.out.println("Loading report template...");
            
            InputStream reportStream = getClass().getClassLoader().getResourceAsStream("report_template.jrxml");
            if (reportStream == null) {
                throw new JRException("Report template not found in classpath");
            }
            
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
            System.out.println("Report template compiled.");
            
            for (Configuration config : configs) {
                List<String> nonCompliantRules = complianceService.analyzeCompliance(config);
                config.setComplianceStatus(String.join(", ", nonCompliantRules));
            }
            
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
