package com.company.todo.model;

import java.util.Iterator;

/**
 * Created by Boris Borshevsky on 4/14/2015.
 */
public interface ITodoTaskDao {
    public TaskEntity getTaskById(int id) throws TodoDaoException;
    public Iterator<TaskEntity> getAllTasks() throws TodoDaoException;
    public int addTask(TaskEntity taskEntity) throws TodoDaoException;
    public void updateTask(TaskEntity taskEntity) throws TodoDaoException;
    public void deleteTask(int id) throws TodoDaoException;
    public Iterator<TaskEntity> getAllTasksForUser(String username) throws TodoDaoException;
    public int upsertTask(TaskEntity taskEntity) throws TodoDaoException;
}
