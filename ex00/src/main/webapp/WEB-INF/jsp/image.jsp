<%request.setCharacterEncoding("UTF-8");%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>image</title>
</head>
<body>
<img src="data:image/png;base64,<%=request.getAttribute("image")%>" alt="qqqq" height="800" width="1200">
</body>