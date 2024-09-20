<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>ToDo Management - Project Details</title>
</head>
<body>
    <h1>Project: ${project.title}</h1>

    <h2>ToDos</h2>
    <form action="/todo/add" method="post">
        <input type="hidden" name="projectId" value="${project.id}">
        <label for="description">New ToDo:</label>
        <input type="text" id="description" name="description" required>
        <button type="submit">Add</button>
    </form>

    <ul>
    <c:forEach var="todo" items="${project.todos}">
        <li>
            <c:choose>
                <c:when test="${todo.status == 'PENDING'}">
                    <input type="checkbox" onclick="location.href='updateTodoStatus?id=${todo.id}&status=COMPLETED'">
                    ${todo.description}
                </c:when>
                <c:otherwise>
                    <input type="checkbox" checked disabled>
                    ${todo.description}
                </c:otherwise>
            </c:choose>
            <a href="/todo/delete?id=${todo.id}">[Delete]</a>
        </li>
    </c:forEach>
</ul>


    <form action="exportGist" method="post">
        <input type="hidden" name="projectId" value="${project.id}">
        <button type="submit">Export to Gist</button>
    </form>

    <br><br>
    <a href="/project/listProjects">Back to Projects</a>
</body>
</html>
