<%@page import="com.Bank.DTO.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admindashboard</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
<% Customer c=(Customer)session.getAttribute("customer");
if(c==null){
response.sendRedirect("Login.jsp");
return;
}
%>
<div class="container mt-5">
        <h1 class="text-center">Welcome Admin: <%= c.getName() %></h1>
        <div class="text-center mt-4">
            
            <a href="viewUsers.jsp" class="btn btn-warning">View Data</a><br><br>
            <a href="ResetPin.jsp" class="btn btn-warning">ResetPin</a><br><br>
            <form action="logout" method="post">
 <input class="btn btn-danger" type="submit"
name="logout" value="Logout">
 </form>
            
        </div>
    </div>
</body>
</html>