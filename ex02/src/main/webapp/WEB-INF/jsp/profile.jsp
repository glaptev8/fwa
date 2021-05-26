<%@ page import="edu.school21.cinema.models.User" %>
<%@ page import="edu.school21.cinema.models.SignIn" %>
<%@ page import="java.io.File" %>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import = "java.util.ResourceBundle" %>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>signIn</title>
</head>
<body>
<h1>Profile</h1>
<% User user = (User)request.getSession().getAttribute("user"); %>
<%=user.getUserName()%>
<br/>
<%=user.getLastName()%>
<br/>
<%=user.getPhone()%>
<br/>
<% for (SignIn signIn: user.getSignInList()) {
  %>    <%=signIn.getIp() + " "%>
        <%=signIn.getDate()%>
        <br/>
  <%
}
if (request.getAttribute("pathImages") != null) {
  File path = new File((String) request.getAttribute("pathImages"));
  %> <ul><%
    for(File imageFile : path.listFiles()) {
        String imageFileName = imageFile.getName();
  %>
    <li>
        <a href="/images/<%=imageFileName%>" target="_blank"><%=imageFileName%></a>
    </li>
<%       }}%></ul>

<form enctype="multipart/form-data" method="post" action="/profile">
    <p><input type="file" name="image">
        <input type="submit" value="Отправить"></p>
</form>
</body>
</html>