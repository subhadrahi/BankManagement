package com.Bank.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import com.Bank.DAO.CustomerDAOImpl;
import com.Bank.DAO.TransactionDAOImpl;
import com.Bank.DTO.Customer;
import com.Bank.DTO.Transaction;
import com.Bank.DTO.TransactionID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/Deposit")
public class Deposit extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Initialize DAO instances
        CustomerDAOImpl cdao = new CustomerDAOImpl();
        TransactionDAOImpl tdao = new TransactionDAOImpl();

        // Get parameters from the request
        String accno = request.getParameter("accno");
        double amount = 0;

        try {
            amount = Double.parseDouble(request.getParameter("amount"));
        } catch (NumberFormatException e) {
            response.getWriter().println("Invalid amount format!");
            return;
        }

        PrintWriter out = response.getWriter();

        if (amount <= 0) {
            out.println("Invalid amount! Please enter a positive value.");
            return;
        }

        // Retrieve customer details
       // Customer c = cdao.getCustomerByAccNo(accno);
        long accnoLong = Long.parseLong(accno);
        Customer c=cdao.getCustomer(accnoLong);
        if (c == null) {
            out.println("Customer account not found.");
            return;
        }

        // Update customer balance
        c.setBalance(c.getBalance() + amount);
        boolean res = cdao.updateCustomer(c);

        if (res) {
            // Create a transaction record
            Transaction t = new Transaction();
            t.setTransactionId(TransactionID.generateTransactionId());
            t.setUser(c.getAccno());
            t.setRec_acc(c.getAccno());
            t.setTransaction("CREDITED");
            t.setAmount(amount);
            t.setBalance(c.getBalance());

            boolean res1 = tdao.insertTransaction(t);

            if (res1) {
                out.println("Amount of Rs. " + amount + " has been successfully deposited.");
                out.println("Your updated balance is Rs. " + c.getBalance());
            } else {
                out.println("Error while saving transaction. Please contact support.");
            }
        } else {
            out.println("Failed to update customer details. Please try again.");
        }
    }
}
