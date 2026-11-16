
package com.medicalstock.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Medicine {

    private int id;
    private String name;
    private String category;
    private String batch;
    private String manufacturer;
    private int quantity;
    private double price;
    private LocalDate manufacturingDate;
    private LocalDate expiryDate;

    public Medicine(int id, String name, String category,
                    String batch, String manufacturer,
                    int quantity, double price,
                    LocalDate manufacturingDate,
                    LocalDate expiryDate) {

        this.id = id;
        this.name = name;
        this.category = category;
        this.batch = batch;
        this.manufacturer = manufacturer;
        this.quantity = quantity;
        this.price = price;
        this.manufacturingDate = manufacturingDate;
        this.expiryDate = expiryDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getBatch() {
        return batch;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        }
    }

    public long getRemainingDays() {
        return ChronoUnit.DAYS.between(
                LocalDate.now(), expiryDate
        );
    }

    public boolean isExpired() {
        return expiryDate.isBefore(LocalDate.now());
    }

    public boolean isExpiringSoon() {
        long days = getRemainingDays();
        return days >= 0 && days <= 30;
    }
}

