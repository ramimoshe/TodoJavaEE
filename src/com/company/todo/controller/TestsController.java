package com.company.todo.controller;

import com.company.todo.Helpers.JspUrlResolver;
import com.company.todo.model.TaskEntity;
import com.company.todo.model.TodoTasksDao;
import com.company.todo.model.UserEntity;
import com.company.todo.model.UsersDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by rami.moshe on 4/29/2015.
 */
@WebServlet(name = "TestsController", urlPatterns = { "/controller/tests" })
public class TestsController extends HttpServlet {
    private TodoTasksDao todoTasksDao;
    private UsersDao usersDao;

    public TestsController(){
        todoTasksDao = new TodoTasksDao();
        usersDao = new UsersDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String testResultView = JspUrlResolver.getJspUrl("testResult.jsp");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(testResultView);


        request.setAttribute("usersResult", new Boolean(testTask()));
        request.setAttribute("tasksResult", new Boolean(testTask()));

        dispatcher.forward(request, response);
    }

    private boolean testUser() {
        try {
            UserEntity user = new UserEntity();
            user.setUsername("bla");
            user.setPassword("tit");
            user.setDateCreated(new java.sql.Date(new Date().getTime()));
            usersDao.addUser(user);
            Iterator<UserEntity> users = usersDao.getAllUsers();
            UserEntity newUser = usersDao.getUserById(user.getUsername());
            usersDao.deleteUser(newUser.getUsername());
            user.setPassword("zzz");
            usersDao.updateUser(user);
            ((UsersDao) usersDao).deleteForeverUser(user);

            return true;
        }catch (Exception e){
            return false;
        }
    }

    private boolean testTask() {
        try {
            TaskEntity task = new TaskEntity();
            task.setDuedate(new java.sql.Date(new Date(2016, 12, 20).getTime()));
            task.setContent("bla");
            task.setTitle("tit");
            task.setDateCreated(new java.sql.Date(new Date().getTime()));
            task.setUserId("testUser");
            todoTasksDao.addTask(task);
            Iterator<TaskEntity> tasks = todoTasksDao.getAllTasks();
            TaskEntity newTask = todoTasksDao.getTaskById(task.getTaskId());
            todoTasksDao.deleteTask(newTask.getTaskId());
            task.setTitle("jjjj");
            todoTasksDao.updateTask(task);
            ((TodoTasksDao) todoTasksDao).deleteForeverTask(task);

            return true;
        }catch (Exception e){
            return false;
        }
    }
}
