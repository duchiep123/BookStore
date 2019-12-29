<%-- 
    Document   : ShoppingPage
    Created on : Nov 23, 2019, 6:42:47 PM
    Author     : mrhie
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Page</title>
    </head>
    <script>
        window.onload = checkForm;

        function checkForm() {
            var by = sessionStorage.getItem("kind");
            if (by !== null) {
                if (by === "ByName") {

                    document.getElementById("searchBox").style.display = "block";
                } else {
                    document.getElementById("searchBox").style.display = "none";
                }
                if (by === "ByPrice") {

                    document.getElementById("ByPrice").style.display = "block";
                } else {
                    document.getElementById("ByPrice").style.display = "none";
                }

                if (by === "ByCategory") {

                    document.getElementById("ByCategory").style.display = "block";
                } else {
                    document.getElementById("ByCategory").style.display = "none";
                }
            }
        }
        function changeForm() {

            var by = document.getElementById("By").value;

            if (by === "ByName") {
                sessionStorage.setItem("kind", "" + by);
                document.getElementById("searchBox").style.display = "block";
            } else {
                document.getElementById("searchBox").style.display = "none";
            }
            if (by === "ByPrice") {
                sessionStorage.setItem("kind", "" + by);
                document.getElementById("ByPrice").style.display = "block";
            } else {
                document.getElementById("ByPrice").style.display = "none";
            }

            if (by === "ByCategory") {
                sessionStorage.setItem("kind", "" + by);
                document.getElementById("ByCategory").style.display = "block";
            } else {
                document.getElementById("ByCategory").style.display = "none";
            }

        }

        function check() {

            var by = sessionStorage.getItem("kind");
            var findError = false;
            if (by !== null) {
                if (by === "ByName") {
                    var searchValue = document.getElementById("searchValue").value;
                    if (searchValue.trim().length === 0) {
                        document.getElementById("errorSearchValue").innerHTML = "Search value not allow empty.";
                        findError = true;
                    } else {
                        document.getElementById("errorSearchValue").innerHTML = "";
                    }

                }
                if (by === "ByPrice") {
                    var pattern = new RegExp("^[0-9]+[0-9.]*$");
                    var minPrice = document.getElementById("minPrice").value;
                    var maxPrice = document.getElementById("maxPrice").value;
                    if (pattern.test(minPrice) === false) {
                        findError = true;
                        document.getElementById("errorMinPrice").innerHTML = "Wrong format price.";
                    } else {
                        document.getElementById("errorMinPrice").innerHTML = "";
                    }
                    if (pattern.test(maxPrice) === false) {
                        document.getElementById("errorMaxPrice").innerHTML = "Wrong format price.";
                        findError = true;

                    } else {
                        document.getElementById("errorMaxPrice").innerHTML = "";
                    }
                    if (findError === false) {
                        if (parseFloat(minPrice) > parseFloat(maxPrice)) {
                            findError = true;
                            document.getElementById("errorMinPrice").innerHTML = "MinPrice must be smaller than MaxPrice";
                        } else {
                            document.getElementById("errorMinPrice").innerHTML = "";
                        }
                    }

                }

                if (by === "ByCategory") {

                    document.getElementById("ByCategory").style.display = "block";
                }
            } else { // lan dau search
                var searchValue = document.getElementById("searchValue").value;
                if (searchValue.trim().length === 0) {
                    document.getElementById("errorSearchValue").innerHTML = "Search value not allow empty.";
                    findError = true;
                } else {
                    document.getElementById("errorSearchValue").innerHTML = "";
                }
            }
            if (findError) {
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
        <c:if test="${sessionScope.CUSTOMER ==null}">
            <a href="loginpage.jsp">
                Sign in
            </a>
        </c:if>
        <h1>Shopping</h1>
        <form action="search" method="post" onsubmit="return check();">
            <div id="searchBox">
                Search : <input id="searchValue" type="text" name="searchValue" value="${sessionScope.searchValue}"/><span id="errorSearchValue"></span>
            </div>
            <div id="ByPrice" style="display: none">
                MinPrice: <input id="minPrice"  type="text" name="txtMinPrice" value="${sessionScope.MinPrice}"/><span id="errorMinPrice"></span><br/>
                MaxPrice: <input id="maxPrice" type="text" name="txtMaxPrice" value="${sessionScope.MaxPrice}"/><span id="errorMaxPrice"></span><br/>
            </div>
            Search By: <select id="ByCategory" name="txtCategory" style="display: none" >
                <c:forEach var="category" items="${requestScope.LISTCATEGORY}">
                    <option <c:if test="${sessionScope.searchCategory == category.description}">selected="selected"</c:if>>
                        ${category.description}
                    </option>
                </c:forEach>

            </select>
            <select name="kind" id="By" onchange="changeForm()">
                <option value="ByName"<c:if test="${param.kind == 'ByName'}">selected="selected"</c:if> >Name</option>
                <option value="ByPrice" <c:if test="${param.kind == 'ByPrice'}">selected="selected"</c:if>>Range of money</option>
                <option value="ByCategory"<c:if test="${param.kind == 'ByCategory'}">selected="selected"</c:if> >Category</option>

                </select>
                <input type="submit" name="button" value="Search"/>

            </form>

        <c:set var="listBook" value="${requestScope.LISTBOOK}"/>
        <c:if test="${listBook !=null}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Image</th>
                        <th>Book Name</th>
                        <th>Author</th>
                        <th>Price</th>
                        <th>Buy</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="book" items="${requestScope.LISTBOOK}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>
                                <img src="${book.image}" style="height: 150px; width: 150px;" />


                            </td>
                            <td>${book.bookName}</td>
                            <td>${book.author}</td>
                            <td>${book.price}</td>
                            <td>
                                <form action="showDetailBook" method="post">
                                    <input type="hidden" name="bookID" value="${book.bookID}"/>
                                    <input type="submit" name="button" value="Detail"/>

                                </form>
                            </td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${sessionScope.CUSTOMER !=null}">
            
            <a href="showHistoryOrder">View your history order.</a><br/>

        </c:if>
        <a href="showCartDetail">View Your Cart</a>

    </body>
</html>
