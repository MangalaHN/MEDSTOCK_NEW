
package com.medicalstock.service;

import java.util.List;
import com.medicalstock.model.Medicine;

public interface StockMonitor {

    int getTotalMedicines();

    int getTotalStock();

    int getLowStockCount();

    int getExpiredCount();

    int getExpiringSoonCount();

    List<Medicine> getLowStockMedicines();

    List<Medicine> getExpiredMedicines();

    List<Medicine> getExpiringSoonMedicines();
}
