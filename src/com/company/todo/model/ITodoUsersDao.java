package com.company.todo.model;

import java.util.Iterator;

/**
 * Created by Boris Borshevsky on 4/17/2015.
 */
public interface ITodoUsersDao {
    public UserEntity getUserById(String username) throws TodoDaoException;
    public Iterator<UserEntity> getAllUsers() throws TodoDaoException;
    public String addUser(UserEntity taskEntity) throws TodoDaoException;
    public void updateUser(UserEntity taskEntity) throws TodoDaoException;
    public void deleteUser(String username) throws TodoDaoException;
}
