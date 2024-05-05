package org.example.gui.user;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.gui.LoginScreen;
import org.example.services.LibraryServiceImpl;

import java.sql.SQLException;

public class UserBorrowBook extends Application {

    private LibraryServiceImpl libraryService = new LibraryServiceImpl();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button borrowBookButton = new Button("Borrow Book");
        Label borrowBookLabel = new Label("Enter book ID to borrow");
        TextField bookIdField = new TextField();
        Label invalidBookIdLabel = new Label("Invalid book ID");
        invalidBookIdLabel.setStyle("-fx-text-fill: red;");
        invalidBookIdLabel.setVisible(false);
        Label bookBorrowedLabel = new Label("Book borrowed successfully");
        bookBorrowedLabel.setStyle("-fx-text-fill: green;");
        bookBorrowedLabel.setVisible(false);
        Button returnToUserScreenButton = new Button("Go back");

        returnToUserScreenButton.setOnAction(e -> {
            UserScreen userScreen = new UserScreen();
            try {
                userScreen.start(primaryStage);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        borrowBookButton.setOnAction(e -> {
            String bookIdText = bookIdField.getText();
            int bookId = Integer.parseInt(bookIdText);
            try {
                if (bookIdText.isEmpty() || libraryService.getBookById(bookId) == null) {
                    invalidBookIdLabel.setVisible(true);
                    System.out.println("Invalid book ID");
                } else {
                    invalidBookIdLabel.setVisible(false);
                    int userId = libraryService.getUserByUsername(LoginScreen.getLoggedInUser()).getId();
                    libraryService.borrowBook(userId, bookId);
                    bookBorrowedLabel.setVisible(true);
                    System.out.println("Book borrowed successfully");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        VBox borrowBookLayout = new VBox(10);
        borrowBookLayout.getChildren().addAll(borrowBookLabel, bookIdField, borrowBookButton, invalidBookIdLabel, bookBorrowedLabel, returnToUserScreenButton);
        borrowBookLayout.setPadding(new Insets(10));
        borrowBookLayout.setAlignment(javafx.geometry.Pos.CENTER);

        Scene scene = new Scene(borrowBookLayout, 800, 600);

        primaryStage.setTitle("Borrow Book");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
