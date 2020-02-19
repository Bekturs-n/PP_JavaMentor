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
</head>
<body>
<table>
    <tr>
        <td>
            <h3>
                <%
                    User user = (User) request.getAttribute("users");
                %>
                You will change <%= user.getName()%>
            </h3>
        </td>
    </tr>
    <tr>
        <td>
            <form method="POST" action="/edit" >
                New name:<input name="name" value = "<%= user.getName()%>"><br>
                New surname:<input name="surname" value = "<%= user.getSuname()%>"><br>
                New age: <input name ="age" value="<%= user.getAge()%>"><br>
                <input name ="id" type="hidden" value="<%=user.getId()%>">
                <button type="submit">Change</button>
            </form>
        </td>
    </tr>
</table>
</body>
</html>
