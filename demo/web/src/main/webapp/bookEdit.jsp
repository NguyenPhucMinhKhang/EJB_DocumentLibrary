<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Book</title>
</head>
<body>
    <h1>Edit Book</h1>
    <form action="${pageContext.request.contextPath}/books/edit" method="post">
        <input type="hidden" name="id" value="${book.id}">
        
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" value="${book.title}" required><br><br>
        
        <label for="author">Author:</label>
        <input type="text" id="author" name="author" value="${book.author}" required><br><br>
        
        <label for="category">Category:</label>
        <select id="category" name="category" required>
            <c:forEach var="category" items="${categories}">
            <option value="${category.name}" ${category.name == book.category.getName() ? 'selected="selected"' : ''}>
                ${category.name}
            </option>
            </c:forEach>
        </select><br><br>
        
        <input type="submit" value="Update Book">

        
    </form>
</body>
</html>