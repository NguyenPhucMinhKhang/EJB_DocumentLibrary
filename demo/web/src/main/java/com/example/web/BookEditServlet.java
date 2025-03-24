package com.example.web;

import com.example.ejb.Book;
import com.example.ejb.BookBean;
import com.example.ejb.Category;
import com.example.ejb.CategoryBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/books/edit")
public class BookEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private BookBean bookBean;

    @EJB
    private CategoryBean categoryBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookIdStr = request.getParameter("id");
        if (bookIdStr == null || bookIdStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/books?error=missingId");
            return;
        }

        try {
            Long bookId = Long.parseLong(bookIdStr);
            Book book = bookBean.findBookById(bookId);

            List<Category> categories = categoryBean.getAllCategories();

            request.setAttribute("categories", categories);
            request.setAttribute("book", book);
            request.getRequestDispatcher("/bookEdit.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/books?error=invalidId");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookIdStr = request.getParameter("id");
        if (bookIdStr == null || bookIdStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/books?error=missingId");
            return;
        }

        try {
            Long bookId = Long.parseLong(bookIdStr);
            Book book = bookBean.findBookById(bookId);
            if (book == null) {
                response.sendRedirect(request.getContextPath() + "/books?error=bookNotFound");
                return;
            }

            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String categoryName = request.getParameter("category");

            if (title == null || author == null || categoryName == null) {
                response.sendRedirect(request.getContextPath() + "/books/edit?id=" + bookId + "&error=missingParameters");
                return;
            }

            Category category = categoryBean.findCategoryByName(categoryName);
            if (category == null) {
                response.sendRedirect(request.getContextPath() + "/books/edit?id=" + bookId + "&error=invalidCategory");
                return;
            }

            book.setTitle(title);
            book.setAuthor(author);
            book.setCategory(category);
            bookBean.updateBookById(bookId, book);

            response.sendRedirect(request.getContextPath() + "/books?success=bookUpdated");
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/books?error=invalidId");
        }
    }
}
