package com.example.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class BookBean {
    private List<Book> books = new ArrayList<>();

    @EJB
    private CategoryBean categoryBean; // Inject CategoryBean

    @PostConstruct
    public void init() {
        // Initialize with some fake data
        books.add(new Book(1L, "The Great Gatsby", "F. Scott Fitzgerald", categoryBean.getAllCategories().get(2))); // Classic
        books.add(new Book(2L, "1984", "George Orwell", categoryBean.getAllCategories().get(1))); // Science Fiction
        books.add(new Book(3L, "To Kill a Mockingbird", "Harper Lee", categoryBean.getAllCategories().get(2))); // Classic
        books.add(new Book(4L, "Pride and Prejudice", "Jane Austen", categoryBean.getAllCategories().get(2))); // Classic
        books.add(new Book(5L, "The Catcher in the Rye", "J.D. Salinger", categoryBean.getAllCategories().get(2))); // Classic
    }

    public void addBook(Book book) {
        books.add(book);
        
    }
    public void removeBookById(Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void updateBookById(Long id, Book newBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(id)) {
                books.set(i, newBook);
                break;
            }
        }
    }

    public List<Book> getAllBooks() {
        return books;
    }
    
    public List<Book> getBooksByCategoryId(Long categoryId) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getCategory().getId().equals(categoryId)) {
                result.add(book);
            }
        }
        return result;
    }

    public Book findBookById(Long id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }
}
