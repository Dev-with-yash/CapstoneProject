package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.ComplianceRule;
import com.example.model.Configuration;
import com.example.service.ComplianceService;
import com.example.service.ConfigurationService;
import com.example.service.ReportService;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
 @Autowired
 private ComplianceService complianceService;
 @Autowired
 private ReportService reportService;
 @Autowired
 private ConfigurationService configurationService;
 @GetMapping("/compliance")
 public ResponseEntity<List<String>> getComplianceStatus(@RequestParam String deviceId) {
	 
     List<Configuration> configurations = configurationService.getConfigurations(deviceId);
     if (configurations.isEmpty()) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
     }

     Configuration config = configurations.get(0);
     List<String> nonCompliantRules = complianceService.analyzeCompliance(config);
     return ResponseEntity.ok(nonCompliantRules);
 }

 
 @GetMapping("/report")
 public ResponseEntity<byte[]> getComplianceReport() throws JRException, IOException {
	 
	 try {
         List<Configuration> configs = configurationService.getAllConfigurations();
         System.out.println("Number of configurations: " + configs.size());

         if (configs.isEmpty()) {
             System.out.println("No configurations found.");
             return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
         }

         byte[] report = reportService.generateReport(configs);
         System.out.println("Report generated successfully.");
         return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(report);
     } catch (JRException e) {
         System.err.println("JRException: " + e.getMessage());
         e.printStackTrace();
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
     } catch (IOException e) {
         System.err.println("IOException: " + e.getMessage());
         e.printStackTrace();
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
     } catch (Exception e) {
         System.err.println("Exception: " + e.getMessage());
         e.printStackTrace();
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
     }
 }
}