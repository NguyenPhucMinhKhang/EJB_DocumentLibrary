package com.example.web;

import com.example.ejb.Book;
import com.example.ejb.BookBean;
import com.example.ejb.Category;
import com.example.ejb.CategoryBean;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

    private BookBean bookBean;
    private CategoryBean categoryBean;

    @Override
    public void init() throws ServletException {
        try {
            InitialContext ic = new InitialContext();
            bookBean = (BookBean) ic.lookup("java:global/demo-web-1.0/BookBean");
            categoryBean = (CategoryBean) ic.lookup("java:global/demo-web-1.0/CategoryBean");
        } catch (NamingException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookBean.getAllBooks();
        req.setAttribute("books", books);
        req.getRequestDispatcher("books.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        Long categoryId = Long.parseLong(req.getParameter("category"));
        Category category = categoryBean.findCategoryById(categoryId);

        if (category != null) {
            Book book = new Book(title, author, category);
            bookBean.addBook(book);
        }

        resp.sendRedirect("books");
    }
}