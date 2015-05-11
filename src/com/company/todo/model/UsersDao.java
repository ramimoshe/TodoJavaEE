package com.company.todo.model;

import org.hibernate.*;

import java.util.Iterator;

/**
 * Created by Boris Borshevsky on 4/20/2015.
 */
public class UsersDao implements ITodoUsersDao {

    private SessionFactory getSession() {
        return SessionFactoryDao.getSession();
    }

    private Session session;

    @Override
    public UserEntity getUserById(String userName) throws TodoDaoException {
        Transaction transaction = null;
        try {
            session = getSession().openSession();
            transaction = session.beginTransaction();
            UserEntity userEntity = (UserEntity) session.get(UserEntity.class, userName);
            transaction.commit();
            return userEntity;

        } catch (HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new TodoDaoException("Exception getting user by id", he);
        } finally {
            if (session != null) {
                session.flush();
                session.close();
            }
        }
    }

    @Override
    public Iterator<UserEntity> getAllUsers() throws TodoDaoException {
        Transaction transaction = null;
        try {
            session = getSession().openSession();
            transaction = session.beginTransaction();
            Iterator<UserEntity> users = (Iterator<UserEntity>) (session.createQuery("from UserEntity ").list().iterator());
            transaction.commit();
            return users;

        } catch (HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new TodoDaoException("Exception getting all users", he);
        } finally {
            if (session != null) {
                session.flush();
                session.close();
            }
        }
    }

    @Override
    public String addUser(UserEntity userEntity) throws TodoDaoException {
        Transaction transaction = null;
        try {
            session = getSession().openSession();
            transaction = session.beginTransaction();
            session.save(userEntity);
            transaction.commit();
            return userEntity.getUsername();

        } catch (HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new TodoDaoException("Exception in adding user", he);
        } finally {
            if (session != null) {
                session.flush();
                session.close();
            }
        }
    }

    @Override
    public void updateUser(UserEntity userEntity) throws TodoDaoException {
        Transaction transaction = null;
        try {
            session = getSession().openSession();
            transaction = session.beginTransaction();
            session.update(userEntity);
            transaction.commit();

        } catch (HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new TodoDaoException("Exception in adding user", he);
        } finally {
            if (session != null) {
                session.flush();
                session.close();
            }
        }
    }


    public boolean loginUser(UserEntity userEntity) throws TodoDaoException {
        Transaction transaction = null;
        try {
            session = getSession().openSession();
            transaction = session.beginTransaction();

            Query query = session.createQuery("from UserEntity user where user.username = :username AND user.password = :password ");
            query.setParameter("username", userEntity.getUsername());
            query.setParameter("password", userEntity.getPassword());


            boolean result = query.list().size() == 1;
            transaction.commit();
            return result;

        } catch (HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new TodoDaoException("Exception in login", he);
        } finally {
            if (session != null) {
                session.flush();
                session.close();
            }
        }
    }

    public boolean isValidUser(UserEntity userEntity) throws TodoDaoException {
        Transaction transaction = null;
        try {
            session = getSession().openSession();
            transaction = session.beginTransaction();

            Query query = session.createQuery("from UserEntity user where user.username = :username");
            query.setParameter("username", userEntity.getUsername());
            Iterator<UserEntity> users = query.list().iterator();
            transaction.commit();
            return users.next() != null;

        } catch (HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new TodoDaoException("Exception in login", he);
        } finally {
            if (session != null) {
                session.flush();
                session.close();
            }
        }
    }


    @Override
    public void deleteUser(String username) throws TodoDaoException {
        try {
            UserEntity user = getUserById(username);
            user.setIsDeleted(true);
            updateUser(user);
        } catch (TodoDaoException tde) {
            throw new TodoDaoException("Exeption in deleting user", tde);
        }
    }

    public void deleteForeverUser(UserEntity user) throws TodoDaoException {
        Transaction transaction = null;
        try {
            session = getSession().openSession();
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();

        } catch (HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new TodoDaoException("Exception in deleting firever user user", he);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}