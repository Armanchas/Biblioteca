package org.example.gui.user;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.Book;
import org.example.gui.LoginScreen;
import org.example.services.LibraryServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class UserReturnBook {

    private LibraryServiceImpl libraryService = new LibraryServiceImpl();



    public void start(Stage primaryStage) {

        TextField returnBookIdField = new TextField();
        Label returnBookIdLabel = new Label("Enter book ID to return");
        Button returnBookButton = new Button("Return Book");
        Button returnToUserScreenButton = new Button("Go back");
        Label errorLabel = new Label("Please enter a valid book ID");
        errorLabel.setStyle("-fx-text-fill: red;");
        errorLabel.setVisible(false);
        Label bookReturnedLabel = new Label("Book returned successfully");
        bookReturnedLabel.setStyle("-fx-text-fill: green;");
        bookReturnedLabel.setVisible(false);

        returnToUserScreenButton.setOnAction(e -> {
            UserScreen userScreen = new UserScreen();
            try {
                userScreen.start(primaryStage);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        returnBookButton.setOnAction(e -> {
            String returnBookIdText = returnBookIdField.getText();
            int returnBookId = 0;
            try {
                returnBookId = Integer.parseInt(returnBookIdText);
            } catch (NumberFormatException ex) {
                errorLabel.setVisible(true);
                return;
            }
            try {
                if (returnBookIdText.isEmpty() || libraryService.getBookById(returnBookId) == null) {
                    errorLabel.setVisible(true);
                } else{
                    libraryService.returnBook(libraryService.getUserId(LoginScreen.getLoggedInUser()), returnBookId);
                    errorLabel.setVisible(false);
                    bookReturnedLabel.setVisible(true);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        ListView<String> listView = new ListView<>();

        List<Book> borrowedBooks = null;
        try {
            borrowedBooks = libraryService.getBorrowHistory(libraryService.getUserId(LoginScreen.getLoggedInUser()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Book book : borrowedBooks) {
            listView.getItems().add("ID: " + book.getId() + ", Title: " + book.getTitle());
        }


        HBox returnBookLabels = new HBox(15);
        returnBookLabels.getChildren().addAll(errorLabel, bookReturnedLabel);
        returnBookLabels.setAlignment(javafx.geometry.Pos.CENTER);

        HBox returnBookInputs= new HBox(15);
        returnBookInputs.getChildren().addAll(returnBookIdLabel, returnBookIdField, returnBookButton, returnToUserScreenButton);
        returnBookInputs.setAlignment(javafx.geometry.Pos.CENTER);


        VBox layout = new VBox();
        layout.getChildren().addAll(returnBookInputs, returnBookLabels, listView);

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}