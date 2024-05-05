package org.example.gui.admin;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.Book;
import org.example.services.LibraryServiceImpl;

public class AdminAddBook extends Application {

    private LibraryServiceImpl libraryService = new LibraryServiceImpl();

    @Override
    public void start(Stage primaryStage) {
        Label titleLabel = new Label("Book Title:");
        TextField titleField = new TextField();
        Label authorLabel = new Label("Author:");
        TextField authorField = new TextField();
        Label categoryLabel = new Label("Category:");
        TextField categoryField = new TextField();
        Label quantityLabel = new Label("Quantity:");
        TextField quantityField = new TextField();
        Button submitButton = new Button("Add Book");
        Label bookAddedLabel = new Label("Book added successfully");
        bookAddedLabel.setStyle("-fx-text-fill: green;");
        bookAddedLabel.setVisible(false);
        Button returnToAdminButton = new Button("Go back");
        Label errorLabel = new Label("Please fill in all fields correctly");
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setVisible(false);

        returnToAdminButton.setOnAction(e -> {
            AdminScreen adminScreen = new AdminScreen();
            adminScreen.start(primaryStage);
        });

        submitButton.setOnAction(e -> {
            String title = titleField.getText();
            String author = authorField.getText();
            String category = categoryField.getText();
            String quantityText = quantityField.getText();

            if (title.isEmpty() || author.isEmpty() || category.isEmpty() || quantityText.isEmpty()) {
                errorLabel.setVisible(true);
                System.out.println("Please fill in all fields correctly");
                return;
            }

            int quantity;
            try {
                quantity = Integer.parseInt(quantityText);
            } catch (NumberFormatException ex) {
                errorLabel.setVisible(true);
                System.out.println("Please fill in all fields correctly");
                return;
            }

            try {
                if (!libraryService.getBooksByTitle(title).isEmpty()){
                    libraryService.updateBookQuantity(title, libraryService.getBookQuantity(title) + quantity);
                } else {
                    Book book = new Book(0, title, author, category, quantity);
                    libraryService.createBook(book);
                    bookAddedLabel.setVisible(true);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(titleLabel, titleField, authorLabel, authorField, categoryLabel, categoryField, quantityLabel, quantityField, submitButton, returnToAdminButton, bookAddedLabel);
        layout.setPadding(new Insets(10));
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        Scene scene = new Scene(layout, 800, 600);

        primaryStage.setTitle("Add Book");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}