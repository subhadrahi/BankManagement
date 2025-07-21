<%@page import="com.Bank.DTO.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initialscale=1.0">
        <title>Bank Management</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
    <%Customer c=(Customer)session.getAttribute("customer"); %>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand navbar-primary" href="#">Welcome <%=c.getName()%> </a>
        <button class="navbar-toggler" type="button" data-bstoggle="collapse" data-bs-target="#navbarNav" ariacontrols="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" Accno="navbarNav">
        <ul class="navbar-nav ms-auto">
 <%if(c.getAccno()==1100110011){ %>
        <li class="nav-item">
 <a class="nav-link" href="viewUsers.jsp">View Data</a>
 </li>
 <%}%>
            <li class="nav-item">
                <a class="nav-link" href="Deposit.jsp">Deposit</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="TransferAmount.jsp">TransferAmount</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="Passbook.jsp">Passbook</a>
            </li>
           
            <li class="nav-item">
                <a class="nav-link" href="ResetPin.jsp">Reset Pin</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="UpdateAccount.jsp">Update Data</a>
            </li>
            <li class="nav-item">
                 <form action="logout" method="post">
 <input class="btn btn-danger" type="submit"
name="logout" value="Logout">
 </form>
            </li>
        </ul>
    </div>
</div>
</nav>
<div class="container mt-4">
    <h1 class="text-center mb-4">dashboard</h1>
</div>
<!-- User Section -->
 <div class="row">
    <div class="col-md-12">
        <h3>View Your Data</h3>
         <%String not=(String)request.getAttribute("invalid");
 if(not!=null){ %>
 <h3 class="notDelete"><%=not%></h3>
 <%}%>
 <form action="search" method="post">
 <label>enter the user Accno</label>
 <input type="number" name="Accno">
 <button type="submit" value="search" placeholder="search here">Search</button>
 </form>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>Accno</th>
                    <th>Name</th>
                    <th>Phone</th>
                    <th>Mail ID</th>
                    <th>Balance</th>
                </tr>
            </thead>
            <tbody>
<tr>
 <td><%=c.getAccno() %></td>
 <td><%=c.getName() %></td>
 <td><%=c.getPhone() %></td>
 <td><%=c.getMail() %></td>
 <td><%=c.getBalance() %></td>
 
 </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>