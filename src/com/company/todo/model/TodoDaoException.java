package com.company.todo.model;

/**
 * Created by Boris Borshevsky on 4/14/2015.
 */

public class TodoDaoException extends Exception {
    public TodoDaoException(String msg) {
        super(msg);
    }
    public TodoDaoException(String msg, Throwable cause) {
        super(msg,cause);
    }
}
