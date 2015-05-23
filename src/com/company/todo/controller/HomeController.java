package com.company.todo.controller;

import com.company.todo.Helpers.JspUrlResolver;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by rami.moshe on 4/29/2015.
 */
@WebServlet(name = "HomeController", urlPatterns = { "/home/*" })
public class HomeController extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher;
        String path = request.getPathInfo();
        if (path == null) path = "";

        switch (path) {
            case "/loginPage":
                dispatcher = getServletContext().getRequestDispatcher(JspUrlResolver.getJspUrl("/login_page.jsp"));
                dispatcher.forward(request, response);
                break;
            case "/registerPage":
                dispatcher = getServletContext().getRequestDispatcher(JspUrlResolver.getJspUrl("/register_page.jsp"));
                dispatcher.forward(request, response);
                break;
            default:
                String homeView = JspUrlResolver.getJspUrl("index.jsp");
                dispatcher = getServletContext().getRequestDispatcher(homeView);
                dispatcher.forward(request, response);
        }
    }
}
