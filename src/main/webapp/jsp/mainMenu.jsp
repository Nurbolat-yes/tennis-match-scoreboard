<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Menu</title>
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/mainMenu.css">
</head>
<body>
<%@include file="header.jsp"%>
<main>
    <div class="main-menu-container">
        <a href="${pageContext.request.contextPath}/new-match">New Match</a>
        <a href="${pageContext.request.contextPath}/matches">Completed Matches</a>
    </div>
</main>
</body>
</html>
