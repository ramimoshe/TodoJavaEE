package com.company.todo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Boris Borshevsky on 4/16/2015.
 */
//@Entity
//@Table(name = "users", schema = "", catalog = "tododb")
public class UserEntity implements Serializable{

    private static final long serialVersionUID = 752L;

    private String username;
    private String password;
    private Date dateCreated;
    private boolean isDeleted;


    public UserEntity(String username, String password) {
        setUsername(username);
        setPassword(password);
        setIsDeleted(false);
    }

    public UserEntity() {
    }


    //@Id
    //@Column(name = "username", nullable = false, insertable = true, updatable = true, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //@Basic
    //@Column(name = "password", nullable = false, insertable = true, updatable = true, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //@Basic
    //@Column(name = "dateCreated", nullable = false, insertable = true, updatable = true)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    //@Basic
    //@Column(name = "isDeleted", nullable = false, insertable = true, updatable = true)
    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (isDeleted != that.isDeleted) return false;
        if (dateCreated != null ? !dateCreated.equals(that.dateCreated) : that.dateCreated != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        result = 31 * result + (isDeleted ? 1 : 0);
        return result;
    }
}
