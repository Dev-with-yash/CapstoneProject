package com.example.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "configurations")
public class Configuration {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 
 @Column(name = "device_id", nullable = false)
 private String deviceId;
 
 @Column(name = "config_data", columnDefinition = "json", nullable = false)
 private String configData;
 
 @Column(name = "version", nullable = false)
 private int version;
 
 @Column(name = "created_at", nullable = false)
 private LocalDateTime createdAt;
 
 

 
// public String getFormattedConfigData() {
//	    try {
//	        ObjectMapper mapper = new ObjectMapper();
//	        Object json = mapper.readValue(configData, Object.class);
//	        ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
//	        return writer.writeValueAsString(json);
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        return configData; // return unformatted JSON in case of an error
//	    }
//	}
		public Long getId() {
			return id;
		}
		
		public void setId(Long id) {
			this.id = id;
		}
		
		public String getDeviceId() {
			return deviceId;
		}
		
		public void setDeviceId(String deviceId) {
			this.deviceId = deviceId;
		}
		
		public String getConfigData() {
			return configData;
		}
		
		public void setConfigData(String configData) {
			this.configData = configData;
		}
		
		public int getVersion() {
			return version;
		}
		
		public void setVersion(int version) {
			this.version = version;
		}
		
		public LocalDateTime getCreatedAt() {
			return createdAt;
		}
		
		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}
		
		public Configuration(String deviceId, String configData) {
			super();
			this.deviceId = deviceId;
			this.configData = configData;
		}
		
		public Configuration() {
			
		}

}