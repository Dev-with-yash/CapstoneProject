package com.example;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.example.model.Configuration;
import com.example.service.ReportService;

import net.sf.jasperreports.engine.JRException;

public class ReportServiceTest {
 @Test
 public void testGenerateReport() throws JRException, IOException {
 ReportService service = new ReportService();
 List<Configuration> configs = List.of(new Configuration("device_001","{ \"mode\": \"auto\", \"threshold\": 75, \"enabledFeatures\": [\"feature1\", \"feature2\"] }"));
 
 byte[] report = service.generateReport(configs);
 assertNotNull(report);
 }
}