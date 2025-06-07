<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Current Match</title>
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/currentMatch.css">
</head>
<body>
<%@include file="header.jsp"%>

<div class="match-container">
    <h1>Match №</h1>

    <!-- Форма для игрока 1 -->
    <form class="score_btn" action="${pageContext.request.contextPath}/match-score?uuid=${param.uuid}" method="post">
        <div class="row">
            <div class="prev-sets">
                <h2>Previous sets</h2>
                <div class="set-boxes">
                    <div class="set-box"><p>${sessionScope.listOfWinners.get(sessionScope.player1).getSet()}</p></div>
                    <div class="set-box"><p>${sessionScope.listOfWinners.get(sessionScope.player1).getSet()}</p></div>
                    <div class="set-box"><p>${sessionScope.listOfWinners.get(sessionScope.player1).getSet()}</p></div>
                    <div class="set-box"><p>${sessionScope.listOfWinners.get(sessionScope.player1).getSet()}</p></div>
                </div>
            </div>

            <div class="players">
                <h2>Players</h2>
                <div class="player-box">
                    <p>${sessionScope.player1.name}</p>
                </div>
            </div>

            <div class="sets">
                <h2>Sets</h2>
                <div class="set-box">
                    <c:choose>
                        <c:when test="${sessionScope.set1 eq null}">
                            <p>0</p>
                        </c:when>
                        <c:otherwise>
                            <p>${sessionScope.set1}</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <div class="points">
                <h2>Points</h2>
                <div class="point-box">
                    <c:choose>
                        <c:when test="${sessionScope.point1 eq null}">
                            <p>0</p>
                        </c:when>
                        <c:otherwise>
                            <p>${sessionScope.point1}</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <input type="hidden" name="winner_id" value="${sessionScope.player1.id}" />
            <input type="submit" value="Score for 1" />
        </div>
    </form>

    <!-- Форма для игрока 2 -->
    <form class="score_btn" action="${pageContext.request.contextPath}/match-score?uuid=${param.uuid}" method="post">
        <div class="row">
            <div class="prev-sets">
                <h2>Previous sets</h2>
                <div class="set-boxes">
                    <div class="set-box"><p>${sessionScope.listOfWinners.get(sessionScope.player2).getSet()}</p></div>
                    <div class="set-box"><p>${sessionScope.listOfWinners.get(sessionScope.player2).getSet()}</p></div>
                    <div class="set-box"><p>${sessionScope.listOfWinners.get(sessionScope.player2).getSet()}</p></div>
                    <div class="set-box"><p>${sessionScope.listOfWinners.get(sessionScope.player2).getSet()}</p></div>
                </div>
            </div>

            <div class="players">
                <h2>Players</h2>
                <div class="player-box">
                    <p>${sessionScope.player2.name}</p>
                </div>
            </div>

            <div class="sets">
                <h2>Sets</h2>
                <div class="set-box">
                    <c:choose>
                        <c:when test="${sessionScope.set2 eq null}">
                            <p>0</p>
                        </c:when>
                        <c:otherwise>
                            <p>${sessionScope.set2}</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <div class="points">
                <h2>Points</h2>
                <div class="point-box">
                    <c:choose>
                        <c:when test="${sessionScope.point2 eq null}">
                            <p>0</p>
                        </c:when>
                        <c:otherwise>
                            <p>${sessionScope.point2}</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <input type="hidden" name="winner_id" value="${sessionScope.player2.id}" />
            <input type="submit" value="Score for 2" />
        </div>
    </form>
</div>

</body>
</html>
