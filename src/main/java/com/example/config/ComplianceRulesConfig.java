package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.example.model.ComplianceRule;
import com.example.service.ComplianceService;

import jakarta.annotation.PostConstruct;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.regex.Pattern;


@Configuration
public class ComplianceRulesConfig {

    @Autowired
    private ComplianceService complianceService;

    @PostConstruct
    public void init() {
    	ObjectMapper objectMapper = new ObjectMapper();
        // Example rule
    	ComplianceRule rule1 = new ComplianceRule("rule1", "Device ID numeric part should be greater than 1", 
    		    config -> {
    		        String deviceId = config.getDeviceId();
    		        String numericPart = deviceId.substring(deviceId.indexOf('_') + 1);
    		        return Integer.parseInt(numericPart) > 1;
    		    }
    		);
    		complianceService.addRule(rule1);

        
        ComplianceRule rule2 = new ComplianceRule("Ipaddress rule", "IP address should be in a valid format", 
        	    config -> {
        	        try {
        	            JsonNode configData = objectMapper.readTree(config.getConfigData());
        	            String ipAddress = configData.get("ipAddress").asText();
        	            String ipPattern = "^([0-9]{1,3}\\.){3}[0-9]{1,3}$";
        	            return Pattern.matches(ipPattern, ipAddress);
        	        } catch (Exception e) {
        	            e.printStackTrace();
        	            return false;
        	        }
        	    });
        	complianceService.addRule(rule2);
        	ComplianceRule rule3 = new ComplianceRule("subnetRule", "Subnet mask should be '255.255.255.0'", 
        		    config -> {
        		        try {
        		            JsonNode configData = objectMapper.readTree(config.getConfigData());
        		            return configData.get("subnetMask").asText().equals("255.255.255.0");
        		        } catch (Exception e) {
        		            e.printStackTrace();
        		            return false;
        		        }
        		    });
        		complianceService.addRule(rule3);
        		
        		ComplianceRule rule4 = new ComplianceRule("GatewayRule", "Gateway should be '10.10.3.1'", 
        			    config -> {
        			        try {
        			            JsonNode configData = objectMapper.readTree(config.getConfigData());
        			            return configData.get("gateway").asText().equals("10.10.3.1");
        			        } catch (Exception e) {
        			            e.printStackTrace();
        			            return false;
        			        }
        			    });
        		complianceService.addRule(rule4);
        		
        		ComplianceRule passwordRule = new ComplianceRule("passwordRule", "Password should be greater than 6 characters and match the pattern", 
        			    config -> {
        			        try {
        			            JsonNode configData = objectMapper.readTree(config.getConfigData());
        			            String password = configData.get("password").asText();
        			            // Password pattern: at least one uppercase letter, one lowercase letter, one digit, and one special character
        			            String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{7,}$";
        			            return password.length() > 6 && Pattern.matches(passwordPattern, password);
        			        } catch (Exception e) {
        			            e.printStackTrace();
        			            return false;
        			        }
        			    });
        		complianceService.addRule(passwordRule);
        		
        		

       
    }
}