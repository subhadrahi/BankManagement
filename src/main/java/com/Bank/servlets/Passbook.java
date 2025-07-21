package com.Bank.servlets;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;
import java.util.List;

import com.Bank.DAO.CustomerDAO;
import com.Bank.DAO.CustomerDAOImpl;
import com.Bank.DAO.TransactionDAO;
import com.Bank.DAO.TransactionDAOImpl;
import com.Bank.DTO.Customer;
import com.Bank.DTO.Transaction;

@WebServlet("/Passbook")
public class Passbook extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accnoStr = req.getParameter("accno");
        String pinStr = req.getParameter("pin");

        if (accnoStr == null || accnoStr.isEmpty() || pinStr == null || pinStr.isEmpty()) {
            req.setAttribute("error", "Account number and pin are required.");
            forwardToPage(req, resp);
            return;
        }

        try {
            long accno = Long.parseLong(accnoStr);
            int pin = Integer.parseInt(pinStr);

            CustomerDAO customerDAO = new CustomerDAOImpl();
            Customer customer = customerDAO.getCustomer(accno);

            if (customer == null || customer.getPin() != pin) {
                req.setAttribute("error", "Invalid account number or pin.");
                forwardToPage(req, resp);
                return;
            }

            TransactionDAO transactionDAO = new TransactionDAOImpl();
            List<Transaction> transactions = transactionDAO.getTransaction(accno);

            req.setAttribute("transactions", transactions);
            forwardToPage(req, resp);

        } catch (NumberFormatException e) {
            req.setAttribute("error", "Invalid input. Account number and pin must be numeric.");
            forwardToPage(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", "An unexpected error occurred: " + e.getMessage());
            forwardToPage(req, resp);
        }
    }

    private void forwardToPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("Passbook.jsp");
        dispatcher.forward(req, resp);
    }
}