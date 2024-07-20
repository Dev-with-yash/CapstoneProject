package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Configuration;
import com.example.service.ConfigurationService;

@RestController
@RequestMapping("/api/configurations")
public class ConfigurationController {
 @Autowired
 private ConfigurationService configurationService;
 @PostMapping
 public ResponseEntity<Configuration> createConfiguration(@RequestBody Configuration 
configuration) {
 Configuration savedConfig = configurationService.saveConfiguration(configuration);
 return ResponseEntity.ok(savedConfig);
 }
 @GetMapping("/{deviceId}")
 public ResponseEntity<List<Configuration>> getConfigurations(@PathVariable String deviceId) {
 List<Configuration> configurations = configurationService.getConfigurations(deviceId);
 return ResponseEntity.ok(configurations);
 }
}