<!-- filepath: d:\Zidane\Nam4\HKII\KientrucThietKe\EJB_DocumentLibrary\demo\web\src\main\webapp\index.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
  <title>Book List</title>
</head>

<body>
  <h1>Book List</h1>
  <ul>
    <c:choose>
      <c:when test="${not empty books}">
        <c:forEach var="book" items="${books}">
          <li>${book.title} by ${book.author}</li>
        </c:forEach>
      </c:when>
      <c:otherwise>
        <li>No books available.</li>
      </c:otherwise>
    </c:choose>
  </ul>
</body>

</html>
</Book>