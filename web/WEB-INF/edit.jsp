<%@ page import="Model.User" %><%--
  Created by IntelliJ IDEA.
  User: Beka
  Date: 19.02.2020
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
    <style>
        div.wrapper {
            width: 450px;
        }

        div.left_block {
            float: left;
            width: 30%;
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
<table>
    <tr>
        <td>
            <h3>
                <%
                    User user = (User) request.getAttribute("user");
                %>
                You will change <%= user.getName()%>
            </h3>
        </td>
    </tr>
    <tr>
        <td>
            <form method="POST" action="/admin/edit">
                <div class="wrapper">
                    <div class="left_block">
                        New name(login):<br>
                        New password:<br>
                        New age:<br>
                    </div>
                    <div class="right_block">
                        <input name="name" value="<%= user.getName()%>" type="email"><br>
                        <input name="surname" value="<%= user.getPassword()%>" type="password"><br>
                        <input name="age" value="<%= user.getAge()%>"><br>
                        <input name="id" type="hidden" value="<%=user.getId()%>">
                        <br>
                        <button type="submit">Change</button>
                    </div>
                </div>
            </form>
        </td>
    </tr>
</table>
</body>
</html>
