package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.ConfigurationRepository;
import com.example.model.Configuration;

@Service
public class ConfigurationService {

	   @Autowired
	    private ConfigurationRepository configurationRepository;
	    
	    public Configuration saveConfiguration(Configuration configuration) {
	        return configurationRepository.save(configuration);
	    }
	    
	    public List<Configuration> getConfigurations(String deviceId) {
	        return configurationRepository.findByDeviceId(deviceId);
	    }
	    
	    public List<Configuration> getAllConfigurations(){
	    	return configurationRepository.findAll();
	    }
	    
}
