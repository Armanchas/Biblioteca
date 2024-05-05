package org.example;

import javafx.application.Application;
import org.example.gui.LoginScreen;

public class Main {
    public static void main(String[] args){
        //Usuario del administrador = admin
        //Contraseña del administrador = admin
        Application.launch(LoginScreen.class, args);
    }
}