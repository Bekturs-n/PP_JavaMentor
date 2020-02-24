<%--
  Created by IntelliJ IDEA.
  User: Beka
  Date: 24.01.2020
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <style>
        div.wrapper {
            width: 200px;
            height: 200px;
        }

        div.left_block {
            float: left;
            width: 40%;
            height: 30px;
        }

        div.right_block {
            float: right;
            width: 60%;
            height: 30px;
            text-align: center;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <div class="left_block">
        Login:<br><br>
        Password:<br><br>
    </div>
    <div class="right_block">
        <form method="GET" action="/auth">
            <input name="login" > <br><br>
            <input name="password" type="password"><br><br>
            <button type="submit">Login</button>
        </form>
    </div>

</div>
<h3 style="color: red"> ${message}</h3>
</body>
</html>
