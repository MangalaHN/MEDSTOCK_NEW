
package com.medicalstock.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.medicalstock.config.Database;
import com.medicalstock.model.Medicine;

public class MedicineDAO {

    // Add a new medicine
    public boolean addMedicine(Medicine medicine) {

        String sql = "INSERT INTO medicines " +
                "(name, category, batch, manufacturer, quantity, " +
                "price, manufacturing_date, expiry_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, medicine.getName());
            ps.setString(2, medicine.getCategory());
            ps.setString(3, medicine.getBatch());
            ps.setString(4, medicine.getManufacturer());
            ps.setInt(5, medicine.getQuantity());
            ps.setDouble(6, medicine.getPrice());
            ps.setDate(7, java.sql.Date.valueOf(
                    medicine.getManufacturingDate()));
            ps.setDate(8, java.sql.Date.valueOf(
                    medicine.getExpiryDate()));

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Add medicine error: " + e.getMessage());
            return false;
        }
    }

    // Update existing medicine
    public boolean updateMedicine(Medicine medicine) {

        String sql = "UPDATE medicines SET " +
                "name = ?, category = ?, batch = ?, " +
                "manufacturer = ?, quantity = ?, price = ?, " +
                "manufacturing_date = ?, expiry_date = ? " +
                "WHERE id = ?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, medicine.getName());
            ps.setString(2, medicine.getCategory());
            ps.setString(3, medicine.getBatch());
            ps.setString(4, medicine.getManufacturer());
            ps.setInt(5, medicine.getQuantity());
            ps.setDouble(6, medicine.getPrice());
            ps.setDate(7, java.sql.Date.valueOf(
                    medicine.getManufacturingDate()));
            ps.setDate(8, java.sql.Date.valueOf(
                    medicine.getExpiryDate()));
            ps.setInt(9, medicine.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Update medicine error: " + e.getMessage());
            return false;
        }
    }

    // Display all medicines
    public List<Medicine> getAllMedicines() {

        List<Medicine> medicines = new ArrayList<>();

        String sql = "SELECT * FROM medicines";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Medicine medicine = new Medicine(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getString("batch"),
                        rs.getString("manufacturer"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getDate("manufacturing_date").toLocalDate(),
                        rs.getDate("expiry_date").toLocalDate()
                );

                medicines.add(medicine);
            }

        } catch (SQLException e) {
            System.out.println("Display error: " + e.getMessage());
        }

        return medicines;
    }

    // Search medicine by name
    public List<Medicine> searchMedicine(String keyword) {

        List<Medicine> medicines = new ArrayList<>();

        String sql = "SELECT * FROM medicines WHERE name LIKE ?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Medicine medicine = new Medicine(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("category"),
                            rs.getString("batch"),
                            rs.getString("manufacturer"),
                            rs.getInt("quantity"),
                            rs.getDouble("price"),
                            rs.getDate("manufacturing_date").toLocalDate(),
                            rs.getDate("expiry_date").toLocalDate()
                    );

                    medicines.add(medicine);
                }
            }

        } catch (SQLException e) {
            System.out.println("Search error: " + e.getMessage());
        }

        return medicines;
    }

    // Delete medicine
    public boolean deleteMedicine(int id) {

        String sql = "DELETE FROM medicines WHERE id = ?";

        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Delete error: " + e.getMessage());
            return false;
        }
    }
}
