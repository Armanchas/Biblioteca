package org.example.gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.gui.admin.AdminScreen;
import org.example.gui.user.UserScreen;
import org.example.services.LibraryServiceImpl;

import java.sql.SQLException;

public class LoginScreen extends Application {

    private boolean isAdminMode = false;
    private LibraryServiceImpl libraryService = new LibraryServiceImpl();

    private static String loggedInUser = null;

    @Override
    public void start(Stage primaryStage) {
        Label userLabel = new Label("Username:");
        TextField userField = new TextField();
        Label passLabel = new Label("Password:");
        PasswordField passField = new PasswordField();
        Button loginButton = new Button("Login");
        Label errorLabel = new Label("Invalid username or password");
        errorLabel.setStyle("-fx-text-fill: red;");
        Button switchButton = new Button("Switch to Admin Login");

        switchButton.setOnAction(e -> {
            isAdminMode = !isAdminMode;
            if (isAdminMode) {
                switchButton.setText("Switch to User Login");
            } else {
                switchButton.setText("Switch to Admin Login");
            }
        });

        loginButton.setOnAction(e -> {
            String username = userField.getText();
            String password = passField.getText();

            VBox errorLayout = new VBox(15);
            errorLayout.getChildren().addAll(userLabel, userField, passLabel, passField, loginButton, switchButton, errorLabel);
            errorLayout.setAlignment(Pos.CENTER);
            Scene errorScene = new Scene(errorLayout, 400, 300);

            //admin and user logins
            if (isAdminMode) {
                try {
                    if (libraryService.isValidAdminLogin(username, password)) {
                        AdminScreen adminScreen = new AdminScreen();
                        adminScreen.start(primaryStage);
                    } else {
                        primaryStage.setScene(errorScene);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                try {
                    if (libraryService.isValidLogin(username, password)) {
                        loggedInUser = username;
                        UserScreen userScreen = new UserScreen();
                        userScreen.start(primaryStage);
                    } else {
                        primaryStage.setScene(errorScene);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        VBox layout = new VBox(15);
        layout.getChildren().addAll(userLabel, userField, passLabel, passField, loginButton, switchButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 300);

        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static String getLoggedInUser() {
        return loggedInUser;
    }
}