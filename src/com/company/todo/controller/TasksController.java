package com.company.todo.controller;

import com.company.todo.Helpers.JspUrlResolver;
import com.company.todo.model.*;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
@WebServlet(name = "TasksController", urlPatterns = { "/controller/tasks/*" })
public class TasksController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private TodoTasksDao todoTasksDao;

    public TasksController(){
        super();
        todoTasksDao = new TodoTasksDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TaskEntity task = new TaskEntity();
        String taskId = request.getParameter("taskId");
        if (taskId != null &&  taskId != ""){
            task.setTaskId(Integer.parseInt(request.getParameter("taskId")));
        }

        String createdDate = request.getParameter("createdDate");
        if (createdDate != null && createdDate != "") {
            task.setDateCreated(java.sql.Date.valueOf(request.getParameter("createdDate")));
        }

        task.setTitle(request.getParameter("title"));
        task.setContent(request.getParameter("content"));
        task.setDuedate(java.sql.Date.valueOf(request.getParameter("dueDate")));
        task.setUserId(request.getParameter("userId"));
        String isDeleted = request.getParameter("isDeleted");
        task.setIsDeleted(Boolean.parseBoolean(isDeleted));

        try {
            todoTasksDao.upsertTask(task);
        } catch (TodoDaoException e) {
            e.printStackTrace();
            //TODO - go to error page
        }

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getNamedDispatcher("HomeController");
            dispatcher.forward(request, response);
        } else {
            UserEntity userSession = (UserEntity)session.getAttribute("currentSessionUser");
            if (userSession == null){
                RequestDispatcher dispatcher = getServletContext().getNamedDispatcher("HomeController");
                dispatcher.forward(request, response);
            }
            Iterator<TaskEntity> allTasksForUser = null;
            try {
                allTasksForUser = todoTasksDao.getAllTasksForUser(userSession.getUsername());
            } catch (TodoDaoException e) {
                //TODO: redirect to error page
                e.printStackTrace();
            }
            request.setAttribute("tasks", allTasksForUser);
            request.setAttribute("userId", userSession.getUsername());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(JspUrlResolver.getJspUrl("/allTasks.jsp"));
            dispatcher.forward(request, response);
        }
    }
}
