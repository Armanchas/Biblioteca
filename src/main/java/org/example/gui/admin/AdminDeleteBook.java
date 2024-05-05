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

public class AdminDeleteBook extends Application {

    private LibraryServiceImpl libraryService = new LibraryServiceImpl();

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Enter book ID:");
        TextField idField = new TextField();
        Button deleteButton = new Button("Delete Book");
        Label bookDeletedLabel = new Label("Book deleted successfully");
        bookDeletedLabel.setStyle("-fx-text-fill: green;");
        bookDeletedLabel.setVisible(false);
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
                if (libraryService.getBookById(id) == null) {
                    errorLabel.setVisible(true);
                } else {
                    libraryService.deleteBook(id);
                    errorLabel.setVisible(false);
                    bookDeletedLabel.setVisible(true);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, idField, deleteButton, bookDeletedLabel, returnToAdminButton, errorLabel);
        layout.setPadding(new Insets(10));
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        Scene scene = new Scene(layout, 800, 600);

        primaryStage.setTitle("Book Administration");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}