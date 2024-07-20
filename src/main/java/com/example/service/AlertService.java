package com.example.service;
import org.springframework.stereotype.Service;

@Service
public class AlertService {
    // Generic method to log alerts (for simplicity)
    public void logAlert(String message,String Id) {
    	
        System.out.println(Id +":"+"Alert: " + message);
    }
    // Method to send an alert based on the type
    public void sendAlert(String message,String Id) {
      logAlert(message,Id);
    }

}
