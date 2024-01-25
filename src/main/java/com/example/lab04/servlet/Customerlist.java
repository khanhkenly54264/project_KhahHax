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
import java.util.List;

@WebServlet("/customer-list")
public class Customerlist extends HttpServlet {
    private static final Long serialVersionUID = 1L;
    private conn connection;
    @Override
    public void init() throws ServletException {
        connection = new conn();
    }
    public Customerlist() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customers = null;
        try {
            customers = connection.selectAllCustomers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("customers", customers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer-list.jsp");
        dispatcher.forward(request, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }


}
