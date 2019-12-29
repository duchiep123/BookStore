<%-- 
    Document   : LoginPage
    Created on : Nov 23, 2019, 5:56:52 PM
    Author     : mrhie
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <form action="login" method="post">
            UserID: <input type="text" name="txtUserID" value=""/><br/>
            Password: <input type="password" name="txtPassword" value=""/><br/>
            <input type="hidden" name="bookID" value="${param.bookID}"/>
            <input type="submit" name="button" value="Login"/>
        </form>
            <c:if test="${requestScope.MesError !=null}">
                <span style="color: red">${requestScope.MesError}</span><br/>
            </c:if>
        <a href="shoppingpage.jsp">Shopping now</a>
    </body>
</html>
