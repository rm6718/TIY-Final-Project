<%--
  Created by IntelliJ IDEA.
  User: reevamerchant
  Date: 11/11/16
  Time: 2:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
<h1>Create Your Goal Entry</h1>
<form method="post" action="/mvc/secure/goals/list/create">
    <table class="table">
        <tr><td>Item:</td> <td><input type="text" name="item"></td></tr>
        <tr><td>Date To Be Completed By:</td> <td><input type="text" name="dateToBeCompleted"></td></tr>
        <tr><td>Comments:</td> <td><input type="text" name="comments"></td></tr>
    </table>
    <input type="submit" value="Save">
</form>


</body>
</html>
