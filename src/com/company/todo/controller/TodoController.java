package com.company.todo.controller;

import com.company.todo.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by rami on 28/04/2015.
 */
@WebServlet(name = "TodoController", urlPatterns = { "/controller/*" })
public class TodoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private TodoTasksDao todoTasksDao;
    private UsersDao usersDao;

    public TodoController(){
        super();
        //todoTasksDao = new TodoTasksDao();
        try {

            usersDao = new UsersDao();
        }catch (Exception e) {
            System.out.print(e.toString());
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        RequestDispatcher dispatcher;
        switch (path) {
            case "/testUsers" :
                dispatcher = getServletContext().getRequestDispatcher("WEB-INF/jsp/userLogged.jsp");
                dispatcher.forward(request, response);
            case "/landingPage":
                dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp");
                dispatcher.forward(request, response);
                break;

            case "/register":
                //todo: implement
                break;

            case "/login":
                try {

                    UserEntity user = new UserEntity();
                    user.setUsername(request.getParameter("un"));
                    user.setPassword(request.getParameter("pw"));

                    boolean loginSuccessful = usersDao.loginUser(user);


                    if (loginSuccessful) {
                        HttpSession session = request.getSession(true);

                        Iterator<TaskEntity> tasks = (Iterator<TaskEntity>) todoTasksDao.getAllTasksForUser(user.getUsername());

                        session.setAttribute("currentSessionUser", user);
                        session.setAttribute("notes", tasks);

                        dispatcher = getServletContext().getRequestDispatcher("/userLogged.jsp");
                        dispatcher.forward(request, response);

                    } else {
                        dispatcher = getServletContext().getRequestDispatcher("/invalidLogin.jsp");
                        dispatcher.forward(request, response);
                    }
                } catch (TodoDaoException e) {
                    response.getWriter().print(e.getStackTrace());
                }
                break;


            default:
                response.sendRedirect("/controller/landingPage");
        }
    }
}
