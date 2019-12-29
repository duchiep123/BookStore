<%-- 
    Document   : EditBookPage
    Created on : Nov 24, 2019, 6:38:17 PM
    Author     : mrhie
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Book Page</title>

        <script>
            function checkValidation() {
                var findError = false;
                var bookName = document.getElementById("bookName").value;
                var author = document.getElementById("author").value;
                var txtPrice = document.getElementById("price").value;
                if (bookName.trim().length === 0) {
                    document.getElementById("mesSuccess").innerHTML = "";
                    document.getElementById("errorBookName").innerHTML = "Book Name not allow empty.";
                    findError = true;
                }
                // 
                var pattern = new RegExp("^[a-zA-Z. ]+$");

                if (author.trim().length === 0) {
                    findError = true;
                    document.getElementById("mesSuccess").innerHTML = "";
                    document.getElementById("errorAuthor").innerHTML = "Author not allow empty.";
                } else if (pattern.test(author.trim()) === false) {
                    findError = true;
                    document.getElementById("mesSuccess").innerHTML = "";
                    document.getElementById("errorAuthor").innerHTML = "Author not allow number.";
                } else {
                    document.getElementById("errorAuthor").innerHTML = "";
                }
                var price = parseFloat(txtPrice.trim());
                pattern = new RegExp("^[0-9]+[0-9.]*$");
                if (pattern.test(txtPrice.trim()) === false) {
                    document.getElementById("mesSuccess").innerHTML = "";
                    findError = true;
                    document.getElementById("errorPrice").innerHTML = "Price is a number.";
                } else if (price <= 0) {
                    document.getElementById("mesSuccess").innerHTML = "";
                    findError = true;
                    document.getElementById("errorPrice").innerHTML = "Price must be bigger than 0.";
                } else {
                    document.getElementById("errorPrice").innerHTML = "";
                }

                if (findError) {
                    return false;
                } else {
                    return true;
                }

            }

        </script>
        <style>
            span{
                color: red;
            }
        </style>


    </head>
    <body>
        <h1>Book Information Page</h1>
        <c:set var="book" value="${requestScope.BOOK}"/>
        <form action="updateBook" method="post" onsubmit="return checkValidation();">
            <img src="${book.image}" style="height: 250px; width: 250px"/> <br/> <br/>
            BookID: ${book.bookID}
            <input type="hidden" name="bookID" value="${book.bookID}"/><br/>
            Book Name: <input id="bookName" type="text" name="txtBookName" value="${book.bookName}"/><span  id="errorBookName"></span><br/>
            Author:  <input id="author" type="text" name="txtAuthor" value="${book.author}"/><span  id="errorAuthor"></span><br/>
            Price: <input id="price" type="text" name="txtPrice" value="${book.price}"/><span  id="errorPrice"></span><br/>
            Category: 
            <select name="txtCategory">
                <c:forEach var="c" items="${requestScope.LISTCATEGORY}">
                    <option <c:if test="${c.category == book.category}">
                            selected="selected"
                        </c:if>value="${c.category}" >

                        ${c.description}
                    </option>
                </c:forEach>
            </select><br/>
            Quantity: <input required="" type="number" name="txtQuantity" value="${book.quantity}" onkeydown="return false;" min="1"/><br/>
            <input type="submit" name="butotn" value="Update Book"/>

        </form>
        <span id="mesSuccess" style="color: green">
            <c:if test="${requestScope.Mes !=null}">

                ${requestScope.Mes}
            </c:if>
        </span><br/>
        <a href="search">Back</a>





    </body>
</html>
