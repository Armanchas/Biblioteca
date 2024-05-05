package org.example.services;

import org.example.Book;
import org.example.User;
import org.example.Administrator;

import java.sql.SQLException;
import java.util.List;

public interface LibraryService {
    String getUserName(int id) throws SQLException;
    int getUserId(String username) throws SQLException;
    String getUserPassword(String username) throws SQLException;
    String getUserRole(String username) throws SQLException;
    void createUser(User user) throws SQLException;
    void updateUser(User user) throws SQLException;
    void deleteUser(int id) throws SQLException;
    User getUserById(int id) throws SQLException;
    List<User> getAllUsers() throws SQLException;
    User getUserByUsername(String username) throws SQLException;
    List<Book> getBorrowHistory(int userId) throws SQLException;
    List<Book> getReservedBooks(int userId) throws SQLException;
    void borrowBook(int userId, int bookId) throws SQLException;
    void returnBook(int userId, int bookId) throws SQLException;
    void reserveBook(int userId, int bookId) throws SQLException;
    List<Administrator> getAllAdministrators() throws SQLException;
    void createAdministrator(Administrator administrator) throws SQLException;
    void updateAdministrator(Administrator administrator) throws SQLException;
    String getAdminPassword(String username) throws SQLException;
    int getAdminId(String username) throws SQLException;
    String getAdminUsername(int id) throws SQLException;
    void createBook(Book book) throws SQLException;
    void updateBook(Book book) throws SQLException;
    void updateBookQuantity(String title, int quantity) throws SQLException;
    int getBookQuantity(String title) throws SQLException;
    void deleteBook(int id) throws SQLException;
    List<Book> getAllBooks() throws SQLException;
    Book getBookById(int id) throws SQLException;
    List<Book> getBooksByTitle(String title) throws SQLException;
    List<Book> getBooksByAuthor(String author) throws SQLException;
    List<Book> getBooksByCategory(String category) throws SQLException;
    List<Book> getBooksByRating(int rating) throws SQLException;
    boolean doesUserExist(String username) throws SQLException;
    boolean doesAdminExist(String username) throws SQLException;
    boolean doesBookExist(String title) throws SQLException;
    boolean isBookAvailable(int id) throws SQLException;
    boolean isValidLogin(String username, String password) throws SQLException;
    boolean isValidAdminLogin(String username, String password) throws SQLException;
}