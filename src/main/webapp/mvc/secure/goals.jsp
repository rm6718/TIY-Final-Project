<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
    response.setHeader("Cache-Control", "no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Latest compiled and minified CSS -->
    <script
            src="https://code.jquery.com/jquery-3.1.1.min.js"
            integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
            crossorigin="anonymous"></script>
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script>
        function markComplete(id) {
            window.location = 'http://localhost:8080/mvc/secure/user/accomplished/add?id='+id;
        }
    </script>

    <script>
        function markNotComplete(id) {
            window.location = 'http://localhost:8080/mvc/secure/user/accomplished/remove?id='+id;
        }
    </script>
</head>

<body>

<div class="container">
    <div class="header clearfix">
        <nav>
            <ul class="nav nav-pills pull-right">
                <li role="presentation" class="active"><a href="/mvc/secure/goals/all">Home</a></li>
                <c:if test="${user_loggedin_perms.containsKey('ADMIN_ADD_USER')}">
                    <li role="presentation"><a href="/mvc/secure/admin/users">Users</a></li>
                </c:if>
                <li role="presentation"><a href="/mvc/secure/creategoal.jsp">Create New Goal</a></li>
                <li role="presentation"><a href="/mvc/open/logout">Logout</a></li>
            </ul>
        </nav>
        <h3 class="text-muted" style="font-style: italic" >Wishful</h3>
    </div>






<!--margin - left: 370px  -->
    <div style="display: flex; flex-direction: column; align-content: center; text-align: center;" >

            <h4 align="center">Here's Your Bucket List</h4>

            <table class="table">
                <thead>
                <tr>
                    <th align="center">Completed</th>
                    <th align="center">Goal</th>
                    <th align="center">Date To Be Completed By</th>
                    <th align="center">Comments</th>
                    <th align="center">Edit Goal</th>
                    <th align="center">Delete Goal</th>
                </tr>
                </thead>
                <tbody>


                <c:forEach items="${notcompleted_goals}" var="aGoal">
                    <tr>
                        <td align="center"><input type="checkbox" id="myCheck" onclick="markComplete(${aGoal.id})"></td>
                        <td align="center"><c:out value="${aGoal.item}"/></td>
                        <td align="center"><c:out value="${aGoal.dateToBeCompleted}"/></td>
                        <td align="center"><c:out value="${aGoal.comments}"/></td>
                        <td align="center"><a href="/mvc/secure/goals/list/edit?id=<c:out value="${aGoal.id}"/>">Edit</a></td>
                        <td align="center"><a href="/mvc/secure/goals/list/delete?id=<c:out value="${aGoal.id}"/>">Delete</a></td>
                    </tr>

                </c:forEach>


                </tbody>
            </table>





        <div style="margin-top:20px"></div>




                <h4 align="center">Your Completed Goals</h4>

                <p/>

                <table class="table">
                    <thead>
                    <tr>
                        <th align="center">Completed</th>
                        <th align="center">Goal</th>
                        <th align="center">Date To Be Completed By</th>
                        <th align="center">Comments</th>
                        <th align="center">Edit Goal</th>
                        <th align="center">Delete Goal</th>
                    </tr>
                    </thead>
                    <tbody>


                    <c:forEach items="${completed_goals}" var="aGoal">

                        <tr>
                            <td align="center"><input type="checkbox" id="myCheck2" checked="checked" onclick="markNotComplete(${aGoal.id})"></td>
                            <td align="center"><c:out value="${aGoal.item}"/></td>
                            <td align="center"><c:out value="${aGoal.dateToBeCompleted}"/></td>
                            <td align="center"><c:out value="${aGoal.comments}"/></td>
                            <td align="center"><a href="/mvc/secure/goals/list/edit?id=<c:out value="${aGoal.id}"/>">Edit</a></td>
                            <td align="center"><a href="/mvc/secure/goals/list/delete?id=<c:out value="${aGoal.id}"/>">Delete</a></td>
                        </tr>

                    </c:forEach>


                    </tbody>
                </table>



    </div>


    <footer class="footer">
        <p>&copy; 2016 Company, Inc.</p>
    </footer>



</body>
</html>