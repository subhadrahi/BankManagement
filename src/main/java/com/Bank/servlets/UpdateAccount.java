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

@WebServlet("/UpdateAccount")
public class UpdateAccount extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, 
HttpServletResponse resp) throws ServletException, IOException {
	String name=req.getParameter("name");
	String phonenumber=req.getParameter("phone");
	 long phone=Long.parseLong(phonenumber);
	String mail=req.getParameter("mail");
	HttpSession session=req.getSession(false);
	Customer c=(Customer)session.getAttribute("customer");
	CustomerDAO cdao=new CustomerDAOImpl();
	if(c!=null)
	{
	 c.setName(name);
	 c.setPhone(phone);
	 c.setMail(mail);
	 boolean res=cdao.updateCustomer(c);
	 if(res)
{
		 req.setAttribute("success","Account updated  successfully");
				  RequestDispatcher 
				 rd=req.getRequestDispatcher("UpdateAccount.jsp");
				  rd.forward(req, resp);
				  }
				  else
				  {
				  req.setAttribute("failure","Failed to update");
				  RequestDispatcher 
				 rd=req.getRequestDispatcher("UpdateAccount.jsp");
				  rd.forward(req, resp);
				  }
				 }
				 else
				 {
				  req.setAttribute("failure","An error occured!, login again");
				  RequestDispatcher 
				 rd=req.getRequestDispatcher("UpdateAccount.jsp");
				  rd.forward(req, resp);
}}} 
	 
	 
	 
	 