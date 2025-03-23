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
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", categoryBean.getAllCategories().get(2))); // Classic
        books.add(new Book("1984", "George Orwell", categoryBean.getAllCategories().get(1))); // Science Fiction
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", categoryBean.getAllCategories().get(2))); // Classic
        books.add(new Book("Pride and Prejudice", "Jane Austen", categoryBean.getAllCategories().get(2))); // Classic
        books.add(new Book("The Catcher in the Rye", "J.D. Salinger", categoryBean.getAllCategories().get(2))); // Classic
    }

    public void addBook(Book book) {
        books.add(book);
        
    }
    public void removeBookById(int id) {
        books.removeIf(book -> book.getId() == id);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void updateBook(Book oldBook, Book newBook) {
        int index = books.indexOf(oldBook);
        if (index != -1) {
            books.set(index, newBook);
        }
    }
    public List<Book> getAllBooks() {
        return books;
    }
    
    public List<Book> getBooksByCategory(Category category) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getCategory().equals(category)) {
                result.add(book);
            }
        }
        return result;
    }
}
