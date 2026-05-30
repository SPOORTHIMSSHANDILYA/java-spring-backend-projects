```sql
-- ============================================================================
-- LIBRARY MANAGEMENT SYSTEM - DATABASE SCHEMA & INITIALIZATION SCRIPT
-- ============================================================================
-- Entity Relationship Summary
-- ============================================================================
-- authors (1)  → books (Many)  — One author has many books
-- books (1)    → borrow_records (Many)  — One book can have many borrow records
-- members (1)  → borrow_records (Many)  — One member can have many borrow records
--
-- Relationship Diagram:
--        Author (1)
--           ↑
--           |  @ManyToOne (owner = author_id)
--        Book (Many)
--           ↑
--           |  @ManyToOne (owner = book_id)
--     BorrowRecord
--           ↓
--           |  @ManyToOne (owner = member_id)
--        Member (1)
-- ============================================================================

-- Create Database
CREATE DATABASE IF NOT EXISTS `library_management`;
USE `library_management`;

-- ============================================================================
-- TABLE 1: authors
-- ============================================================================
CREATE TABLE authors (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(60) NOT NULL,
    last_name VARCHAR(60) NOT NULL,
    email VARCHAR(120) UNIQUE,
    bio TEXT,
    nationality VARCHAR(60),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- ============================================================================
-- TABLE 2: books
-- ============================================================================
CREATE TABLE books (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    isbn VARCHAR(20) NOT NULL UNIQUE,
    genre VARCHAR(60) NOT NULL,
    published_year INT,
    total_copies INT NOT NULL DEFAULT 1,
    available_copies INT NOT NULL DEFAULT 1,
    author_id BIGINT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_book_author FOREIGN KEY (author_id)
        REFERENCES authors(id) ON DELETE RESTRICT
);

-- ============================================================================
-- TABLE 3: members
-- ============================================================================
CREATE TABLE members (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(60) NOT NULL,
    last_name VARCHAR(60) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    phone VARCHAR(20),
    membership_date DATE NOT NULL,
    is_active TINYINT(1) NOT NULL DEFAULT 1,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- ============================================================================
-- TABLE 4: borrow_records
-- ============================================================================
CREATE TABLE borrow_records (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    member_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    borrow_date DATE NOT NULL,
    due_date DATE NOT NULL,              -- typically borrow_date + 14 days
    return_date DATE,                     -- NULL means not returned yet
    status VARCHAR(20) NOT NULL DEFAULT 'BORROWED',  -- BORROWED | RETURNED | OVERDUE
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_borrow_member FOREIGN KEY (member_id)
        REFERENCES members(id) ON DELETE RESTRICT,
    CONSTRAINT fk_borrow_book FOREIGN KEY (book_id)
        REFERENCES books(id) ON DELETE RESTRICT
);

-- ============================================================================
-- SEED DATA - AUTHORS (14 authors)
-- ============================================================================
INSERT INTO authors (first_name, last_name, email, bio, nationality) VALUES
('George', 'Orwell', 'orwell@email.com', 'Author of dystopian and political fiction novels', 'British'),
('J.K.', 'Rowling', 'rowling@email.com', 'Creator of the Harry Potter fantasy series', 'British'),
('Robert', 'Martin', 'rmartin@email.com', 'Software engineer and author known as Uncle Bob', 'American'),
('Yuval Noah', 'Harari', 'harari@email.com', 'Historian and professor at Hebrew University Jerusalem', 'Israeli'),
('Agatha', 'Christie', 'christie@email.com', 'Queen of Crime — bestselling mystery novelist of all time', 'British'),
('Dan', 'Brown', 'danbrown@email.com', 'Author of fast-paced thriller and mystery novels', 'American'),
('Paulo', 'Coelho', 'coelho@email.com', 'Brazilian author known for The Alchemist worldwide', 'Brazilian'),
('Stephen', 'King', 'sking@email.com', 'King of Horror — prolific author of horror and thriller', 'American'),
('J.R.R.', 'Tolkien', 'tolkien@email.com', 'Creator of Middle-earth and The Lord of the Rings', 'British'),
('Chetan', 'Bhagat', 'chetan@email.com', 'Indian author and columnist writing about modern India', 'Indian');

-- ============================================================================
-- SEED DATA - BOOKS (35 books)
-- ============================================================================
INSERT INTO books (title, isbn, genre, published_year, total_copies, available_copies, author_id) VALUES
-- George Orwell Books
('1984', 'ISBN-001', 'Dystopian', 1949, 5, 5, 1),
('Animal Farm', 'ISBN-002', 'Political', 1945, 4, 4, 1),
('Homage to Catalonia', 'ISBN-003', 'Non-Fiction', 1938, 3, 2, 1),
('Down and Out in Paris and London', 'ISBN-004', 'Memoir', 1933, 2, 2, 1),
('The Road to Wigan Pier', 'ISBN-005', 'Non-Fiction', 1937, 2, 1, 1),

-- J.K. Rowling Books
('Harry Potter and the Philosophers Stone', 'ISBN-006', 'Fantasy', 1997, 8, 7, 2),
('Harry Potter and the Chamber of Secrets', 'ISBN-007', 'Fantasy', 1998, 7, 6, 2),
('Harry Potter and the Prisoner of Azkaban', 'ISBN-008', 'Fantasy', 1999, 7, 5, 2),
('Harry Potter and the Goblet of Fire', 'ISBN-009', 'Fantasy', 2000, 6, 6, 2),
('Harry Potter and the Order of the Phoenix', 'ISBN-010', 'Fantasy', 2003, 6, 4, 2),

-- Robert Martin Books (Uncle Bob)
('Clean Code', 'ISBN-011', 'Technology', 2008, 6, 5, 3),
('The Clean Coder', 'ISBN-012', 'Technology', 2011, 4, 4, 3),
('Clean Architecture', 'ISBN-013', 'Technology', 2017, 5, 3, 3),
('Agile Software Development', 'ISBN-014', 'Technology', 2002, 3, 3, 3),
('Clean Agile', 'ISBN-015', 'Technology', 2019, 3, 2, 3),

-- Yuval Noah Harari Books
('Sapiens', 'ISBN-016', 'History', 2011, 7, 6, 4),
('Homo Deus', 'ISBN-017', 'Non-Fiction', 2015, 5, 5, 4),
('21 Lessons for the 21st Century', 'ISBN-018', 'Non-Fiction', 2018, 4, 4, 4),
('Unstoppable Us', 'ISBN-019', 'History', 2022, 3, 3, 4),
('Nexus', 'ISBN-020', 'Non-Fiction', 2023, 2, 2, 4),

-- Agatha Christie Books
('Murder on the Orient Express', 'ISBN-021', 'Mystery', 1934, 6, 5, 5),
('And Then There Were None', 'ISBN-022', 'Mystery', 1939, 5, 4, 5),
('The ABC Murders', 'ISBN-023', 'Mystery', 1936, 4, 4, 5),
('Death on the Nile', 'ISBN-024', 'Mystery', 1937, 4, 3, 5),
('Crooked House', 'ISBN-025', 'Mystery', 1949, 3, 3, 5),

-- Dan Brown Books
('The Da Vinci Code', 'ISBN-026', 'Thriller', 2003, 6, 5, 6),
('Angels & Demons', 'ISBN-027', 'Thriller', 2000, 5, 5, 6),
('The Lost Symbol', 'ISBN-028', 'Thriller', 2009, 4, 4, 6),

-- Paulo Coelho Books
('The Alchemist', 'ISBN-029', 'Philosophical', 1988, 7, 7, 7),
('The Pilgrimage', 'ISBN-030', 'Self-Help', 1987, 5, 5, 7),

-- Stephen King Books
('The Shining', 'ISBN-031', 'Horror', 1977, 4, 4, 8),
('It', 'ISBN-032', 'Horror', 1986, 5, 4, 8),

-- J.R.R. Tolkien Books
('The Lord of the Rings', 'ISBN-033', 'Fantasy', 1954, 8, 7, 9),
('The Hobbit', 'ISBN-034', 'Fantasy', 1937, 6, 5, 9),

-- Chetan Bhagat Books
('Five Point Someone', 'ISBN-035', 'Fiction', 2004, 7, 6, 10),
('The 3 Mistakes of My Life', 'ISBN-036', 'Fiction', 2008, 5, 4, 10),
('2 States', 'ISBN-037', 'Romance', 2009, 6, 5, 10);

-- ============================================================================
-- SEED DATA - MEMBERS (24 members)
-- ============================================================================
INSERT INTO members (first_name, last_name, email, phone, membership_date, is_active) VALUES
('Amit', 'Sharma', 'amit@email.com', '9876543210', '2024-01-10', 1),
('Priya', 'Reddy', 'priya@email.com', '9123456780', '2024-02-15', 1),
('Rahul', 'Verma', 'rahul@email.com', '9988776655', '2024-03-20', 1),
('Sneha', 'Iyer', 'sneha@email.com', '9090909090', '2024-04-05', 1),
('Vikram', 'Patel', 'vikram@email.com', '9871234560', '2024-04-12', 1),
('Anjali', 'Nair', 'anjali@email.com', '9345678901', '2024-04-20', 1),
('Karthik', 'Menon', 'karthik@email.com', '9012345678', '2024-05-01', 1),
('Deepa', 'Gupta', 'deepa@email.com', '9234567890', '2024-05-14', 0),
('Arjun', 'Singh', 'arjun@email.com', '9567890123', '2024-05-22', 1),
('Meera', 'Joshi', 'meera@email.com', '9678901234', '2024-06-03', 1),
('Suresh', 'Kumar', 'suresh@email.com', '9789012345', '2024-06-15', 0),
('Divya', 'Rao', 'divya@email.com', '9456789012', '2024-06-28', 1),
('Naveen', 'Choudhary', 'naveen@email.com', '9321098765', '2024-07-07', 1),
('Pooja', 'Deshmukh', 'pooja@email.com', '9654321098', '2024-07-19', 1),
('Ravi', 'Pillai', 'ravi@email.com', '9210987654', '2024-08-02', 0),
('Lakshmi', 'Bhat', 'lakshmi@email.com', '9109876543', '2024-08-14', 1),
('Siddharth', 'Malhotra', 'siddharth@email.com', '9432109876', '2024-08-25', 1),
('Kavitha', 'Srinivasan', 'kavitha@email.com', '9543210987', '2024-09-05', 1),
('Manoj', 'Tiwari', 'manoj@email.com', '9876012345', '2024-09-18', 0),
('Swati', 'Kulkarni', 'swati@email.com', '9765432109', '2024-09-29', 1),
('Arun', 'Hegde', 'arun@email.com', '9898765432', '2024-10-10', 1),
('Nisha', 'Pandey', 'nisha@email.com', '9187654321', '2024-10-22', 1),
('Ganesh', 'Sawant', 'ganesh@email.com', '9276543210', '2024-11-03', 1),
('Revathi', 'Subramaniam', 'revathi@email.com', '9365432109', '2024-11-15', 0);

-- ============================================================================
-- SEED DATA - BORROW RECORDS
-- ============================================================================
-- Active Borrowing (Status: BORROWED)
INSERT INTO borrow_records (member_id, book_id, borrow_date, due_date, return_date, status) VALUES
(1, 1, '2024-05-20', '2024-06-03', NULL, 'BORROWED'),
(2, 3, '2024-05-22', '2024-06-05', NULL, 'BORROWED'),
(3, 5, '2024-05-18', '2024-06-01', NULL, 'BORROWED'),
(4, 7, '2024-05-21', '2024-06-04', NULL, 'BORROWED'),
(5, 4, '2024-05-20', '2024-06-03', NULL, 'BORROWED'),
(6, 7, '2024-05-22', '2024-06-05', NULL, 'BORROWED'),
(9, 1, '2024-05-18', '2024-06-01', NULL, 'BORROWED'),
(9, 3, '2024-05-19', '2024-06-02', NULL, 'BORROWED'),
(10, 2, '2024-05-21', '2024-06-04', NULL, 'BORROWED'),
(13, 5, '2024-05-15', '2024-05-29', NULL, 'BORROWED'),
(16, 6, '2024-05-20', '2024-06-03', NULL, 'BORROWED');

-- Returned Books (Status: RETURNED)
INSERT INTO borrow_records (member_id, book_id, borrow_date, due_date, return_date, status) VALUES
(1, 2, '2024-05-03', '2024-05-17', '2024-05-16', 'RETURNED'),
(2, 1, '2024-05-10', '2024-05-24', '2024-05-20', 'RETURNED'),
(5, 2, '2024-05-05', '2024-05-19', '2024-05-18', 'RETURNED'),
(6, 4, '2024-05-08', '2024-05-22', '2024-05-15', 'RETURNED'),
(7, 3, '2024-05-12', '2024-05-26', '2024-05-25', 'RETURNED'),
(8, 1, '2024-04-20', '2024-05-04', '2024-05-01', 'RETURNED'),
(10, 5, '2024-05-01', '2024-05-15', '2024-05-14', 'RETURNED'),
(12, 6, '2024-04-15', '2024-04-29', '2024-04-28', 'RETURNED'),
(14, 2, '2024-05-10', '2024-05-24', '2024-05-22', 'RETURNED');

-- Overdue Books (Status: BORROWED but past due date, not returned)
INSERT INTO borrow_records (member_id, book_id, borrow_date, due_date, return_date, status) VALUES
(1, 5, '2024-04-10', '2024-04-24', NULL, 'BORROWED'),
(7, 8, '2024-04-01', '2024-04-15', NULL, 'BORROWED'),
(12, 3, '2024-04-05', '2024-04-19', NULL, 'BORROWED'),
(14, 7, '2024-04-08', '2024-04-22', NULL, 'BORROWED'),
(18, 4, '2024-03-25', '2024-04-08', NULL, 'BORROWED'),
(20, 1, '2024-04-12', '2024-04-26', NULL, 'BORROWED');

-- ============================================================================
-- VERIFICATION QUERIES (Optional - for testing)
-- ============================================================================
-- SELECT * FROM authors;
-- SELECT * FROM books;
-- SELECT * FROM members;
-- SELECT * FROM borrow_records;
--
-- -- Get member with active borrow count
-- SELECT m.id, m.first_name, m.last_name, m.email, m.phone, m.membership_date, m.is_active, 
--        COUNT(br.id) as activeBorrows
-- FROM members m
-- LEFT JOIN borrow_records br ON m.id = br.member_id AND br.status = 'BORROWED'
-- WHERE m.id = 1
-- GROUP BY m.id;
--
-- -- Get member's borrow history with status
-- SELECT br.id, m.id as memberId, CONCAT(m.first_name, ' ', m.last_name) as memberName,
--        b.id as bookId, b.title as bookTitle, b.isbn as bookIsbn,
--        br.borrow_date, br.due_date, br.return_date,
--        CASE WHEN br.status = 'BORROWED' AND br.due_date < CURDATE() THEN 'OVERDUE' ELSE br.status END as status
-- FROM members m
-- JOIN borrow_records br ON br.member_id = m.id
-- JOIN books b ON br.book_id = b.id
-- WHERE m.id = 1
-- ORDER BY br.borrow_date DESC;
--
-- -- Get all overdue books
-- SELECT br.id, m.id as memberId, CONCAT(m.first_name, ' ', m.last_name) as memberName,
--        b.id as bookId, b.title as bookTitle, b.isbn as bookIsbn,
--        br.borrow_date, br.due_date, br.return_date,
--        CASE WHEN br.status = 'BORROWED' AND br.due_date < CURDATE() THEN 'OVERDUE' ELSE br.status END as status
-- FROM members m
-- JOIN borrow_records br ON br.member_id = m.id
-- JOIN books b ON br.book_id = b.id
-- WHERE br.status = 'OVERDUE' OR (br.status = 'BORROWED' AND br.due_date < CURDATE())
-- ORDER BY br.borrow_date DESC;
--
-- -- Get available books
-- SELECT * FROM books WHERE available_copies > 0;
--
-- -- Get books by author
-- SELECT * FROM books WHERE author_id = 2;
```

Now update your **README.md** to include a database setup section:

```markdown
## 🗄️ Database Setup

### Option 1: Using SQL Script

1. **Create the database and tables**:
   ```bash
   mysql -u your_username -p < database/schema.sql
   ```

2. **Verify the setup**:
   ```sql
   USE library_management;
   SELECT * FROM authors;
   SELECT * FROM books;
   SELECT * FROM members;
   SELECT * FROM borrow_records;
   ```

### Option 2: Manual Setup

1. Create the database:
   ```sql
   CREATE DATABASE library_management;
   ```

2. Execute the schema script from `database/schema.sql` in your MySQL client

### Database Schema Overview

The database consists of 4 main tables with the following relationships:

```
authors (1) ───┐
               ├──→ borrow_records (Many)
books (1) ─────┤
               ├──→ borrow_records (Many)
members (1) ───┘
```

**Foreign Keys**:
- `books.author_id` → `authors.id` (ON DELETE RESTRICT)
- `borrow_records.member_id` → `members.id` (ON DELETE RESTRICT)
- `borrow_records.book_id` → `books.id` (ON DELETE RESTRICT)
