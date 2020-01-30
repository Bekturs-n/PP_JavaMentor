<%--
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
</head>
<body>
<table border="1" width="80%">
    <tr>
        <td>
            ${firstColumn}
            <h5>
                ${info}
            </h5>
        </td>

        <td>
            ${secondColumn}
        </td>
    </tr>
    <tr>
        <td width="40%">
            ${message}
        </td>

        <td width="40%">
            <form method="GET" action="/users">
                Name<br>    <input name="name"> <br>
                Surname<br> <input name="surname"> <br>
                Age<br>     <input name="age" type="number"> <br><br>
                <input name="method" value="put" type = "hidden">
                <button type="submit"> AddUser</button>
            </form>
        </td>
    </tr>

</table>

</body>
</html>
