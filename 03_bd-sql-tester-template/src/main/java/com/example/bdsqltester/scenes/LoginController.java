package com.example.bdsqltester.scenes;

import com.example.bdsqltester.HelloApplication;
import com.example.bdsqltester.datasources.MainDataSource;
import com.example.bdsqltester.scenes.user.UserController;
import com.example.bdsqltester.dtos.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
// Import PasswordField
import javafx.scene.control.PasswordField;

import java.io.IOException;
import java.sql.*;

public class LoginController {

    @FXML
    private TextField usernameField;

    // Mengubah tipe dari TextField menjadi PasswordField
    @FXML
    private PasswordField passwordField;

    @FXML
    private ChoiceBox<String> selectRole;

    private User getUserByUsername(String username) throws SQLException {
        try (Connection c = MainDataSource.getConnection()) {
            PreparedStatement stmt = c.prepareStatement("SELECT id, username, password, role FROM users WHERE username = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("role"));
                user.setId(rs.getLong("id"));
                return user;
            }
            return null;
        }
    }

    boolean verifyCredentials(String username, String password, String role) throws SQLException {
        try (Connection c = MainDataSource.getConnection()) {
            PreparedStatement stmt = c.prepareStatement("SELECT password FROM users WHERE username = ? AND role = ?");
            stmt.setString(1, username);
            stmt.setString(2, role.toLowerCase());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String dbPassword = rs.getString("password");
                // Perbandingan password tetap sama
                return dbPassword.equals(password);
            }
            return false;
        }
    }

    @FXML
    void initialize() {
        selectRole.getItems().addAll("Admin", "User");
        selectRole.setValue("User");
    }

    @FXML
    void onLoginClick(ActionEvent event) {
        String username = usernameField.getText();
        // Mengambil teks dari PasswordField (tetap menggunakan getText())
        String password = passwordField.getText();
        String role = selectRole.getValue();

        try {
            if (verifyCredentials(username, password, role)) {
                HelloApplication app = HelloApplication.getApplicationInstance();
                FXMLLoader loader;
                Scene scene;

                if (role.equals("Admin")) {
                    app.getPrimaryStage().setTitle("Admin View");
                    loader = new FXMLLoader(HelloApplication.class.getResource("admin-view.fxml"));
                    try {
                        scene = new Scene(loader.load());
                        app.getPrimaryStage().setScene(scene);
                    } catch (IOException e) {
                        showAlert("Error Loading View", "Failed to load Admin view.", e.getMessage());
                        e.printStackTrace(); // Tetap cetak stack trace untuk debugging
                    }
                } else {
                    app.getPrimaryStage().setTitle("User View");
                    loader = new FXMLLoader(HelloApplication.class.getResource("user-view.fxml"));
                    try {
                        scene = new Scene(loader.load());
                        app.getPrimaryStage().setScene(scene);
                        UserController userController = loader.getController();
                        try {
                            User loggedInUser = getUserByUsername(username);
                            if (loggedInUser != null) {
                                Long loggedInIdFromDatabase = loggedInUser.getId();
                                System.out.println("LoginController: Pengguna berhasil login, ID dari database = " + loggedInIdFromDatabase);
                                System.out.println("LoginController: Akan memanggil setLoggedInUserId dengan nilai = " + loggedInIdFromDatabase);
                                userController.setLoggedInUserId(loggedInIdFromDatabase);
                            } else {
                                showAlert("Error", "Gagal", "Gagal mendapatkan informasi pengguna setelah login");
                            }
                        } catch (SQLException e) {
                            showAlert("Database Error", "Gagal mendapatkan informasi pengguna.", e.getMessage());
                        }
                        app.getPrimaryStage().setScene(scene);
                    } catch (IOException e) {
                        showAlert("Error Loading View", "Failed to load User view.", e.getMessage());
                        e.printStackTrace(); // Tetap cetak stack trace untuk debugging
                    }
                }
            } else {
                showAlert("Login Failed", "Invalid Credentials", "Please check your username and password.");
            }
        } catch (SQLException e) {
            showAlert("Database Error", "Database Connection Failed", "Could not connect to the database. Please try again later.");
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
