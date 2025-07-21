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
@WebServlet("/ResetPin")
public class ResetPin extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, 
HttpServletResponse resp) throws ServletException, IOException {
	//collect the data from user
	 String phonenumber=req.getParameter("phone");
	 String mail=req.getParameter("mail");
	 String pin=req.getParameter("pin");
	 String confirmpin=req.getParameter("confirmpin");
	 
	 //conversion of datatypes
	 long phone=Long.parseLong(phonenumber);
	 int pinInt = Integer.parseInt(pin);
	 int confirmpinInt=Integer.parseInt(confirmpin);
	 PrintWriter out=resp.getWriter();
	 //JDBC Implementation
	 CustomerDAO cdao=new CustomerDAOImpl();
	 Customer c= cdao.getCustomer(phone, mail);
	 
	 if (c != null && phone == c.getPhone() && mail.equals(c.getMail())) {
         if (pinInt == confirmpinInt) {
             c.setPin(pinInt);
	 boolean result=cdao.updateCustomer(c);
	 if(result)
	 {
		 req.setAttribute("success", "ResetPin successful");
		 RequestDispatcher 
		rd=req.getRequestDispatcher("ResetPin.jsp");
		 rd.forward(req, resp);
	// out.println("<h1>Password updated successfully</h1>");
	 }
	 else
	 {
		 req.setAttribute("failure", "ResettPin failure");
		 RequestDispatcher 
		rd=req.getRequestDispatcher("ResettPin.jsp");
		 rd.forward(req, resp);
	// out.println("<h1>Failed to update the password</h1>");
	 }
	 }
}}}


