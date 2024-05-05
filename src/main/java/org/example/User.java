package org.example;

import java.util.ArrayList;
import java.util.List;
public class User {
    private int id;
    private String username;
    private String password;
    private String role; // Estudiante, Profesor, Personal de la Biblioteca
    private List<Book> borrowHistory;
    private List<Book> reservedBooks;
    private List<Integer> ratings;

    public User(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.borrowHistory = new ArrayList<>();
        this.reservedBooks = new ArrayList<>();
        this.ratings = new ArrayList<>();
    }

    public void borrowBook(Book book) {
        borrowHistory.add(book);
    }

    public void realizarReserva(Book book) {
        reservedBooks.add(book);
    }

    public void agregarCalificacion(int rating) {
        ratings.add(rating);
    }

    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
}