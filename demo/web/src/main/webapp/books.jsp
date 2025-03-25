<!-- filepath: d:\Zidane\Nam4\HKII\KientrucThietKe\EJB_DocumentLibrary\demo\web\src\main\webapp\index.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
  <title>Book List</title>
</head>

<body>
  <h1>Book List</h1>
  <c:if test="${not empty param.error}">
    <div style="color: red;">
      <c:choose>
        <c:when test="${param.error == 'missingId'}">Error: Missing book ID.</c:when>
        <c:when test="${param.error == 'invalidId'}">Error: Invalid book ID.</c:when>
        <c:when test="${param.error == 'bookNotFound'}">Error: Book not found.</c:when>
      </c:choose>
    </div>
  </c:if>
  <c:if test="${not empty param.success}">
    <div style="color: green;">
      <c:choose>
        <c:when test="${param.success == 'bookDeleted'}">Success: Book deleted.</c:when>
      </c:choose>
    </div>
  </c:if>
  <form action="<%= request.getContextPath() %>/books/filter" method="get">
    <label for="category">Filter by Category:</label>
    <select name="category" id="category">
      <c:forEach var="category" items="${categories}">
        <option value="${category.id}">${category.name}</option>
      </c:forEach>
    </select>
    <button type="submit">Filter</button>
  </form>
  <ul>
    <c:choose>
      <c:when test="${not empty books}">
      <c:forEach var="book" items="${books}">
        <li>
        <strong>${book.title}</strong> by ${book.author} <br>
        <em>Category:</em> ${book.category.getName()} <br>
        <em>ID:</em> ${book.id} <br>
        <form action="<%= request.getContextPath() %>/books/delete" method="post" style="display:inline;">
          <input type="hidden" name="id" value="${book.id}">
          <button type="submit">Delete</button>
        </form>
        <button onclick="location.href='books/edit?id=${book.id}'">Update</button>
        </li>
        <hr>
      </c:forEach>
      </c:when>
      <c:otherwise>
      <li>No books available.</li>
      </c:otherwise>
    </c:choose>
    <br>
    <button onclick="location.href='books/add'">Add New Book</button>
  </ul>
</body>

</html>