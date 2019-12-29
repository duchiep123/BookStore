<%-- 
    Document   : ShowCartPage
    Created on : Nov 25, 2019, 7:56:58 PM
    Author     : mrhie
--%>


<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Detail</title>
    </head>

    <script>
        function changeQuantity(count) {
            document.getElementById("quantity_" + count).value = document.getElementById("q_" + count).value;

        }
        function confirmRemove() {
            var c = confirm("Are you sure to remove this book.");
            if (c === true) {
                return true;
            } else {
                return false;
            }


        }


    </script>

    <body>
        <c:if test="${sessionScope.CUSTOMER !=null}">
            Welcome, ${sessionScope.CUSTOMER.fullname}<br/>
            <a href="logout">Logout</a>
        </c:if>
        <h1>Your Cart</h1>
        <c:set var="listBook" value="${sessionScope.CART.cart}"/>
        <c:if test="${listBook !=null}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Image</th>
                        <th>Book Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Update</th>
                        <th>Remove</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${listBook}" varStatus="counter">
                        <c:set var="book" value="${item.book}"/>



                        <tr>    
                            <td>${counter.count}</td>
                            <td>
                                <img src="${book.image}" style="height: 150px; width: 150px"/>

                            </td>
                            <td>
                                ${book.bookName}
                            </td>
                            <td>${book.price}</td>
                    <form action="updateQuantity" method="post" >
                        <td>
                            <c:forEach var="tmp" items="${sessionScope.LISTMAXQUANTITY}">
                                <c:if test="${tmp.bookID == book.bookID}">
                                    <input id="q_${counter.count}" onchange="changeQuantity(${counter.count})" type="number" name="quantity_${counter.count}" value="${item.quantity}" min="1" max="${tmp.quantity}" onkeydown="return false;"/>
                                </c:if>

                            </c:forEach>
                        </td>
                        <td><input type="submit" value="Update"/></td>
                    </form>
                    <td>
                        <form action="removeBookInCart" method="post" onsubmit="return confirmRemove();">
                            <input type="hidden" name="bookID" value="${book.bookID}"/>
                            <input type="submit"  value="Remove"/>
                        </form>
                    </td>

                </tr>
            </c:forEach>
        </tbody>
    </table>
            <b>Total Price: ${sessionScope.TOTALPRICE} $</b> <br/>
    <c:if test="${sessionScope.DISCOUNT.value ==null}">
        <form action="checkDiscount" method="post">
            <b>Discount Code:<input type="text" name="discountCode" value=""/><input type="submit" value="Check"/> </b>
        </form>
    </c:if>
    <c:if test="${sessionScope.DISCOUNT.value !=null}">
        <b>Discount: ${sessionScope.PERCENTDISCOUNT} % </b> <br/>
        <b>  Total Price after discount: ${sessionScope.TOTALPRICEAFTERDISCOUNT} $</b>
    </c:if>
    <c:if test="${requestScope.MesDiscount !=null}">
        <span style="color: red">${requestScope.MesDiscount}</span>
    </c:if>

    <form action="confirmBook" method="post">
        <c:forEach var="item" items="${listBook}" varStatus="counter">
            <input id="quantity_${counter.count}" type="hidden" name="quantity_${counter.count}" value="${item.quantity}"/>
        </c:forEach>
            <input type="hidden" name="totalPrice" value="${sessionScope.TOTALPRICE}"/>
        <input type="submit" value="Confirm"/>
    </form>
    <span style="color: red">
        <c:if test="${requestScope.LISTERRORQUANTITY !=null}">
            <c:forEach var="error" items="${requestScope.LISTERRORQUANTITY}">

                ${error}<br/>

            </c:forEach>
        </c:if>
    </span>
</c:if>
<c:if test="${listBook == null}">
    <h1>Your Cart is empty now.</h1>
</c:if>

<c:set var="Mes" value="${requestScope.Mes}"/>
<span style="color: green">
    <c:if test="${Mes!=null}">
        ${Mes}

    </c:if>
</span><br/>

<a href="search">Back</a>
</body>
</html>
