<!-- filepath: d:\Zidane\Nam4\HKII\KientrucThietKe\EJB_DocumentLibrary\web\addBook.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <html>
      <head>
        <title>Add Book</title>
      </head>
      <body>
        <h1>Add Book</h1>
        <form action="<%= request.getContextPath() %>/books/add" method="post">
          <label for="title">Title:</label>
          <input type="text" id="title" name="title" required /><br />

          <label for="author">Author:</label>
          <input type="text" id="author" name="author" required /><br />

          <label for="category">Category:</label>
          <select id="category" name="category" required>
            <c:forEach var="category" items="${categories}">
              <option value="${category.name}">
                ${category.name}
              </option>
            </c:forEach>
          </select><br />

          <input type="submit" value="Add Book" />
        </form>
      </body></html></Category
></Category>
