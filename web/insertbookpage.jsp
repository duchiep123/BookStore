<%-- 
    Document   : InsertBookPage
    Created on : Nov 26, 2019, 9:47:16 AM
    Author     : mrhie
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Book Page</title>
    </head>

    <script>
        function SelectFile() {
            document.getElementById('file').click();
        }
        function ShowImg(event) {
            var files = event.target.files;// file co s

            var file = files[0]; // lay phan tu dau cua file

            var fileReader = new FileReader();

            fileReader.readAsDataURL(file);
            fileReader.onload = function () {

                var url = fileReader.result;
                document.getElementById("image").src = url;

                document.getElementById("picturePath").value = url; // gan vo bien de gui sang server

            };

        }

        function checkValidate() {
            var bookID = document.getElementById("bookID").value;
            var bookName = document.getElementById("bookName").value;
            var author = document.getElementById("author").value;
            var txtPrice = document.getElementById("price").value;
            var description = document.getElementById("description").value;
            var findError = false;
            var image = document.getElementById("image").src;
            //

            var pattern = new RegExp("^B\\d{6}$");
            if (pattern.test(bookID) === false) {
                document.getElementById("mesSuccess").innerHTML = "";
                document.getElementById("errorBookID").innerHTML = "Book ID must follow format Bxxxxxx (x is a digits)";
                findError = true;
            } else {
                document.getElementById("errorBookID").innerHTML = "";
            }
            if (bookName.trim().length === 0)
            {
                document.getElementById("mesSuccess").innerHTML = "";
                document.getElementById("errorBookName").innerHTML = "Book name not allow empty.";
                findError = true;
            } else {
                document.getElementById("errorBookName").innerHTML = "";
            }
            pattern = new RegExp("^[a-zA-Z. ]+$");

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
            if (description.trim().length === 0) {
                document.getElementById("mesSuccess").innerHTML = "";
                document.getElementById("errorDescription").innerHTML = "Description not allow empty.";
                findError = true;
            } else {
                document.getElementById("errorDescription").innerHTML = "";
            }
            if (image.length === 0) {

                document.getElementById("errorPicture").innerHTML = "Image not allow empty.";
                findError = true;
                document.getElementById("mesSuccess").innerHTML = "";
            } else {
                document.getElementById("errorPicture").innerHTML = "";
            }


            if (findError) {
                return false;
            } else {
                return true;
            }


        }


    </script>
    <body>
        <h1>Create New Book</h1>

        <form action="insertNewBook" method="post" onsubmit="return checkValidate();">
            <div style="height: 270px; width: 100%;  margin-top: 50px;" >
                <img id="image"  style="height: 240px; width: 240px; "/>
                <input id="file" onchange="ShowImg(event)" type="file" multiple="false" style="display: none;"accept="image/x-png,image/gif,image/jpeg"/><br/>
                <span style="color: red" id="errorPicture"></span><br/>
                <input type="button" value="Change image" onclick="SelectFile()" />
                <input type="hidden" id="picturePath" name="image"/> 

            </div><br/>


            BookID:  <input id="bookID" type="text" name="bookID"/> <span style="color: red" id="errorBookID"></span><br/>

            Book Name: <input id="bookName" type="text" name="bookName"  ><span style="color: red" id="errorBookName"></span><br/>
            Author:  <input id="author" type="text" name="author"><span style="color: red" id="errorAuthor"></span><br/>

            Price:  <input id="price" type="text" name="price" /><span style="color: red" id="errorPrice"></span><br/>
            Category: <select name="category">
                <c:forEach var="c" items="${requestScope.LISTCATEGORY}">
                    <option value="${c.category}">${c.description}</option>
                </c:forEach>
            </select>

            <br/>
            Quantity: <input type="number" name="quantity" min="0" value="0" onkeydown="return false;" style="width: 30px" /><br/>
            Description:<br/> <textarea id="description" name="description" rows="3" cols="50">
                
            </textarea><span style="color: red" id="errorDescription"></span><br/>
            <input type="submit" name="button" value="Create" /><br/>
        </form>
        <c:set var="Mes" value="${requestScope.Mes}"/>
        <span id="mesSuccess" >
            <c:if test="${Mes!=null}">
                ${Mes}
            </c:if>
        </span><br/>
        <a href="search">Back</a>


    </body>
</html>
