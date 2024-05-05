package org.example.gui.admin;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.User;
import org.example.services.LibraryServiceImpl;

public class AdminAddUser extends Application {

    private LibraryServiceImpl libraryService = new LibraryServiceImpl();

    @Override
    public void start(Stage primaryStage) {
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passLabel = new Label("Password:");
        PasswordField passField = new PasswordField();
        Label confirmPasswordLabel = new Label("Confirm Password:");
        PasswordField confirmPasswordField = new PasswordField();
        Label roleLabel = new Label("Role:");
        TextField roleField = new TextField();
        Button submitButton = new Button("Add User");
        Label userAddedLabel = new Label("User added successfully");
        userAddedLabel.setStyle("-fx-text-fill: green;");
        userAddedLabel.setVisible(false);
        Button returnToAdminButton = new Button("Go back");
        Label errorLabel = new Label("Please fill in all fields correctly");
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setVisible(false);
        Label passwordMismatchLabel = new Label("Passwords do not match");
        passwordMismatchLabel.setStyle("-fx-text-fill: red;");
        passwordMismatchLabel.setVisible(false);

        returnToAdminButton.setOnAction(e -> {
            AdminScreen adminScreen = new AdminScreen();
            adminScreen.start(primaryStage);
        });

        submitButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passField.getText();
            String role = roleField.getText();


            if (!password.equals(confirmPasswordField.getText())) {
                passwordMismatchLabel.setVisible(true);
            } else if (username.isEmpty() || role.isEmpty()) {
                errorLabel.setVisible(true);
            } else {
                try {
                    User user = new User(0, username, password, role);
                    libraryService.createUser(user);
                    errorLabel.setVisible(false);
                    passwordMismatchLabel.setVisible(false);
                    userAddedLabel.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }


        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(usernameLabel, usernameField, passLabel, passField, confirmPasswordLabel, confirmPasswordField ,roleLabel, roleField, submitButton, returnToAdminButton, userAddedLabel, passwordMismatchLabel, errorLabel);
        layout.setPadding(new Insets(10));
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        Scene scene = new Scene(layout, 800, 600);

        primaryStage.setTitle("Add User");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}