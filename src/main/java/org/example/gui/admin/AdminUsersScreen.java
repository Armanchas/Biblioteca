package org.example.gui.admin;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import org.example.User;
import org.example.services.LibraryServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class AdminUsersScreen extends Application {

    private LibraryServiceImpl libraryService = new LibraryServiceImpl();

    @Override
    public void start(Stage primaryStage) throws SQLException {
        TextField searchField = new TextField();
        searchField.setPromptText("Search for a user");
        searchField.setPrefWidth(350);
        Button addUserButton = new Button("Add user");
        addUserButton.setPrefWidth(150);
        Button returnToAdminButton = new Button("Go back");
        returnToAdminButton.setPrefWidth(150);
        Button deletdUserButton = new Button("Delete user");
        deletdUserButton.setPrefWidth(150);
        Button modifdUserButton = new Button("Modify user");
        modifdUserButton.setPrefWidth(150);

        returnToAdminButton.setOnAction(e -> {
            AdminScreen adminScreen = new AdminScreen();
            adminScreen.start(primaryStage);
        });

        addUserButton.setOnAction(e -> {
            AdminAddUser adminAddUser = new AdminAddUser();
            adminAddUser.start(primaryStage);
        });

        deletdUserButton.setOnAction(e -> {
            AdminDeleteUser adminDeleteUser = new AdminDeleteUser();
            adminDeleteUser.start(primaryStage);
        });

        modifdUserButton.setOnAction(e -> {
            AdminModifyUser adminModifyUser = new AdminModifyUser();
            adminModifyUser.start(primaryStage);
        });

        TableView<User> userTableView = new TableView<>();

        TableColumn<User, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        userTableView.getColumns().addAll(idColumn, usernameColumn, roleColumn);

        List<User> users = libraryService.getAllUsers();

        ObservableList<User> userDetails = FXCollections.observableArrayList(users);

        userTableView.setItems(userDetails);

        HBox topLayout = new HBox(10);
        topLayout.getChildren().addAll(searchField, addUserButton, modifdUserButton, deletdUserButton, returnToAdminButton);
        HBox.setMargin(searchField, new Insets(10, 10, 0, 10));
        HBox.setMargin(addUserButton, new Insets(10, 10, 0, 0));
        HBox.setMargin(modifdUserButton, new Insets(10, 10, 0, 0));
        HBox.setMargin(deletdUserButton, new Insets(10, 10, 0, 0));
        HBox.setMargin(returnToAdminButton, new Insets(10, 10, 0, 0));

        VBox layout = new VBox(10);
        layout.getChildren().addAll(topLayout, userTableView);

        Scene scene = new Scene(layout, 800, 600);

        primaryStage.setTitle("User Administration");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}