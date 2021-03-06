package com.company.todo.controller;

import com.company.todo.Helpers.JspUrlResolver;
import com.company.todo.model.TaskEntity;
import com.company.todo.model.TodoDaoException;
import com.company.todo.model.UserEntity;
import com.company.todo.model.UsersDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by rami.moshe on 4/29/2015.
 */
@WebServlet(name = "UsersController", urlPatterns = { "/controller/users/*" })
public class UsersController extends HttpServlet {
    private UsersDao usersDao;

    public UsersController(){
        super();
        usersDao = new UsersDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        RequestDispatcher dispatcher;
        UserEntity user;
        switch (path) {
            case "/register":
                user = new UserEntity();
                user.setUsername(request.getParameter("un"));
                user.setPassword(request.getParameter("pw"));
                boolean isValidUser = validateUser(user);
                if (!isValidUser){
                    redirectToErrorPage(request, response, "invalid username or password");
                }

                user.setIsDeleted(false);
                user.setDateCreated(new java.sql.Date(new Date().getTime()));
                try {
                    usersDao.addUser(user);
                    login(request, response, user);

                } catch (TodoDaoException e) {
                    e.printStackTrace();
                    //TODO: move to error page
                }

                break;

            case "/login":
                try {
                    user = new UserEntity();
                    user.setUsername(request.getParameter("un"));
                    user.setPassword(request.getParameter("pw"));

                    login(request, response, user);
                } catch (TodoDaoException e) {
                    response.getWriter().print(e.getStackTrace());
                }

                break;

            default:
                String homeView = JspUrlResolver.getJspUrl("index.jsp");
                response.sendRedirect(homeView);
        }
    }

    private void redirectToErrorPage(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
        request.setAttribute("content", errorMessage);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(JspUrlResolver.getJspUrl("/main_exception_page.jsp"));
        dispatcher.forward(request, response);
    }

    private boolean validateUser(UserEntity user) {
        if (user.getUsername() == null || user.getUsername().length() < 3 ||
            user.getPassword() == null || user.getPassword().length() < 3){
            return false;
        }

        return true;
    }

    private void login(HttpServletRequest request, HttpServletResponse response, UserEntity user) throws TodoDaoException, IOException, ServletException {
        //TODO: handle the exceptions
        RequestDispatcher dispatcher;
        boolean isLoginSuccessful = usersDao.loginUser(user);
        if (isLoginSuccessful) {
            HttpSession session = request.getSession(true);
            session.setAttribute("currentSessionUser", user);
            ServletContext context = getServletContext();

            response.sendRedirect("/controller/tasks");
        } else {
            dispatcher = getServletContext().getRequestDispatcher(JspUrlResolver.getJspUrl("/invalid_login.jsp"));
            dispatcher.forward(request, response);
        }
    }
}
