package org.example.gui.admin;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.User;
import org.example.services.LibraryServiceImpl;

import java.sql.SQLException;

public class AdminModifyUser extends Application {

    private LibraryServiceImpl libraryService = new LibraryServiceImpl();

    @Override
    public void start(Stage primaryStage) {
        Label idLabel = new Label("Enter user ID:");
        TextField idField = new TextField();
        Label usernameLabel = new Label("Enter new username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Enter new password:");
        TextField passwordField = new TextField();
        Label roleLabel = new Label("Enter new role:");
        TextField roleField = new TextField();
        Button modifyButton = new Button("Modify User");
        Label userModifiedLabel = new Label("User modified successfully");
        userModifiedLabel.setStyle("-fx-text-fill: green;");
        userModifiedLabel.setVisible(false);
        Button returnToAdminButton = new Button("Go back");
        Label errorLabel = new Label("Please enter valid details");
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setVisible(false);

        returnToAdminButton.setOnAction(e -> {
            AdminScreen adminScreen = new AdminScreen();
            adminScreen.start(primaryStage);
        });

        modifyButton.setOnAction(e -> {
            String idText = idField.getText();
            int id = Integer.parseInt(idText);
            String username = usernameField.getText();
            String password = passwordField.getText();
            String role = roleField.getText();
            if (idText.isEmpty() || username.isEmpty() || password.isEmpty() || role.isEmpty()) {
                errorLabel.setVisible(true);
                return;
            }
            try {
                if (libraryService.getUserById(id) == null) {
                    errorLabel.setVisible(true);
                } else {
                    User user = new User(id, username, password, role);
                    libraryService.updateUser(user);
                    errorLabel.setVisible(false);
                    userModifiedLabel.setVisible(true);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(idLabel, idField, usernameLabel, usernameField, passwordLabel, passwordField, roleLabel, roleField, modifyButton, userModifiedLabel, returnToAdminButton, errorLabel);
        layout.setPadding(new Insets(10));
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        Scene scene = new Scene(layout, 800, 600);

        primaryStage.setTitle("User Administration");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}