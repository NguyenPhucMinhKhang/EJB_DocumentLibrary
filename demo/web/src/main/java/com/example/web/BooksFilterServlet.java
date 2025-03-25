package com.example.web;


import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.ejb.Book;
import com.example.ejb.BookBean;
import com.example.ejb.Category;
import com.example.ejb.CategoryBean;

import java.io.IOException;
import java.util.List;

@WebServlet("/books/filter")
public class BooksFilterServlet extends HttpServlet {
    @EJB
    private BookBean bookBean;

    @EJB
    private CategoryBean categoryBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId = request.getParameter("category");
        List<Book> books = bookBean.getBooksByCategoryId(Long.parseLong(categoryId));
        List<Category> categories = categoryBean.getAllCategories();
        request.setAttribute("categories", categories);
        request.setAttribute("books", books);
        request.getRequestDispatcher("/books.jsp").forward(request, response);
    }
}
