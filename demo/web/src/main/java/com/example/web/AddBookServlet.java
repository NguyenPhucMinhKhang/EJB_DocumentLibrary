package com.example.web;

import com.example.ejb.Book;
import com.example.ejb.BookBean;
import com.example.ejb.Category;
import com.example.ejb.CategoryBean;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/books/add")
public class AddBookServlet extends HttpServlet {

    @EJB
    private BookBean bookBean;

    @EJB
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (categoryBean == null) {
            response.sendRedirect(request.getContextPath() + "/books/add?error=categoryBeanUnavailable");
            return;
        }

        List<Category> categories = categoryBean.getAllCategories();
        if (categories == null) {
            response.sendRedirect(request.getContextPath() + "/books/add?error=noCategories");
            return;
        }

        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/addBook.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (categoryBean == null) {
            response.sendRedirect(request.getContextPath() + "/books/add?error=categoryBeanUnavailable");
            return;
        }

        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String categoryName = request.getParameter("category");

        if (title == null || author == null || categoryName == null) {
            response.sendRedirect(request.getContextPath() + "/books/add?error=missingParameters");
            return;
        }

        Category category = categoryBean.getAllCategories().stream()
                .filter(c -> c.getName().equals(categoryName))
                .findFirst()
                .orElse(null);

        if (category == null) {
            response.sendRedirect(request.getContextPath() + "/books/add?error=invalidCategory");
            return;
        }

        Book book = new Book(title, author, category);
        bookBean.addBook(book);

        response.sendRedirect(request.getContextPath() + "/books");
    }
}
