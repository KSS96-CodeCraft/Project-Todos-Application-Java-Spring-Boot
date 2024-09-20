<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>ToDo Management - Projects</title>
</head>
<body>
    <h1>Projects</h1>

    <h2>Create a New Project</h2>
    <form action="createProject" method="post">
        <label for="title">Project Title:</label>
        <input type="text" id="title" name="title" required>
        <button type="submit">Create</button>
    </form>

    <h2>All Projects</h2>
    <ul>
        <c:forEach var="project" items="${projects}">
            <li>
                <a href="/project/view?id=${project.id}">${project.title}</a>
            </li>
        </c:forEach>
    </ul>
</body>
</html>