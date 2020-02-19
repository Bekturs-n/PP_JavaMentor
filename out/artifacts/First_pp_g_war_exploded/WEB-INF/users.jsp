<%@ page import="java.util.stream.Collectors" %>
<%@ page import="Model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="Service.UserServiceHibernate" %>
<%@ page import="java.util.concurrent.atomic.AtomicLong" %><%--
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
                UserServiceHibernate userServiceHibernate = (UserServiceHibernate) request.getAttribute("users");
                List<User> list = userServiceHibernate.getAllUser();
                for (User user : list) {
            %>
            <div class="wrapper">
                <div class="left_block">
                    <%= user.getName()%>
                    <%= user.getSuname()%>
                    <%= user.getAge()%>
                </div>
                <div class="right_block">
                    <div class="left_block">
                        <form method="GET" action="/delete">
                            <input name="id" value="<%= user.getId()%>" type="hidden">
                            <button type="submit"> Delete</button>
                        </form>
                    </div>
                    <div class="right_block">
                        <form method="GET" action="/edit">
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

        <td width="40%">
            <form method="POST" action="/users">
                Name<br> <input name="name"> <br>
                Surname<br> <input name="surname"> <br>
                Age<br> <input name="age" type="number"> <br><br>
                <input name="method" value="put" type="hidden">
                <button type="submit"> AddUser</button>
            </form>
        </td>
    </tr>

</table>

</body>
</html>
