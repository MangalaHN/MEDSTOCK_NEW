
package com.medicalstock;

import javax.swing.SwingUtilities;
import com.medicalstock.ui.LoginFrame;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            LoginFrame login = new LoginFrame();
            login.setVisible(true);
        });

    }
}
