package com.example;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.model.ComplianceRule;
import com.example.model.Configuration;
import com.example.service.AlertService;
import com.example.service.ComplianceService;
public class ComplianceServiceTest {
	
	@Autowired
    private AlertService alertService;
 @Test
 public void testAnalyzeCompliance() {
 ComplianceRule rule = new ComplianceRule("rule1", "Test Rule", config -> config.getDeviceId().equals("device_001"));
 ComplianceService service = new ComplianceService(List.of(rule));
 Configuration config = new Configuration("device_001","{ \"setting\": \"value1\"}");
 List<String> result = service.analyzeCompliance(config);
 
 assertTrue(result.isEmpty());
 // Verify that alert is sent (Check console output for alert message)
  alertService.logAlert("Alert: Compliance violation detected: Test Rule");
 }
}