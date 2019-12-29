<%-- 
    Document   : historydetailpage
    Created on : Nov 28, 2019, 9:43:03 PM
    Author     : mrhie
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History detail</title>
    </head>
    <body>
        <c:if test="${sessionScope.CUSTOMER !=null}">
            Welcome, ${sessionScope.CUSTOMER.fullname}<br/>
            <a href="logout">Logout</a>
        </c:if>
        <h1>Detail history</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Book Name</th>
                    <th>Quantity</th>
                    <th>Unit Price</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="detail" items="${requestScope.LISTDETAIL}" varStatus="counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${detail.bookName}</td>
                        <td>${detail.quantity}</td>
                        <td>${detail.unitPrice}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <c:url var="link" value="searchOrder">
            <c:param value="${param.date}" name="date"/>

        </c:url>
        <a href="${link}">Back</a>

    </body>
</html>
