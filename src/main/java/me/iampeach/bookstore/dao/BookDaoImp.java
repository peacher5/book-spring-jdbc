package me.iampeach.bookstore.dao;

import me.iampeach.bookstore.model.Book;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class BookDaoImp implements BookDao {
    private JdbcTemplate jdbcTemplate;

    public BookDaoImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Book book) {
        String query = "INSERT INTO books (id, name, price) VALUES (?, ?, ?);";
        Object[] args = new Object[]{book.getId(), book.getName(), book.getPrice()};
        jdbcTemplate.update(query, args);
    }

    @Override
    public void update(int id, Book book) {
        String query = "UPDATE books SET id = ?, name = ?, price = ? WHERE id = ?;";
        Object[] args = new Object[]{book.getId(), book.getName(), book.getPrice(), id};
        jdbcTemplate.update(query, args);
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM books WHERE id = ?;";
        Object[] args = new Object[]{id};
        jdbcTemplate.update(query, args);
    }

    @Override
    public Book findById(int id) {
        String query = "SELECT * FROM books WHERE id = ?";
        Object[] args = new Object[]{id};
        return jdbcTemplate.queryForObject(query, args, new BookRowMapper());
    }

    @Override
    public List<Book> findAll() {
        String query = "SELECT * FROM books";
        return jdbcTemplate.query(query, new BookRowMapper());
    }
}
