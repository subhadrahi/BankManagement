package com.Bank.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import com.Bank.DAO.CustomerDAO;
import com.Bank.DAO.CustomerDAOImpl;
import com.Bank.DTO.Customer;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Login")
public class Login extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
 //creating asession object
	HttpSession session=req.getSession();
//collecting the data from the user
	String accno = req.getParameter("accno");
    String pin = req.getParameter("pin");
		 PrintWriter out=resp.getWriter();
		 
         long accnoLong = Long.parseLong(accno);
         int pinInt = Integer.parseInt(pin);
         CustomerDAO cdao = new CustomerDAOImpl();
         Customer c = cdao.getCustomer(accnoLong, pinInt);
         if(c!=null)
		 {
        	 if(c.getAccno()==1100110011) {
        		 session.setAttribute("customer", c);
    			 RequestDispatcher rd=req.getRequestDispatcher("admindashboard.jsp");
    			 rd.forward(req,resp);
        	 }
        	 session.setAttribute("customer", c);
			 RequestDispatcher rd=req.getRequestDispatcher("dashboard.jsp");
			 rd.forward(req,resp);
//			 req.setAttribute("success", "Login successful");
//		 RequestDispatcher 
//			rd=req.getRequestDispatcher("Login.jsp"); 
//        	 out.println("<h1>Login successful, Welcome "+c.getName()+"</h1>"
//        			  + "<br>"
//        			  + "<a href=\"Login.html\">Back</a>");
        			  }
        			  else
        			  {
        				  
       					 req.setAttribute("Failure", "fail to Login ");
        					 RequestDispatcher 
        					rd=req.getRequestDispatcher("Login.jsp");
        			//  out.println("<h1>Invalid mail or password!</h1>" + "<br>"+ "<a href=\"Login.html\">Back</a>");
        			  }
}
}
        	 
        	 
        	 
    