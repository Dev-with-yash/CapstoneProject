package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.model.Configuration;
import com.example.service.ConfigurationService;

@SpringBootTest
public class ConfigurationServiceTest {
 @Autowired
 private ConfigurationService configurationService;
 
 @Test
 public void testSaveConfiguration() {
	 Configuration config = new Configuration();
     config.setDeviceId("device_2");
     config.setConfigData("{\"gateway\": \"10.10.3.1\", \"password\": \"Askdj@12344\", \"ipAddress\": \"192.168.2.2.1\", \"deviceName\": \"Device 2\", \"dnsServers\": [\"8.8.8.8\", \"8.8.4.4\"], \"subnetMask\": \"0.0.255.255\"}");
     config.setVersion(2);
//     config.setId(123456789L);
     config.setCreatedAt(LocalDateTime.of(2023, 7, 18, 15, 30));
     config.setComplianceStatus("something");

     Configuration savedConfig = configurationService.saveConfiguration(config);
     assertEquals("device_2", savedConfig.getDeviceId());
     assertEquals("{\"gateway\": \"10.10.3.1\", \"password\": \"Askdj@12344\", \"ipAddress\": \"192.168.2.2.1\", \"deviceName\": \"Device 2\", \"dnsServers\": [\"8.8.8.8\", \"8.8.4.4\"], \"subnetMask\": \"0.0.255.255\"}", savedConfig.getConfigData());
     assertEquals(2, savedConfig.getVersion());
//     assertEquals(123456789L, savedConfig.getId());
     assertEquals(LocalDateTime.of(2023, 7, 18, 15, 30), savedConfig.getCreatedAt());
     assertEquals("something", savedConfig.getComplianceStatus());
    
}
 }
