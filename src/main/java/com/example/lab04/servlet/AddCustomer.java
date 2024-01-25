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

@WebServlet("/new")
public class AddCustomer extends HttpServlet {
    public AddCustomer() {
        super();
    }
    private static final Long serialVersionUID = 1L;
    private conn connection;
    @Override
    public void init() throws ServletException {
        connection = new conn();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/customer-form-new.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String cusUser = request.getParameter("cusUser");
        String cusPass = request.getParameter("cusPass");
        String cusName = request.getParameter("cusName");
        String cusPhone = request.getParameter("cusPhone");
        String cusAdd = request.getParameter("cusAdd");
        String cusEmail = request.getParameter("cusEmail");
        String cusFacebook = request.getParameter("cusFacebook");
        String cusSkyper = request.getParameter("cusSkyper");
        int cusStatus = Integer.parseInt(request.getParameter("cusStatus"));

        Customer newCustomer = new Customer(0,cusUser, cusPass, cusName, cusPhone, cusAdd, cusEmail, cusFacebook, cusSkyper, cusStatus);
        try {
            connection.insertCustomer(newCustomer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("customer-list");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }


}
