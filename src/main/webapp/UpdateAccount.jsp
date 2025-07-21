<%@page import="com.Bank.DTO.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
    <title>Update Account</title>
    <style>
    /* General Styles */
body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
  background-color: #f4f4f4;
  color: #333;
}

/* Header Styles */
header {
  background: #333;
  color: #fff;
  padding: 1rem 0;
  text-align: center;
}

/* Form Section Styles */
.update-form {
  max-width: 500px;
  margin: 30px auto;
  background: #fff;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.update-form form {
  display: flex;
  flex-direction: column;
}

.update-form label {
  margin-top: 10px;
  font-weight: bold;
}

.update-form input {
  padding: 10px;
  margin-top: 5px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 16px;
}

.update-form button {
  margin-top: 20px;
  padding: 10px;
  background: #333;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
}

.update-form button:hover {
  background: #555;
}

/* Footer Styles */
footer {
  text-align: center;
  padding: 10px;
  background: #333;
  color: #fff;
  position: relative;
  bottom: 0;
  width: 100%;

    }
  </style>
</head>
<body>
<%Customer c=(Customer)session.getAttribute("customer"); %>
<%String success=(String)request.getAttribute("success");
 if(success!=null){%>
 <h3 class="success"><%=success%></h3>
 <%}%>
 <%String failure=(String)request.getAttribute("failure");
 if(failure!=null){%>
 <h3 class="failure"><%=failure%></h3>
 <%}%>

<form action="UpdateAccount" method="post">
    <table>
        <tr>
            <td><b>Enter the name to be updated:</b></td>
            <td><input type="text" name="name" value="<%= c.getName() %>"></td>
        </tr>
        <tr>
            <td><br></td>
            <td><br></td>
        </tr>
        <tr>
            <td><b>Enter the Phone number to be updated:</b></td>
            <td><input type="tel" name="phone" value="<%= c.getPhone() %>"></td>
        </tr>
        <tr>
            <td><br></td>
            <td><br></td>
        </tr>
        <tr>
            <td><b>Enter the Mail ID to be updated:</b></td>
            <td><input type="email" name="mail" value="<%= c.getMail() %>"></td>
        </tr>
    </table>
    <input type="submit" value="UpdateAccount">
</form>

</body>
</html> 