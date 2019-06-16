<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals list</h2>

<table>
    <tr>
        <th>Time          </th>
        <th>        Description       </th>
        <th>     Calories    </th>
    </tr>

    <c:forEach var="mealsList" items="${mealsList}">
        <tr style="color: ${mealsList.isExcess() ? 'red' : 'yellow'}">
            <td>${mealsList.getDateTime().toString().replace("T", " ")}</td>
            <td>${mealsList.getDescription()}</td>
            <td>${mealsList.getCalories()}</td>
        </tr>
     </c:forEach>

</table>
</body>
</html>