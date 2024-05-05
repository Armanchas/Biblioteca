package org.example.gui.admin;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.Book;
import org.example.services.LibraryServiceImpl;

import java.sql.SQLException;

public class AdminModifyBook extends Application {

    private LibraryServiceImpl libraryService = new LibraryServiceImpl();

    @Override
    public void start(Stage primaryStage) {
        Label idLabel = new Label("Enter book ID:");
        TextField idField = new TextField();
        Label titleLabel = new Label("New Title:");
        TextField titleField = new TextField();
        Label authorLabel = new Label("New Author:");
        TextField authorField = new TextField();
        Label categoryLabel = new Label("New Category:");
        TextField categoryField = new TextField();
        Label quantityLabel = new Label("New Quantity:");
        TextField quantityField = new TextField();
        Button modifyButton = new Button("Modify Book");
        Button returnToAdminButton = new Button("Go back");
        Label errorLabel = new Label("Please fill in all fields correctly");
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setVisible(false);
        Label invalidIdLabel = new Label("Please enter a valid ID");
        invalidIdLabel.setStyle("-fx-text-fill: red;");
        invalidIdLabel.setVisible(false);
        Label bookModifiedLabel = new Label("Book modified successfully");
        bookModifiedLabel.setStyle("-fx-text-fill: green;");
        bookModifiedLabel.setVisible(false);

        returnToAdminButton.setOnAction(e -> {
            AdminScreen adminScreen = new AdminScreen();
            adminScreen.start(primaryStage);
        });

        modifyButton.setOnAction(e -> {
            String idText = idField.getText();
            int id = Integer.parseInt(idText);
            String newTitle = titleField.getText();
            String newAuthor = authorField.getText();
            String newCategory = categoryField.getText();
            int newQuantity = Integer.parseInt(quantityField.getText());

            try {
                if (idText.isEmpty() || newTitle.isEmpty() || newAuthor.isEmpty() || newCategory.isEmpty() || quantityField.getText().isEmpty()) {
                    errorLabel.setVisible(true);
                } else if (libraryService.getBookById(id) == null) {
                    invalidIdLabel.setVisible(true);
                } else {
                        Book book = new Book(id, newTitle, newAuthor, newCategory, newQuantity);
                        libraryService.updateBook(book);
                        errorLabel.setVisible(false);
                        invalidIdLabel.setVisible(false);
                        bookModifiedLabel.setVisible(true);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(idLabel, idField, titleLabel, titleField, authorLabel, authorField, categoryLabel, categoryField, quantityLabel, quantityField, modifyButton, bookModifiedLabel, returnToAdminButton, errorLabel, invalidIdLabel);
        layout.setPadding(new javafx.geometry.Insets(10));
        layout.setAlignment(javafx.geometry.Pos.CENTER);


        Scene scene = new Scene(layout, 800, 600);

        primaryStage.setTitle("Book Administration");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}