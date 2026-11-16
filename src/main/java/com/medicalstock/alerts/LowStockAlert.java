
package com.medicalstock.alerts;

public class LowStockAlert extends Alert {

    public LowStockAlert(String message) {
        super(message);
    }

    @Override
    public void displayAlert() {
        System.out.println("LOW STOCK ALERT: " + message);
    }
}
