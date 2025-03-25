<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<base href="${pageContext.servletContext.contextPath}/">
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Book List</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f4f4f4;
    }

    .container {
      max-width: 900px;
      margin: auto;
      background: #fff;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    }

    header,
    footer {
      background: #007bff;
      color: #fff;
      text-align: center;
      padding: 15px 0;
      font-size: 1.2em;
    }

    .message {
      padding: 10px;
      border-radius: 5px;
      margin-bottom: 15px;
    }

    .error {
      background-color: #f8d7da;
      color: #721c24;
      border: 1px solid #f5c6cb;
    }

    .success {
      background-color: #d4edda;
      color: #155724;
      border: 1px solid #c3e6cb;
    }

    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
    }

    th,
    td {
      border: 1px solid #ddd;
      padding: 10px;
      text-align: left;
    }

    th {
      background-color: #007bff;
      color: white;
    }

    .btn {
      padding: 8px 12px;
      border: none;
      cursor: pointer;
      border-radius: 4px;
    }

    .btn-primary {
      background-color: #007bff;
      color: white;
    }

    .btn-danger {
      background-color: #dc3545;
      color: white;
    }

    .btn-secondary {
      background-color: #6c757d;
      color: white;
    }
  </style>
</head>

<body>
  <header>Book Management System</header>
  <div class="container">
    <h1>Book List</h1>

    <c:if test="${not empty param.error}">
      <div class="message error">
        <c:choose>
          <c:when test="${param.error == 'missingId'}">Error: Missing book ID.</c:when>
          <c:when test="${param.error == 'invalidId'}">Error: Invalid book ID.</c:when>
          <c:when test="${param.error == 'bookNotFound'}">Error: Book not found.</c:when>
        </c:choose>
      </div>
    </c:if>

    <c:if test="${not empty param.success}">
      <div class="message success">Success: Book deleted.</div>
    </c:if>

    <form action="books" method="get">
      <label for="category">Filter by Category:</label>
      <select name="category" id="category">
        <option value="all">ALL</option>
        <c:forEach var="category" items="${categories}">
          <option value="${category.id}">${category.name}</option>
        </c:forEach>
      </select>
      <button type="submit" class="btn btn-primary">Filter</button>
    </form>

    <button onclick="location.href='books/add'" class="btn btn-primary">Add New Book</button>

    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Title</th>
          <th>Author</th>
          <th>Category</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <c:choose>
          <c:when test="${not empty books}">
            <c:forEach var="book" items="${books}">
              <tr>
                <td>${book.id}</td>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.category.getName()}</td>
                <td>
                  <form action="books/delete" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="${book.id}">
                    <button type="submit" class="btn btn-danger">Delete</button>
                  </form>
                  <button onclick="location.href='books/edit?id=${book.id}'" class="btn btn-secondary">Edit</button>
                </td>
              </tr>
            </c:forEach>
          </c:when>
          <c:otherwise>
            <tr>
              <td colspan="5" style="text-align:center;">No books available.</td>
            </tr>
          </c:otherwise>
        </c:choose>
      </tbody>
    </table>
  </div>
  <footer>&copy; 2025 Book Management System</footer>
</body>

</html>