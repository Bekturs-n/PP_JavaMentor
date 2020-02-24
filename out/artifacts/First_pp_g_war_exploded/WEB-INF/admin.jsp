<%@ page import="java.util.stream.Collectors" %>
<%@ page import="Model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.concurrent.atomic.AtomicLong" %>
<%@ page import="Service.UserServiceImpl" %><%--
  Created by IntelliJ IDEA.
  User: Beka
  Date: 11.01.2020
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        div.wrapper {
            width: 450px;
        }

        div.left_block {
            float: left;
            width: 70%;
            height: 30px;
        }

        div.right_block {
            float: right;
            width: 30%;
            height: 30px;
        }
    </style>
</head>
<body>
<table border="1" width="100%">
    <tr>
        <td width="30%"><h3>All Users </h3></td>
        <td><h3>Add new User</h3></td>
    </tr>
    <tr>

        <td width="40%">
            <%
                AtomicLong i = new AtomicLong(0);
                UserServiceImpl userService = (UserServiceImpl) request.getAttribute("users");
                List<User> list = userService.getAllUser();
                for (User user : list) {
            %>
            <div class="wrapper" style="padding-left: 10px">
                <div class="left_block">
                    <%= user.getName()%>
                    <%= user.getPassword()%>
                    <%= user.getAge()%>
                </div>
                <div class="right_block">
                    <div class="left_block">
                        <form method="GET" action="/admin/delete">
                            <input name="id" value="<%= user.getId()%>" type="hidden">
                            <button type="submit"> Delete</button>
                        </form>
                    </div>
                    <div class="right_block">
                        <form method="GET" action="/admin/edit">
                            <input name="id" value="<%= user.getId()%>" type="hidden">
                            <button type="submit"> Edit</button>
                        </form>
                    </div>
                </div>
            </div>
            <%
                }
            %>
            ${message}
        </td>

        <td width="40%"><div style="padding-left: 10px">
            <form method="POST" action="/admin/add">
                Name(login)<br> <input name="name" type="email"> <br>
                Password<br> <input name="surname" type="password"> <br>
                Age<br> <input name="age" type="number"> <br>
                Role:<br> <input type="radio" name="role" value="USER" checked> User
                <input type="radio" name="role" value="ADMIN"> Admin <br><br>
                <button type="submit"> Add User</button>
            </form>
        </div>
            <h2>
                ${info}
            </h2>
        </td>
    </tr>
</table>
<form method="GET" action="/out">
    <button type="submit"> Выход </button>
</form>
</body>
</html>
