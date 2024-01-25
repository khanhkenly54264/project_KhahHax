package com.example.lab04.servlet;

import com.example.lab04.conn;
import com.example.lab04.models.Customer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/edit")
public class EditCustomer extends HttpServlet {
    public EditCustomer() {
        super();
    }
    private static final Long serialVersionUID = 1L;
    private conn connection;
    @Override
    public void init() throws ServletException {
        connection = new conn();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cusIDre = request.getParameter("cusID");
        int  cusID = Integer.parseInt(cusIDre);
        Customer existingCustomer = null;
        try {
            existingCustomer = connection.selectCustomerByCusID(cusID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/edit-customer.jsp");
        request.setAttribute("customer", existingCustomer);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cuID = Integer.parseInt(request.getParameter("CuID"));

        String cusPass = request.getParameter("cusPass");
        String cusName = request.getParameter("cusName");
        String cusPhone = request.getParameter("cusPhone");
        String cusAdd = request.getParameter("cusAdd");
        String cusEmail = request.getParameter("cusEmail");
        String cusFacebook = request.getParameter("cusFacebook");
        String cusSkyper = request.getParameter("cusSkyper");
        int cusStatus = Integer.parseInt(request.getParameter("cusStatus"));
        Customer customer = new Customer(cuID, null, cusPass, cusName, cusPhone, cusAdd, cusEmail, cusFacebook, cusSkyper, cusStatus);
        try {
            connection.updateCustomer(customer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("list");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
}
