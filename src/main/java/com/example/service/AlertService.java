package com.example.service;
import org.springframework.stereotype.Service;

@Service
public class AlertService {
    // Generic method to log alerts (for simplicity)
    public void logAlert(String message) {
        System.out.println("Alert: " + message);
    }
    // Method to send an alert based on the type
    public void sendAlert(String message) {
      logAlert(message);
    }

}
