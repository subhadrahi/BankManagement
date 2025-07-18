package com.Bank.servlets;

import java.io.IOException;

import com.Bank.DAO.CustomerDAOImpl;
import com.Bank.DTO.Customer;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        // Retrieve account number from the request
        String accnoStr = req.getParameter("accno");

        // Check if the account number is not null or empty
        if (accnoStr != null && !accnoStr.isEmpty()) {
            try {
                long accno = Long.parseLong(accnoStr);
                System.out.println("Account number to delete: " + accno);

                // Retrieve session and check for logged-in user
                HttpSession session = req.getSession(false);
                if (session == null) {
                    req.setAttribute("failure", "Session expired. Please log in again.");
                    req.getRequestDispatcher("Login.jsp").forward(req, resp);
                    return;
                }

                Customer admin = (Customer) session.getAttribute("customer");

                // Ensure the admin cannot delete their own account
                if (admin.getAccno() == accno) {
                    req.setAttribute("failure", "Admin data cannot be deleted.");
                    req.getRequestDispatcher("viewUsers.jsp").forward(req, resp);
                    return;
                }

                // Perform the deletion process
                CustomerDAOImpl cdao = new CustomerDAOImpl();
                Customer c1 = new Customer();
                c1.setAccno(accno);

                boolean result = cdao.deleteCustomer(c1);

                if (result) {
                    req.setAttribute("success", "Data deleted successfully.");
                } else {
                    req.setAttribute("failure", "Failed to delete the data.");
                }

                // Forward the response to the view page
                req.getRequestDispatcher("viewUsers.jsp").forward(req, resp);

            } catch (NumberFormatException e) {
                req.setAttribute("failure", "Invalid account number format.");
                req.getRequestDispatcher("viewUsers.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("failure", "Account number is required.");
            req.getRequestDispatcher("viewUsers.jsp").forward(req, resp);
        }
    }
}