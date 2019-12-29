<%-- 
    Document   : DetailBookPage
    Created on : Nov 25, 2019, 7:15:16 PM
    Author     : mrhie
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail Book</title>
    </head>

    <script>
        function checkAccountToBuy(user) {

            if (user === undefined) {
                alert("You must sign in to buy book.");
                return false;
            } else {
                return true;
            }


        }
    </script>

    <body>
        <c:set var="book" value="${requestScope.BOOK}"/>
        <c:if test="${sessionScope.CUSTOMER !=null}" >
            Welcome, ${sessionScope.CUSTOMER.fullname}
        </c:if>
        <c:if test="${sessionScope.CUSTOMER ==null}" >

            <a href="loginpage.jsp?bookID=${book.bookID}">Sign in to BUY NOW</a>
        </c:if>   
        <h1>Book Detail</h1>

        <form action="buyBook" method="post" onsubmit="return checkAccountToBuy(${sessionScope.CUSTOMER});">
            <input type="hidden" name="bookID" value="${book.bookID}"/>
            <img src="${book.image}"  style="width: 250px; height: 270px"/><br/>
            Book Name: ${book.bookName}<br/>
            Author: ${book.author}<br/>
            Description: <br/>
            <textarea readonly="true"  rows="4" cols="50">${book.description}</textarea>  <br/>
            Category: 
            <c:forEach var="c" items="${requestScope.LISTCATEGORY}">
                <c:if test="${c.category == book.category}">
                    ${c.description}

                </c:if>
            </c:forEach>


            <br/>
            Price: ${book.price} $<br/>
            Quantity: <input type="number" name="quantity" value="1" min="1" max="${book.quantity}" onkeydown="return false;"/>(Max Quantity: ${book.quantity}) <br/>
            <input type="submit" value="Buy"/>


        </form>
        <span style="color: green">
            <c:if test="${requestScope.Mes !=null}">
                ${Mes}
            </c:if>

        </span><br/>

        <a href="search">Back</a>
    </body>
</html>
