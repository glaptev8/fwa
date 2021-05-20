<%@ page contentType="text/html;charset=utf-8" %>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>signIn</title>
</head>
<body>
<% if (request.getAttribute("error") != null) {%> <%=request.getAttribute("error")%><%
    }%>
<h1> User Login Form </h1>
<form method="POST" action="/signIn">
    <div class="container">
        <label>Username : </label>
        <input type="text" placeholder="Enter Username" name="username" required>
        <label>Password : </label>
        <input type="password" placeholder="Enter Password" name="password" required>
        <button type="submit">Login</button>
    </div>
</form>
</body>
</html>