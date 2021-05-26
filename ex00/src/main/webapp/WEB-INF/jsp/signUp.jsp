<%@ page contentType="text/html;charset=utf-8" %>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>signUp</title>
</head>
<body>
<% if (request.getAttribute("error") != null) {%> <%=request.getAttribute("error")%><%
    }%>
<form method="POST" action="/signUp">
    <div class="container">
        <label>Username : </label>
        <input type="text" placeholder="Enter Username" name="username" required>
        <label>Password : </label>
        <input type="password" placeholder="Enter Password" name="password" required>
        <label>lastName : </label>
        <input type="text" placeholder="Enter lastName" name="lastName" required>
        <label>Phone : </label>
        <input type="text" placeholder="Enter phone" name="phone" required>
        <button type="submit">register</button>
    </div>
</form>
</body>
</html>