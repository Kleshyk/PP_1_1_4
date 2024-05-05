package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }



    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction trans = session.beginTransaction();
            try {
                String CREATE = "CREATE TABLE IF NOT EXISTS User (" + "id INT NOT NULL AUTO_INCREMENT , " +
                        "name VARCHAR(255), " + "lastname VARCHAR(255), " + "age INT, " + "PRIMARY KEY (id)" + ")";
                Query query = session.createSQLQuery(CREATE).addEntity(User.class);
                query.executeUpdate();
                trans.commit();
            } catch (Exception e) {
                if (trans != null) {
                    trans.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction trans = session.beginTransaction();
            try {
                String DROP = "DROP TABLE IF EXISTS User";
                Query query = session.createSQLQuery(DROP);
                query.executeUpdate();
                trans.commit();
            } catch (Exception e) {
                if (trans != null) {
                    trans.rollback();
                }
                e.printStackTrace();
            }
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
