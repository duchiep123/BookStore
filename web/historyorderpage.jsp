<%-- 
    Document   : historyorderpage
    Created on : Nov 28, 2019, 6:45:14 PM
    Author     : mrhie
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Order Page</title>
    </head>
    <script>
        function checkDate() {
            var date = document.getElementById("date").value;
            if (date === "") {
                alert("Date search must be a valid date.");
                return false;
            } else {
                return true;
            }
        }

    </script>
    <body>
        <c:if test="${sessionScope.CUSTOMER !=null}">
            Welcome, ${sessionScope.CUSTOMER.fullname}<br/>
            <a href="logout">Logout</a>
        </c:if>
        <h1>History order</h1>

        <form action="searchOrder" method="post" onsubmit="return checkDate();">
            Search by date:   <input id="date" type="date" name="date" value="${param.date}" /> <input type="submit" value="Search"/>
        </form>
        <c:if test="${requestScope.LISTHISTORYORDER !=null}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Create Date</th>
                        <th>Total Price</th>
                        <th>Detail</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" items="${requestScope.LISTHISTORYORDER}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${order.createDate}</td>
                            <td>${order.totalPrice}</td>
                            <td>
                                <form action="showOrderDetailHistory" method="post">
                                    <input type="hidden" name="date" value="${param.date}"/>
                                    <input type="hidden" name="orderID" value="${order.orderID}"/>
                                    <input type="submit" value="Detail"/>
                                </form>

                            </td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${requestScope.LISTHISTORYORDER ==null and requestScope.MesHistory ==null}">
            <h1>Not Found</h1>
        </c:if>
        <c:if test="${requestScope.MesHistory !=null}">
            <h1>${requestScope.MesHistory}</h1>
        </c:if>
        <a href="search">Back</a>
    </body>
</html>
