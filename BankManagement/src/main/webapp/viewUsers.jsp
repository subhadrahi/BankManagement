
<%@page import="com.Bank.DAO.CustomerDAOImpl"%>
<%@page import="com.Bank.DAO.CustomerDAO"%>
<%@page import="com.Bank.DTO.Customer"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initialscale=1.0">
 <title>View Users</title>
 <style>
 .heading { 
color: #000080;
}
.sub {
color: #1e90ff;
}
.success
{
color:green;
}
.failure
{
color:red;
}
.buttons
{
text-decoration: none;
}
 
 </style>
 <link
href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstr
ap.min.css"
 rel="stylesheet">
</head>
<body>
<%Customer c1=(Customer)session.getAttribute("customer");%>
 <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
 <div class="container-fluid">
 <a class="navbar-brand text" href="#">Welcome 
Admin:<%=c1.getName() %></a>
 <button class="navbar-toggler" type="button" data-bstoggle="collapse" data-bs-target="#navbarNav" ariacontrols="navbarNav" aria-expanded="false" aria-label="Toggle 
navigation">
 <span class="navbar-toggler-icon"></span>
 </button>
 <div class="collapse navbar-collapse" id="navbarNav">
 <ul class="navbar-nav ms-auto">
 <li class="nav-item">
 <a class="btn btn-primary"
href="dashboard.jsp">Back</a>
 </li>
 <li class="nav-item">
 </li>
 </ul>
</div>
 </div>
 </nav>
 <div class="container mt-4">
 <h1 class="text-center mb-4">Customer Data</h1>
</div>
 <%String success=(String)request.getAttribute("success");
 if(success!=null){%>
 <h3 class="success"><%=success%></h3> 
 <%}%>
 <%String failure=(String)request.getAttribute("failure");
 if(failure!=null){%>
 <h3 class="failure"><%=failure%></h3> 
 <%}%>
<!-- User Section -->
 <div class="row">
 <div class="col-md-12">
 <input type="search" value="Search here..!">
 <table class="table table-hover">
 <thead>
 <tr>
 <th>Accno</th>
 <th>Name</th>
 <th>Phone</th>
 <th>Mail ID</th>
 <th>Balance</th>
 <th>Remove</th>
 </tr> 
 </thead>
 <%CustomerDAO cdao=new CustomerDAOImpl();
 ArrayList<Customer> 
 customers=(ArrayList<Customer>)cdao.getCustomer();
 Iterator<Customer> it=customers.iterator();%>
 <tbody>
 <% while(it.hasNext()){
 Customer c=it.next();%>
 <tr>
 <td><%=c.getAccno()%></td>
 <td><%=c.getName()%></td>
 <td><%=c.getPhone()%></td>
 <td><%=c.getMail()%></td>
 <td><%=c.getBalance()%></td>
 
 <td><form action="DeleteUser" method="post">
 <input type="hidden" name="id"
value="<%=c.getAccno()%>">
 <input class="btn btn-danger" type="submit"
value="Delete">
 </form>
 </td>
 </tr>
 <%}%>
 </tbody>
 </table>
 </div>
 </div>
</body>
</html>
 