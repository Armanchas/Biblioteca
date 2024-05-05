package org.example.gui.user;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.Book;
import org.example.gui.LoginScreen;
import org.example.services.LibraryServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class UserScreen extends Application {

    private LibraryServiceImpl libraryService = new LibraryServiceImpl();

    private String loggedInUser = LoginScreen.getLoggedInUser();

    @Override
    public void start(Stage primaryStage) throws SQLException {
        TextField searchField = new TextField();
        searchField.setPromptText("Search for a book");
        searchField.setPrefWidth(350);
        Button borrowBookButton = new Button("Borrow Book");
        borrowBookButton.setPrefWidth(150);
        Button logOutButton = new Button("Log out");
        logOutButton.setPrefWidth(150);
        Button returnBookButton = new Button("Return Book");
        returnBookButton.setPrefWidth(150);

        logOutButton.setOnAction(e -> {
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.start(primaryStage);
        });

        borrowBookButton.setOnAction(e -> {
            UserBorrowBook userBorrowBook = new UserBorrowBook();
            try {
                userBorrowBook.start(primaryStage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        returnBookButton.setOnAction(e -> {
            UserReturnBook userReturnBook = new UserReturnBook();
            userReturnBook.start(primaryStage);
        });

        TableView<Book> bookTableView = new TableView<>();

        TableColumn<Book, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Book, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        bookTableView.getColumns().addAll(idColumn, titleColumn, authorColumn, categoryColumn, quantityColumn);

        List<Book> books = libraryService.getAllBooks();

        ObservableList<Book> bookDetails = FXCollections.observableArrayList(books);

        FilteredList<Book> filteredData = new FilteredList<>(bookDetails, b -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(book -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (book.getTitle().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (book.getAuthor().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Book> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(bookTableView.comparatorProperty());

        bookTableView.setItems(sortedData);

        HBox topLayout = new HBox(10);
        topLayout.getChildren().addAll(searchField, borrowBookButton, returnBookButton, logOutButton);
        HBox.setMargin(searchField, new Insets(10, 10, 0, 10));
        HBox.setMargin(borrowBookButton, new Insets(10, 10, 0, 0));
        HBox.setMargin(returnBookButton, new Insets(10, 10, 0, 0));
        HBox.setMargin(logOutButton, new Insets(10, 10, 0, 0));

        VBox layout = new VBox(10);
        layout.getChildren().addAll(topLayout, bookTableView);

        Scene scene = new Scene(layout, 800, 600);

        primaryStage.setTitle("Library Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}