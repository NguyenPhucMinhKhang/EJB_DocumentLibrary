<!-- filepath: d:\Zidane\Nam4\HKII\KientrucThietKe\EJB_DocumentLibrary\demo\web\src\main\webapp\index.jsp -->
<%@ page import="com.example.ejb.BookBean, com.example.ejb.Book" %> <%@ page
import="javax.naming.InitialContext, javax.naming.NamingException" %> <%@ page
import="java.util.List" %> <% BookBean bookBean = null; try { InitialContext ic
= new InitialContext(); bookBean = (BookBean) ic.lookup("java:module/BookBean");
} catch (NamingException e) { e.printStackTrace(); } List<Book>
  books = bookBean != null ? bookBean.getAllBooks() : null; %>

  <html>
    <head>
      <title>Book List</title>
    </head>
    <body>
      <h1>Book List</h1>
      <ul>
        <% if (books != null) { for (Book book : books) { %>
        <li><%= book.getTitle() %> by <%= book.getAuthor() %></li>
        <% } } %>
      </ul>
    </body>
  </html></Book
>
