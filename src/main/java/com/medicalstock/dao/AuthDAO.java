
package com.medicalstock.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.medicalstock.config.Database;
import com.medicalstock.model.Admin;

public class AuthDAO {

    public Admin login(String username, String password) {

        String sql = "SELECT id, username, password_hash, role " +
                     "FROM users " +
                     "WHERE username = ? AND password_hash = SHA2(?, 256)";

        try (Connection con = Database.getConnection()) {

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, username);
                ps.setString(2, password);

                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {

                        return new Admin(
                                rs.getInt("id"),
                                rs.getString("username"),
                                rs.getString("password_hash"),
                                rs.getString("role")
                        );
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
        }

        return null;
    }
}
