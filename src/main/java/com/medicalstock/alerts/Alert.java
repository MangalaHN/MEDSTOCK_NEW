
package com.medicalstock.alerts;

public abstract class Alert {

    protected String message;

    public Alert(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public abstract void displayAlert();
}
