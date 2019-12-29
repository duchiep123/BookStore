<%-- 
    Document   : createcustomerpage
    Created on : Nov 26, 2019, 11:33:44 PM
    Author     : mrhie
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <script>
        function checkError() {
            var userID = document.getElementById("userID").value;
            var fullname = document.getElementById("fullname").value;
            var password = document.getElementById("password").value;
            var confirm = document.getElementById("confirm").value;
            var tel = document.getElementById("tel").value;
            var address = document.getElementById("address").value;
            var findError = false;
            //

            if (password.search(" ") !== -1 || password.length === 0) {
                document.getElementById("mesSuccess").innerHTML = "";
                document.getElementById("errorPassword").innerHTML = "Password not allow contain space character or empty.";
                findError = true;
            } else if (password.search(" ") === -1 || password.length > 0) {
                document.getElementById("errorPassword").innerHTML = "";
            }
            if (findError === false) {
                if (password !== confirm) {
                    document.getElementById("mesSuccess").innerHTML = "";
                    document.getElementById("errorConfirm").innerHTML = "Confirm password is not match with password.";
                    findError = true;

                } else {
                    document.getElementById("errorConfirm").innerHTML = "";

                }
            }
            if (userID.search(" ") !== -1 || userID.length === 0) {
                document.getElementById("mesSuccess").innerHTML = "";
                document.getElementById("errorUserID").innerHTML = "UserID not allow empty or space character.";
                findError = true;
            } else {
                document.getElementById("errorUserID").innerHTML = "";
            }


            var pattern = new RegExp("^[a-zA-Z ]+$");
            if (pattern.test(fullname) === false) {
                document.getElementById("mesSuccess").innerHTML = "";
                document.getElementById("errorFullname").innerHTML = "Fullname not allow number or empty";
                findError = true;
            } else {
                document.getElementById("errorFullname").innerHTML = "";

            }
            pattern = new RegExp("^[0-9]{10}$");
            if (pattern.test(tel) === false) {
                document.getElementById("errorTel").innerHTML = "Tel is 10 digits.";
                findError = true;
            } else {
                document.getElementById("errorTel").innerHTML = "";

            }
            pattern = new RegExp("^[a-zA-Z0-9 ]+$");
            if (pattern.test(address) === false) {
                document.getElementById("mesSuccess").innerHTML = "";
                document.getElementById("errorAddress").innerHTML = "Address not allow empty or special character.";
                findError = true;
            } else {
                document.getElementById("errorAddress").innerHTML = "";

            }

            if (findError) {
                return false;
            } else {
                return true;
            }

        }

    </script>
    <body>
        <h1>Create Customer</h1>
        <form action="createNewCustomer" method="post" onsubmit="return checkError();">
            UserID: <input id="userID" type="text" value="" name="userID"/><span style="color: red" id="errorUserID"></span><br/>
            Fullname: <input id="fullname" type="text" name="fullname"/><span style="color: red" id="errorFullname"></span><br/>
            Password: <input id="password" type="password" name="password" value=""/><span style="color: red" id="errorPassword"></span><br/>
            Confirm: <input  type="password" id="confirm" value="" /><span style="color: red" id="errorConfirm"></span><br/>
            Tel: <input id="tel" type="text" name="tel" value=""/><span style="color: red" id="errorTel"></span><br/>
            Address <input id="address" type="text" name="address" value="" /><span style="color: red" id="errorAddress"></span><br/>

            <input type="submit" value="Create"/>
        </form>
        <span id="mesSuccess">
            <c:set var="Mes" value="${requestScope.Mes}"/>
                <c:if test="${Mes!=null}">
                    ${Mes}

                </c:if>
            
        </span><br/>
        <a href="search">Back</a>

    </body>
</html>
