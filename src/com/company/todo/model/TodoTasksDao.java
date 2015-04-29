package com.company.todo.model;

import org.hibernate.*;

import java.util.Date;
import java.util.Iterator;

/**
 * Created by Boris Borshevsky on 4/20/2015.
 */
public class TodoTasksDao implements ITodoTaskDao {

    private Session session;

    private SessionFactory getSession() {
        return SessionFactoryDao.getSession();
    }

    @Override
    public TaskEntity getTaskById(int id) throws TodoDaoException {

        Transaction transaction = null;
        try {
            session = getSession().openSession();
            transaction = session.beginTransaction();
            TaskEntity taskEntity = (TaskEntity) session.get(TaskEntity.class, id);
            transaction.commit();
            return taskEntity;

        } catch (HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new TodoDaoException("Exception in getting task", he);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Iterator<TaskEntity> getAllTasks() throws TodoDaoException {
        Transaction transaction = null;
        try {
            session = getSession().openSession();
            transaction = session.beginTransaction();
            Iterator<TaskEntity> tasks = (Iterator) (session.createQuery("from TaskEntity where isDeleted = false").list().iterator());
            transaction.commit();
            return tasks;

        } catch (HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new TodoDaoException("Exception in getting all tasks", he);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }


    @Override
    public Iterator<TaskEntity> getAllTasksForUser(String username) throws TodoDaoException {
        Transaction transaction = null;
        try {
            session = getSession().openSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery("from TaskEntity as task where task.userId = :username and task.isDeleted = false");
            query.setParameter("username", username);
            Iterator<TaskEntity> tasks = query.list().iterator();
            transaction.commit();
            return tasks;

        } catch (HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new TodoDaoException("Exception in getting user specific Tasks tasks", he);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public int upsertTask(TaskEntity taskEntity) throws TodoDaoException {
        Transaction transaction = null;
        try {
            if (taskEntity.getDateCreated() == null){
                taskEntity.setDateCreated(new java.sql.Date(new Date().getTime()));
            }

            session = getSession().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(taskEntity);
            transaction.commit();
            return taskEntity.getTaskId();

        } catch (HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new TodoDaoException("Exception in getting all tasks", he);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public int addTask(TaskEntity taskEntity) throws TodoDaoException {
        Transaction transaction = null;
        try {
            session = getSession().openSession();
            transaction = session.beginTransaction();
            session.save(taskEntity);
            transaction.commit();
            return taskEntity.getTaskId();

        } catch (HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new TodoDaoException("Exception in getting all tasks", he);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void updateTask(TaskEntity taskEntity) throws TodoDaoException {

        Transaction transaction = null;
        try {
            session = getSession().openSession();
            transaction = session.beginTransaction();
            session.update(taskEntity);
            transaction.commit();

        } catch (HibernateException he) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new TodoDaoException("Exception in getting all tasks", he);
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public void deleteTask(int id) throws TodoDaoException {
        try {
            TaskEntity task = getTaskById(id);
            task.setIsDeleted(true);
            updateTask(task);
        } catch (TodoDaoException tde) {
            throw new TodoDaoException("Exeption in deleting user", tde);
        }
    }

    public void deleteForeverTask(TaskEntity task) throws TodoDaoException {
        Transaction transaction = null;
        try {
            session = getSession().openSession();
            transaction = session.beginTransaction();
            session.delete(task);
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
