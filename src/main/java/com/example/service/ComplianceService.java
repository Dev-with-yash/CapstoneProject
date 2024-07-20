package com.example.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.ComplianceRule;
import com.example.model.Configuration;

@Service
public class ComplianceService {
	 private List<ComplianceRule> rules;
	    @Autowired
	    private AlertService alertService;
	 
	
	   public ComplianceService() {
	        this.rules = new ArrayList<>();
	    }

	    public ComplianceService(List<ComplianceRule> rules) {
	        this.rules = rules != null ? rules : new ArrayList<>();
	    }
	  
	 public List<String> analyzeCompliance(Configuration config) {
	 List<String> nonCompliantRules = new ArrayList<>();
	 
	 for (ComplianceRule rule : rules) {
		 if (!rule.evaluate(config)) {
			 nonCompliantRules.add(rule.getRuleId()+" : "+rule.getDescription());
			 alertService.sendAlert("Compliance violation detected: " + rule.getDescription(), config.getDeviceId());
		 }
		 
	 }
	 return nonCompliantRules;
	 }
	 
	 public void addRule(ComplianceRule rule) {
	        rules.add(rule);
	 }
}
