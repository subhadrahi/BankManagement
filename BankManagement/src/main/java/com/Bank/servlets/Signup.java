package com.Bank.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import com.Bank.DAO.CustomerDAO;
import com.Bank.DAO.CustomerDAOImpl;
import com.Bank.DTO.Customer;

@WebServlet("/Signup")
public class Signup extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Collect the data from user
        String name = req.getParameter("name");
        String phoneNumber = req.getParameter("phone");
        String mailId = req.getParameter("mail");
        String pinStr = req.getParameter("pin");
        String confirmPinStr = req.getParameter("confirmPin");

        // Convert necessary data types
        long phone = Long.parseLong(phoneNumber);
        int pin = Integer.parseInt(pinStr);
        int ConfirmPin = Integer.parseInt(confirmPinStr);
        PrintWriter out = resp.getWriter();

        // JDBC Implementation
        Customer c = new Customer();
        
       // CustomerDAOImpl dao = new CustomerDAOImpl();
        CustomerDAO cdao=new CustomerDAOImpl();
        if (pin==ConfirmPin) {
            c.setName(name);
            c.setPhone(phone);
            c.setMail(mailId);
            c.setPin(pin);
            boolean result = cdao.insertCustomer(c);
            if (result) {
            	 req.setAttribute("success", "Signup successful");
        		 RequestDispatcher 
        		rd=req.getRequestDispatcher("Signup.jsp");
        		 rd.forward(req, resp);
//            	c=cdao.getCustomer(ConfirmPin, mailId);
//                out.println("<h1>Signup successful</h1>");
            } else {
            	 req.setAttribute("failure", "Signup failure");
        		 RequestDispatcher 
        		rd=req.getRequestDispatcher("Signup.jsp");
        		 rd.forward(req, resp);
                //out.println("<h1>Signup failed</h1>");
            }
        } else {
            out.println("<h1>Pin and Confirm Pin do not match</h1>");
        }
    }}
