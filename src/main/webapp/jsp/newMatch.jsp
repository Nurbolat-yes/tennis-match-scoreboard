<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Match</title>
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/newMatch.css">
</head>
<body>
<%@include file="header.jsp"%>
<div class="form-container">
    <form action="${pageContext.request.contextPath}/new-match" method="post">
        <div class="form-row">
            <div class="form-group">
                <label for="player1">Player 1</label>
                <input type="text" id="player1" name="player1" value="M. Saken">
            </div>

            <div class="form-group">
                <label for="player2">Player 2</label>
                <input type="text" id="player2" name="player2" value="M. Ali">
            </div>
        </div>

        <input type="submit" value="Start">
    </form>
</div>
</body>
</html>
