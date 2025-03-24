package com.example.web;

import com.example.ejb.Book;
import com.example.ejb.BookBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/books/delete")
public class BookDeleteServlet extends HttpServlet {

    @EJB
    private BookBean bookBean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookIdStr = request.getParameter("id");
        if (bookIdStr == null) {
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
            bookBean.removeBookById(bookId);
            response.sendRedirect(request.getContextPath() + "/books?success=bookDeleted");
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/books?error=invalidId");
        }
    }
}
