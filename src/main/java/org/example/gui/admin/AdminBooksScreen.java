package org.example.gui.admin;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.services.LibraryServiceImpl;
import org.example.Book;

import java.sql.SQLException;
import java.util.List;

public class AdminBooksScreen extends Application {

    private LibraryServiceImpl libraryService = new LibraryServiceImpl();

    @Override
    public void start(Stage primaryStage) throws SQLException {
        TextField searchField = new TextField();
        searchField.setPromptText("Search for a book");
        searchField.setPrefWidth(350);
        Button addBookButton = new Button("Add Book");
        addBookButton.setPrefWidth(150);
        Button returnToAdminButton = new Button("Go back");
        returnToAdminButton.setPrefWidth(150);
        Button deleteBookButton = new Button("Delete Book");
        deleteBookButton.setPrefWidth(150);
        Button modifyBookButton = new Button("Modify Book");
        modifyBookButton.setPrefWidth(150);

        returnToAdminButton.setOnAction(e -> {
            AdminScreen adminScreen = new AdminScreen();
            adminScreen.start(primaryStage);
        });

        addBookButton.setOnAction(e -> {
            AdminAddBook adminAddBook = new AdminAddBook();
            adminAddBook.start(primaryStage);
        });

        modifyBookButton.setOnAction(e -> {
            AdminModifyBook adminModifyBook = new AdminModifyBook();
            adminModifyBook.start(primaryStage);
        });

        deleteBookButton.setOnAction(e -> {
            AdminDeleteBook adminDeleteBook = new AdminDeleteBook();
            adminDeleteBook.start(primaryStage);
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
        topLayout.getChildren().addAll(searchField, addBookButton, modifyBookButton, deleteBookButton, returnToAdminButton);
        HBox.setMargin(searchField, new Insets(10, 10, 0, 10));
        HBox.setMargin(addBookButton, new Insets(10, 10, 0, 0));
        HBox.setMargin(modifyBookButton, new Insets(10, 10, 0, 0));
        HBox.setMargin(deleteBookButton, new Insets(10, 10, 0, 0));
        HBox.setMargin(returnToAdminButton, new Insets(10, 10, 0, 0));

        VBox layout = new VBox(10);
        layout.getChildren().addAll(topLayout, bookTableView);

        Scene scene = new Scene(layout, 800, 600);

        primaryStage.setTitle("Book Administration");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}