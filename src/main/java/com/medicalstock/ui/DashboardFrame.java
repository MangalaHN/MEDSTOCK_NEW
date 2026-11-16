
package com.medicalstock.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import com.medicalstock.dao.MedicineDAO;
import com.medicalstock.model.Admin;
import com.medicalstock.model.Medicine;
import com.medicalstock.service.DefaultStockMonitor;
import com.medicalstock.service.StockMonitor;

public class DashboardFrame extends JFrame {

    private MedicineDAO medicineDAO;
    private StockMonitor stockMonitor;

    private JPanel cardsPanel;
    private JTable medicineTable;
    private DefaultTableModel tableModel;

    private JLabel totalMedicinesLabel;
    private JLabel totalStockLabel;
    private JLabel lowStockLabel;
    private JLabel expiredLabel;
    private JLabel expiringSoonLabel;

    public DashboardFrame(Admin admin) {

        medicineDAO = new MedicineDAO();

        setTitle("Medical Stock and Expiry Monitor");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createDashboard();
        refreshDashboard();
    }

    private void createDashboard() {

        setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(25, 80, 150));
        header.setPreferredSize(new Dimension(1000, 65));

        JLabel title = new JLabel(
                "   MEDICAL STOCK AND EXPIRY MONITOR"
        );

        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.WHITE);

        JButton logoutButton = new JButton("Logout");

        logoutButton.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });

        header.add(title, BorderLayout.WEST);
        header.add(logoutButton, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // Dashboard cards
        cardsPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(
                20, 15, 20, 15
        ));

        totalMedicinesLabel = new JLabel("0", SwingConstants.CENTER);
        totalStockLabel = new JLabel("0", SwingConstants.CENTER);
        lowStockLabel = new JLabel("0", SwingConstants.CENTER);
        expiredLabel = new JLabel("0", SwingConstants.CENTER);
        expiringSoonLabel = new JLabel("0", SwingConstants.CENTER);

        cardsPanel.add(createCard("Total Medicines", totalMedicinesLabel));
        cardsPanel.add(createCard("Total Stock", totalStockLabel));
        cardsPanel.add(createCard("Low Stock", lowStockLabel));
        cardsPanel.add(createCard("Expired", expiredLabel));
        cardsPanel.add(createCard("Expiring Soon", expiringSoonLabel));

        // Main content
        JPanel centerPanel = new JPanel(new BorderLayout());

        JLabel tableTitle = new JLabel("Medicine Inventory");
        tableTitle.setFont(new Font("Arial", Font.BOLD, 19));
        tableTitle.setBorder(BorderFactory.createEmptyBorder(
                10, 15, 10, 10
        ));

        String[] columns = {
                "ID", "Name", "Category", "Batch",
                "Manufacturer", "Quantity", "Price",
                "Expiry Date"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        medicineTable = new JTable(tableModel);
        medicineTable.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(medicineTable);

        
JButton addButton = new JButton("Add Medicine");
JButton searchButton = new JButton("Search Medicine");
JButton updateButton = new JButton("Update Medicine");
JButton deleteButton = new JButton("Delete Medicine");
JButton refreshButton = new JButton("Refresh Data");

addButton.addActionListener(e -> addMedicine());

searchButton.addActionListener(e -> searchMedicine());

updateButton.addActionListener(e -> updateMedicine());

deleteButton.addActionListener(e -> deleteMedicine());

refreshButton.addActionListener(e -> refreshDashboard());

JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

bottomPanel.add(addButton);
bottomPanel.add(searchButton);
bottomPanel.add(updateButton);
bottomPanel.add(deleteButton);
bottomPanel.add(refreshButton);


        centerPanel.add(tableTitle, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(bottomPanel, BorderLayout.SOUTH);

        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(cardsPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createCard(String heading, JLabel valueLabel) {

        JPanel card = new JPanel(new BorderLayout());

        card.setBackground(new Color(235, 243, 255));
        card.setBorder(BorderFactory.createLineBorder(
                new Color(25, 80, 150), 1
        ));

        JLabel headingLabel = new JLabel(
                heading, SwingConstants.CENTER
        );

        headingLabel.setFont(new Font("Arial", Font.BOLD, 13));

        valueLabel.setFont(new Font("Arial", Font.BOLD, 25));
        valueLabel.setForeground(new Color(25, 80, 150));

        card.add(headingLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        return card;
    }

    private void refreshDashboard() {

        List<Medicine> medicines =
                medicineDAO.getAllMedicines();

        stockMonitor = new DefaultStockMonitor(medicines);

        totalMedicinesLabel.setText(
                String.valueOf(stockMonitor.getTotalMedicines())
        );

        totalStockLabel.setText(
                String.valueOf(stockMonitor.getTotalStock())
        );

        lowStockLabel.setText(
                String.valueOf(stockMonitor.getLowStockCount())
        );

        expiredLabel.setText(
                String.valueOf(stockMonitor.getExpiredCount())
        );

        expiringSoonLabel.setText(
                String.valueOf(stockMonitor.getExpiringSoonCount())
        );

        tableModel.setRowCount(0);

        for (Medicine medicine : medicines) {

            Object[] row = {
                    medicine.getId(),
                    medicine.getName(),
                    medicine.getCategory(),
                    medicine.getBatch(),
                    medicine.getManufacturer(),
                    medicine.getQuantity(),
                    medicine.getPrice(),
                    medicine.getExpiryDate()
            };

            tableModel.addRow(row);
        }
    }
private void addMedicine() {

    JTextField nameField = new JTextField();
    JTextField categoryField = new JTextField();
    JTextField batchField = new JTextField();
    JTextField manufacturerField = new JTextField();
    JTextField quantityField = new JTextField();
    JTextField priceField = new JTextField();
    JTextField manufacturingDateField = new JTextField();
    JTextField expiryDateField = new JTextField();

    Object[] fields = {
        "Medicine Name:", nameField,
        "Category:", categoryField,
        "Batch Number:", batchField,
        "Manufacturer:", manufacturerField,
        "Quantity:", quantityField,
        "Price:", priceField,
        "Manufacturing Date (yyyy-mm-dd):", manufacturingDateField,
        "Expiry Date (yyyy-mm-dd):", expiryDateField
    };

    int result = JOptionPane.showConfirmDialog(
        this, fields, "Add New Medicine",
        JOptionPane.OK_CANCEL_OPTION
    );

    if (result == JOptionPane.OK_OPTION) {
        try {
            Medicine medicine = new Medicine(
                0,
                nameField.getText().trim(),
                categoryField.getText().trim(),
                batchField.getText().trim(),
                manufacturerField.getText().trim(),
                Integer.parseInt(quantityField.getText().trim()),
                Double.parseDouble(priceField.getText().trim()),
                java.time.LocalDate.parse(manufacturingDateField.getText().trim()),
                java.time.LocalDate.parse(expiryDateField.getText().trim())
            );

            if (medicineDAO.addMedicine(medicine)) {
                JOptionPane.showMessageDialog(this, "Medicine added successfully!");
                refreshDashboard();
            } else {
                JOptionPane.showMessageDialog(this, "Unable to add medicine.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter valid details.\nDates must use yyyy-mm-dd format."
            );
        }
    }
}private void searchMedicine() {

    String keyword = JOptionPane.showInputDialog(
        this,
        "Enter medicine name to search:",
        "Search Medicine",
        JOptionPane.QUESTION_MESSAGE
    );

    if (keyword == null || keyword.trim().isEmpty()) {
        return;
    }

    List<Medicine> medicines =
        medicineDAO.searchMedicine(keyword.trim());

    tableModel.setRowCount(0);

    for (Medicine medicine : medicines) {
        Object[] row = {
            medicine.getId(),
            medicine.getName(),
            medicine.getCategory(),
            medicine.getBatch(),
            medicine.getManufacturer(),
            medicine.getQuantity(),
            medicine.getPrice(),
            medicine.getExpiryDate()
        };

        tableModel.addRow(row);
    }

    if (medicines.isEmpty()) {
        JOptionPane.showMessageDialog(
            this,
            "No medicine found!"
        );
    }
}private void updateMedicine() {

    int selectedRow = medicineTable.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(
            this,
            "Please select a medicine from the table first."
        );
        return;
    }

    int id = (int) tableModel.getValueAt(selectedRow, 0);

    List<Medicine> medicines = medicineDAO.getAllMedicines();

    Medicine selectedMedicine = null;

    for (Medicine medicine : medicines) {
        if (medicine.getId() == id) {
            selectedMedicine = medicine;
            break;
        }
    }

    if (selectedMedicine == null) {
        JOptionPane.showMessageDialog(this, "Medicine not found.");
        return;
    }

    JTextField nameField = new JTextField(selectedMedicine.getName());
    JTextField categoryField = new JTextField(selectedMedicine.getCategory());
    JTextField batchField = new JTextField(selectedMedicine.getBatch());
    JTextField manufacturerField = new JTextField(selectedMedicine.getManufacturer());
    JTextField quantityField = new JTextField(String.valueOf(selectedMedicine.getQuantity()));
    JTextField priceField = new JTextField(String.valueOf(selectedMedicine.getPrice()));
    JTextField manufacturingDateField =
        new JTextField(selectedMedicine.getManufacturingDate().toString());
    JTextField expiryDateField =
        new JTextField(selectedMedicine.getExpiryDate().toString());

    Object[] fields = {
        "Medicine Name:", nameField,
        "Category:", categoryField,
        "Batch Number:", batchField,
        "Manufacturer:", manufacturerField,
        "Quantity:", quantityField,
        "Price:", priceField,
        "Manufacturing Date (yyyy-mm-dd):", manufacturingDateField,
        "Expiry Date (yyyy-mm-dd):", expiryDateField
    };

    int result = JOptionPane.showConfirmDialog(
        this, fields, "Update Medicine",
        JOptionPane.OK_CANCEL_OPTION
    );

    if (result == JOptionPane.OK_OPTION) {
        try {
            Medicine updatedMedicine = new Medicine(
                id,
                nameField.getText().trim(),
                categoryField.getText().trim(),
                batchField.getText().trim(),
                manufacturerField.getText().trim(),
                Integer.parseInt(quantityField.getText().trim()),
                Double.parseDouble(priceField.getText().trim()),
                java.time.LocalDate.parse(manufacturingDateField.getText().trim()),
                java.time.LocalDate.parse(expiryDateField.getText().trim())
            );

            if (medicineDAO.updateMedicine(updatedMedicine)) {
                JOptionPane.showMessageDialog(this, "Medicine updated successfully!");
                refreshDashboard();
            } else {
                JOptionPane.showMessageDialog(this, "Unable to update medicine.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter valid details. Dates must use yyyy-mm-dd format."
            );
        }
    }
}private void deleteMedicine() {

    int selectedRow = medicineTable.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(
            this,
            "Please select a medicine from the table first."
        );
        return;
    }

    int id = (int) tableModel.getValueAt(selectedRow, 0);

    String medicineName =
        tableModel.getValueAt(selectedRow, 1).toString();

    int confirm = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to delete " + medicineName + "?",
        "Confirm Delete",
        JOptionPane.YES_NO_OPTION
    );

    if (confirm == JOptionPane.YES_OPTION) {

        if (medicineDAO.deleteMedicine(id)) {
            JOptionPane.showMessageDialog(
                this,
                "Medicine deleted successfully!"
            );

            refreshDashboard();

        } else {
            JOptionPane.showMessageDialog(
                this,
                "Unable to delete medicine."
            );
        }
    }
}}
