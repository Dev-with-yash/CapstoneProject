package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Configuration;

public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
	 List<Configuration> findByDeviceId(String deviceId);
}