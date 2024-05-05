package org.example.services;

import org.example.DAOs.UsersDAO;
import org.example.DAOs.AdministratorsDAO;
import org.example.DAOs.BooksDAO;
import org.example.Book;
import org.example.User;
import org.example.Administrator;

import java.sql.SQLException;
import java.util.List;

public class LibraryServiceImpl implements LibraryService {
    private UsersDAO usersDAO;
    private AdministratorsDAO administratorsDAO;
    private BooksDAO booksDAO;

    public LibraryServiceImpl() {
        this.usersDAO = new UsersDAO();
        this.administratorsDAO = new AdministratorsDAO();
        this.booksDAO = new BooksDAO();
    }

    @Override
    public String getUserName(int id) throws SQLException {
        return usersDAO.getUsername(id);
    }

    @Override
    public int getUserId(String username) throws SQLException {
        return usersDAO.getUserId(username);
    }

    @Override
    public String getUserPassword(String username) throws SQLException {
        return usersDAO.getUserPassword(username);
    }

    @Override
    public String getUserRole(String username) throws SQLException {
        return usersDAO.getUserRole(username);
    }

    @Override
    public void createUser(User user) throws SQLException {
        usersDAO.createUser(user);
    }

    @Override
    public void updateUser(User user) throws SQLException {
        usersDAO.updateUser(user);
    }

    @Override
    public void deleteUser(int id) throws SQLException {
        usersDAO.deleteUser(id);
    }

    @Override
    public User getUserById(int id) throws SQLException {
        return usersDAO.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        return usersDAO.getAllUsers();
    }

    @Override
    public User getUserByUsername(String username) throws SQLException {
        return usersDAO.getUserByUsername(username);
    }

    @Override
    public List<Book> getBorrowHistory(int userId) throws SQLException {
        return usersDAO.getBorrowHistory(userId);
    }

    @Override
    public List<Book> getReservedBooks(int userId) throws SQLException {
        return usersDAO.getReservedBooks(userId);
    }

    @Override
    public void borrowBook(int userId, int bookId) throws SQLException {
        usersDAO.borrowBook(userId, bookId);
    }

    @Override
    public void returnBook(int userId, int bookId) throws SQLException {
        usersDAO.returnBook(userId, bookId);
    }
    @Override
    public void reserveBook(int userId, int bookId) throws SQLException {
        usersDAO.reserveBook(userId, bookId);
    }

    @Override
    public List<Administrator> getAllAdministrators() throws SQLException {
        return administratorsDAO.getAllAdministrators();
    }

    @Override
    public void createAdministrator(Administrator administrator) throws SQLException {
        administratorsDAO.createAdministrator(administrator);
    }

    @Override
    public void updateAdministrator(Administrator administrator) throws SQLException {
        administratorsDAO.updateAdministrator(administrator);
    }

    @Override
    public String getAdminPassword(String username) throws SQLException {
        return administratorsDAO.getAdminPassword(username);
    }

    @Override
    public int getAdminId(String username) throws SQLException {
        return administratorsDAO.getAdminId(username);
    }

    @Override
    public String getAdminUsername(int id) throws SQLException {
        return administratorsDAO.getAdminUsername(id);
    }

    @Override
    public void createBook(Book book) throws SQLException {
        booksDAO.createBook(book);
    }

    @Override
    public void updateBook(Book book) throws SQLException {
        booksDAO.updateBook(book);
    }

    @Override
    public void updateBookQuantity(String title, int quantity) throws SQLException {
        booksDAO.updateBookQuantity(title, quantity);
    }

    public int getBookQuantity(String title) throws SQLException {
        return booksDAO.getBookQuantity(title);
    }

    @Override
    public void deleteBook(int id) throws SQLException {
        booksDAO.deleteBook(id);
    }

    @Override
    public List<Book> getAllBooks() throws SQLException {
        return booksDAO.getAllBooks();
    }

    @Override
    public Book getBookById(int id) throws SQLException {
        return booksDAO.getBookById(id);
    }

    @Override
    public List<Book> getBooksByTitle(String title) throws SQLException {
        return booksDAO.getBooksByTitle(title);
    }

    @Override
    public List<Book> getBooksByAuthor(String author) throws SQLException {
        return booksDAO.getBooksByAuthor(author);
    }

    @Override
    public List<Book> getBooksByCategory(String category) throws SQLException {
        return booksDAO.getBooksByCategory(category);
    }

    @Override
    public List<Book> getBooksByRating(int rating) throws SQLException {
        return booksDAO.getBooksByRating(rating);
    }

    @Override
    public boolean doesUserExist(String username) throws SQLException {
        return (usersDAO.getUserByUsername(username) != null);
    }

    @Override
    public boolean doesAdminExist(String username) throws SQLException {
        return (administratorsDAO.getAdminByUsername(username) != null);
    }

    @Override
    public boolean doesBookExist(String title) throws SQLException {
        return (booksDAO.getBooksByTitle(title) != null);
    }

    @Override
    public boolean isBookAvailable(int id) throws SQLException {
        return booksDAO.isBookAvailable(id);

    }

    @Override
    public boolean isValidLogin(String username, String password) throws SQLException {
        return usersDAO.isValidLogin(username, password);
    }

    @Override
    public boolean isValidAdminLogin(String username, String password) throws SQLException {
        return administratorsDAO.isValidAdminLogin(username, password);
    }


}