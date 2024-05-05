package org.example.gui.admin;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.gui.LoginScreen;

import java.sql.SQLException;

public class AdminScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button manageBooksButton = new Button("Manage Books");
        manageBooksButton.setPrefSize(300, 70);

        Button manageUsersButton = new Button("Manage Users");
        manageUsersButton.setPrefSize(300, 70);

        Button returnToLoginButton = new Button("Logout");
        returnToLoginButton.setPrefSize(300, 70);

        manageBooksButton.setOnAction(e -> {
            AdminBooksScreen adminBooksScreen = new AdminBooksScreen();
            try {
                adminBooksScreen.start(primaryStage);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        manageUsersButton.setOnAction(e -> {
            AdminUsersScreen adminUsersScreen = new AdminUsersScreen();
            try {
                adminUsersScreen.start(primaryStage);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        returnToLoginButton.setOnAction(e -> {
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.start(primaryStage);
        });

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(manageBooksButton, manageUsersButton, returnToLoginButton);

        Scene scene = new Scene(layout, 800, 600);

        primaryStage.setTitle("Library Administration");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}