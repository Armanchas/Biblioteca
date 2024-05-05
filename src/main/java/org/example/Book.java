package org.example;
import java.util.ArrayList;
import java.util.List;

public class Book {
    private int id;
    private String title;
    private String author;
    private String category;
    private int quantity;
    private List<Integer> ratings;
    private List<String> comments;

    public Book(int id, String title, String author, String category, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.quantity = quantity;
        this.ratings = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public void addRating(int rating) {
        ratings.add(rating);
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }
}