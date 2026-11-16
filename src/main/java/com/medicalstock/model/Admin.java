
package com.medicalstock.model;

public class Admin extends User {

    private String adminName;

    // Constructor
    public Admin(int id, String username,
            String passwordHash, String adminName) {

        super(id, username, passwordHash, "ADMIN");

        this.adminName = adminName;
    }

    // Getter
    public String getAdminName() {
        return adminName;
    }

    // Setter
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    // Display admin details
    public void displayDetails() {

        System.out.println("Admin ID: " + getId());
        System.out.println("Username: " + getUsername());
        System.out.println("Admin Name: " + adminName);
        System.out.println("Role: " + getRole());

    }
}
