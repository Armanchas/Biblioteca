CREATE TABLE Books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(255),
    category VARCHAR(255),
    quantity INT
);

CREATE TABLE Ratings (
    id SERIAL PRIMARY KEY,
    book_id INT,
    rating INT,
    FOREIGN KEY (book_id) REFERENCES Books(id)
);

CREATE TABLE Comments (
    id SERIAL PRIMARY KEY,
    book_id INT,
    comment TEXT,
    FOREIGN KEY (book_id) REFERENCES Books(id)
);

CREATE TABLE Users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255)
);

CREATE TABLE Administrators (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE BorrowedBooks (
    user_id INT,
    book_id INT,
    borrow_date DATE,
    return_date DATE,
    FOREIGN KEY (user_id) REFERENCES Users(id),
    FOREIGN KEY (book_id) REFERENCES Books(id)
);

CREATE TABLE ReservedBooks (
    user_id INT,
    book_id INT,
    reserve_date DATE,
    FOREIGN KEY (user_id) REFERENCES Users(id),
    FOREIGN KEY (book_id) REFERENCES Books(id)
);

INSERT INTO Administrators (username, password) VALUES ('admin', 'admin');

INSERT INTO Books (title, author, category, quantity) VALUES
('To Kill a Mockingbird', 'Harper Lee', 'Fiction', 10),
('1984', 'George Orwell', 'Fiction', 7),
('The Great Gatsby', 'F. Scott Fitzgerald', 'Fiction', 5),
('Moby Dick', 'Herman Melville', 'Fiction', 3),
('War and Peace', 'Leo Tolstoy', 'Fiction', 2);