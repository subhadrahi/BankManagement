package com.Bank.servlets;

import com.Bank.DAO.CustomerDAO;
import com.Bank.DAO.CustomerDAOImpl;
import com.Bank.DAO.TransactionDAO;
import com.Bank.DAO.TransactionDAOImpl;
import com.Bank.DTO.Customer;
import com.Bank.DTO.Transaction;
import com.Bank.DTO.TransactionID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/TransferAmount")
public class TransferAmount extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Initialize DAO and transaction objects
        CustomerDAO cdao = new CustomerDAOImpl();
        Transaction t1 = null;
        Transaction t2 = null;
        TransactionDAO tdao = new TransactionDAOImpl();

        // Retrieve input from the form
        double amount = Double.parseDouble(request.getParameter("amount"));
        long receiverAccno = Long.parseLong(request.getParameter("receiver_accno"));
        int pin = Integer.parseInt(request.getParameter("pin"));
        long senderAccno = Long.parseLong(request.getParameter("accno"));

     // Fetch the sender and receiver details
        Customer sender = cdao.getCustomer(senderAccno);
        Customer receiver = cdao.getCustomer(receiverAccno);

        // Logic to transfer amount
        if (sender != null && receiver != null) {
            if (sender.getAccno() != receiver.getAccno() && sender.getBalance() > 0 && sender.getBalance() >= amount && amount > 0) {
                if (pin == sender.getPin()) {
                    // Debit the sender's account
                    sender.setBalance(sender.getBalance() - amount);
                    boolean senderUpdated = cdao.updateCustomer(sender);
                    if (senderUpdated) {
                        t1 = new Transaction();
                        t1.setTransactionId(TransactionID.generateTransactionId());
                        t1.setUser(sender.getAccno());
                        t1.setRec_acc(receiver.getAccno());
                        t1.setTransaction("DEBITED");
                        t1.setAmount(amount);
                        t1.setBalance(sender.getBalance());

                        boolean t1Inserted = tdao.insertTransaction(t1);
                    }

                    // Credit the receiver's account
                    receiver.setBalance(receiver.getBalance() + amount);
                    boolean receiverUpdated = cdao.updateCustomer(receiver);
                    if (receiverUpdated) {
                        t2 = new Transaction();
                        t2.setTransactionId(t1.getTransactionId());
                        t2.setUser(receiver.getAccno());
                        t2.setRec_acc(receiver.getAccno());
                        t2.setTransaction("CREDITED");
                        t2.setAmount(amount);
                        t2.setBalance(receiver.getBalance());
                        
                        boolean t2Inserted = tdao.insertTransaction(t2);
                    }

                    // Set success message and forward to result page
                    if (senderUpdated && receiverUpdated) {
                        request.setAttribute("message", "Transaction Successful!");
                    } else {
                        request.setAttribute("message", "Transaction Failed!");
                    }
                } else {
                    request.setAttribute("message", "Incorrect PIN. Transaction Failed!");
                }
            } else {
                request.setAttribute("message", "Invalid transaction details. Transaction Failed!");
            }
        } else {
            request.setAttribute("message", "Account details not found. Transaction Failed!");
        }

        // Forward to transferResult.jsp with the result message
        request.getRequestDispatcher("TransferResult.jsp").forward(request, response);
    }
}