package com.example.ejb;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class BookBean {
    private List<Book> books = new ArrayList<>();

    public BookBean() {
        // Initialize with some fake data
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
        books.add(new Book("1984", "George Orwell"));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee"));
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getAllBooks() {
        return books;
    }
}
