<%--
  Created by IntelliJ IDEA.
  User: reevamerchant
  Date: 11/15/16
  Time: 2:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Edit</title>
</head>
<body>
Please edit your goal and click 'Update'!

    <form action="/mvc/secure/goals/list/save" method="post">
        <table>
            <tr> <td>Item:</td><td><input type="text" name="item" value="<c:out value="${item}"/>"></td></tr>
            <tr><td>Date To Be Completed By:</td><td><input type="text" name="dateToBeCompleted" value="<c:out value="${dateToBeCompleted}"/>"></td></tr>
            <tr> <td>Comments:</td><td><input type="text" name="comments" value="<c:out value="${comments}"/>"></td></tr>

        </table>
        <input type="submit" name="Update" />
        <input type="hidden" name="id" value="<c:out value="${id}"/>"/>
    </form>
</body>
</html>