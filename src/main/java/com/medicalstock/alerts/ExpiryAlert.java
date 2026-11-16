
package com.medicalstock.alerts;

public class ExpiryAlert extends Alert {

    public ExpiryAlert(String message) {
        super(message);
    }

    @Override
    public void displayAlert() {
        System.out.println("EXPIRY ALERT: " + message);
    }
}
