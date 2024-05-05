package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
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
                session.createNativeQuery(CREATE).addEntity(User.class).executeUpdate();
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
                session.createNativeQuery(DROP).executeUpdate();
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
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction trans = session.beginTransaction();
            try {
                User user = new User(name, lastName, age);
                session.save(user);
                trans.commit();
                System.out.println("User с именем " + name + " добавлен в базу данных");
            } catch (Exception e) {
                if (trans != null) {
                    trans.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction trans = session.beginTransaction();
            try {
                User user = session.get(User.class, id);
                if (user != null) {
                    session.delete(user);
                }
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
    public List<User> getAllUsers() {
        List users = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction trans = session.beginTransaction();
            try {
                users = session.createQuery("from User").list();
                trans.commit();
            } catch (Exception e) {
                if (trans != null) {
                    trans.rollback();
                }
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction trans = session.beginTransaction();
            try {
                Query query = session.createQuery("delete from User");
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
}
