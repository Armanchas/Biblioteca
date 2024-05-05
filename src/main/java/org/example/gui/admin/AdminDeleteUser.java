package org.example.gui.admin;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.services.LibraryServiceImpl;

import java.sql.SQLException;

public class AdminDeleteUser extends Application {

    private LibraryServiceImpl libraryService = new LibraryServiceImpl();

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Enter user ID:");
        TextField idField = new TextField();
        Button deleteButton = new Button("Delete User");
        Label userDeletedLabel = new Label("User deleted successfully");
        userDeletedLabel.setStyle("-fx-text-fill: green;");
        userDeletedLabel.setVisible(false);
        Button returnToAdminButton = new Button("Go back");
        Label errorLabel = new Label("Please enter a valid ID");
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setVisible(false);

        returnToAdminButton.setOnAction(e -> {
            AdminScreen adminScreen = new AdminScreen();
            adminScreen.start(primaryStage);
        });

        deleteButton.setOnAction(e -> {
            String idText = idField.getText();
            int id = Integer.parseInt(idText);
            if (idText.isEmpty()) {
                errorLabel.setVisible(true);
                return;
            }
            try {
                if (libraryService.getUserById(id) == null) {
                    errorLabel.setVisible(true);
                } else {
                    libraryService.deleteUser(id);
                    errorLabel.setVisible(false);
                    userDeletedLabel.setVisible(true);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, idField, deleteButton, userDeletedLabel, returnToAdminButton, errorLabel);
        layout.setPadding(new Insets(10));
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        Scene scene = new Scene(layout, 800, 600);

        primaryStage.setTitle("User Administration");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}