package vinopole.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import vinopole.HibernateUtil;

import javax.persistence.PersistenceException;
import java.util.List;

/**
 * @author Nibeaudeau Timothy <timothy.nibeaudeau@gmail.com>
 * @version 0.1
 *          on 24/10/2016 for VinopoleDesktop with IntelliJ IDEA.
 */
public abstract class DAO<T> {
    /**
     * Save t to DB
     *
     * @param t to save
     */
    public void save(T t) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
        } catch (PersistenceException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    /**
     * Remove t from Db
     *
     * @param t to remove
     */
    public void remove(T t) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(t);
            session.flush();
            transaction.commit();
        } catch (PersistenceException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }

    }

    /**
     * Update t from db
     *
     * @param t to update
     */
    public void update(T t) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(t);
            session.getTransaction().commit();
        } catch (PersistenceException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    /**
     * Get T from DB with id
     *
     * @param id
     * @return T
     */
    abstract public T get(long id);

    /**
     * Get all T from Db
     * @return list of T
     */
    abstract public List<T> getAll();
}
