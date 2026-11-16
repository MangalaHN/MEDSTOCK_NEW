
package com.medicalstock.service;

import java.util.ArrayList;
import java.util.List;

import com.medicalstock.model.Medicine;

public class DefaultStockMonitor implements StockMonitor {

    private List<Medicine> medicines;

    public DefaultStockMonitor(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    @Override
    public int getTotalMedicines() {
        return medicines.size();
    }

    @Override
    public int getTotalStock() {
        int total = 0;

        for (Medicine medicine : medicines) {
            total += medicine.getQuantity();
        }

        return total;
    }

    @Override
    public int getLowStockCount() {
        return getLowStockMedicines().size();
    }

    @Override
    public int getExpiredCount() {
        return getExpiredMedicines().size();
    }

    @Override
    public int getExpiringSoonCount() {
        return getExpiringSoonMedicines().size();
    }

    @Override
    public List<Medicine> getLowStockMedicines() {

        List<Medicine> result = new ArrayList<>();

        for (Medicine medicine : medicines) {
            if (medicine.getQuantity() <= 10
                    && !medicine.isExpired()) {
                result.add(medicine);
            }
        }

        return result;
    }

    @Override
    public List<Medicine> getExpiredMedicines() {

        List<Medicine> result = new ArrayList<>();

        for (Medicine medicine : medicines) {
            if (medicine.isExpired()) {
                result.add(medicine);
            }
        }

        return result;
    }

    @Override
    public List<Medicine> getExpiringSoonMedicines() {

        List<Medicine> result = new ArrayList<>();

        for (Medicine medicine : medicines) {
            if (medicine.isExpiringSoon()) {
                result.add(medicine);
            }
        }

        return result;
    }
}
