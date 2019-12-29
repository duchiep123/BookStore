<%-- 
    Document   : AdminPage
    Created on : Nov 23, 2019, 6:37:32 PM
    Author     : mrhie
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin page</title>
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
        Welcome, ${sessionScope.ADMIN} <br/>
        <a href="logout">Logout</a>
        <h1>Admin Page</h1>

        <form action="search" method="post" onsubmit="return check();">
            <div id="searchBox">
                Search : <input id="searchValue" type="text" name="searchValue" value="${sessionScope.searchValue}"/><span id="errorSearchValue"></span>
            </div>
            <div id="ByPrice" style="display: none">
                MinPrice: <input  type="text" name="txtMinPrice" value="${sessionScope.MinPrice}"/><span id="errorMinPrice"></span><br/>
                MaxPrice: <input type="text" name="txtMaxPrice" value="${sessionScope.MaxPrice}"/><span id="errorMaxPrice"></span><br/>
            </div>
            <select id="ByCategory" name="txtCategory" style="display: none">
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
        <c:if test="${listBook !=null}" >
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Image</th>
                        <th>Book Name</th>
                        <th>Price</th>
                        <th>Author</th>
                        <th>Category</th>
                        <th>Quantity</th>
                        <th>Status</th>
                        <th>Import Date</th>
                        <th>Remove Book</th>
                        <th>Edit</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach  var="book" items="${listBook}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td> <img src="${book.image}" style="height: 150px; width: 150px;" /></td>
                            <td>${book.bookName}</td>
                            <td>${book.price}</td>
                            <td>${book.author}</td>
                            <td>
                                <c:forEach var="c" items="${requestScope.LISTCATEGORY}">
                                    <c:if test="${book.category == c.category}">
                                        ${c.description}
                                    </c:if>

                                </c:forEach>


                            </td>
                            <td>${book.quantity}</td>
                            <td>
                                <c:forEach var="s" items="${sessionScope.LISTSTATUS}">
                                    <c:if test="${s.status == book.status}">
                                        ${s.description}
                                    </c:if>
                                </c:forEach>


                            </td>
                            <td>${book.importDate}</td>
                            <td>
                                <form action="removeBook" method="post" onsubmit="return confirmRemove();">
                                    <input type="hidden" name="bookID" value="${book.bookID}"/>


                                    <input type="submit" name="button" value="Remove"/>
                                </form>
                            </td>
                            <td>
                                <form action="editBook" method="post">
                                    <input type="hidden" name="bookID" value="${book.bookID}"/>
                                    <input type="submit" name="button" value="Edit"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${listBook ==null}">

        </c:if>

        <c:set var="Mes" value="${requestScope.Mes}"/>
        <span style="color: green">
            <c:if test="${Mes!=null}">
                ${Mes}
            </c:if>
        </span><br/>
        <a href="createcustomerpage.jsp">Create new customer</a><br/>
        <a href="goToPageCreateBook">Create new Book</a><br/>
        <a href="getAllUser">List all user</a>






    </body>
</html>
