<%-- 
    Document   : listuserpage
    Created on : Dec 9, 2019, 5:00:02 PM
    Author     : mrhie
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List User</title>
    </head>
    <body>
        <h1>List User</h1>

        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>UserID</th>
                    <th>Fullname</th>
                    <th>Tel</th>
                    <th>Address</th>

                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${requestScope.LISTUSER}" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${user.userID}</td>
                        <td>${user.fullname}</td>
                        <td>${user.tel}</td>
                        <td>${user.address}</td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
