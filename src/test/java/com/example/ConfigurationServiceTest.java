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
     config.setDeviceId("device_001");
     config.setConfigData("{ \"mode\": \"auto\", \"threshold\": 75, \"enabledFeatures\": [\"feature1\", \"feature2\"] }");
     config.setVersion(1);
//     config.setId(123456789L);
     config.setCreatedAt(LocalDateTime.of(2023, 7, 18, 15, 30));

     Configuration savedConfig = configurationService.saveConfiguration(config);
     assertEquals("device_001", savedConfig.getDeviceId());
     assertEquals("{ \"mode\": \"auto\", \"threshold\": 75, \"enabledFeatures\": [\"feature1\", \"feature2\"] }", savedConfig.getConfigData());
     assertEquals(1, savedConfig.getVersion());
//     assertEquals(123456789L, savedConfig.getId());
     assertEquals(LocalDateTime.of(2023, 7, 18, 15, 30), savedConfig.getCreatedAt());
     

}
 }
