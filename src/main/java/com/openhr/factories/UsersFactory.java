/*
 * To change this template, choose Tools | Template
 * and open the template in the editor.
 */
package com.openhr.factories;

import static com.openhr.factories.common.OpenHRSessionFactory.getInstance;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.openhr.data.Users;

/**
 *
 * @author Mekbib
 */
public class UsersFactory {

    private static Session session;
    private static Query query;
    private static List<Users> users;
    private static Users user;

    public UsersFactory() {
    }

    public static List<Users> findAll() {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("Users.findAll");
        users = query.list();
        session.getTransaction().commit();

        return users;
    }

    public static List<Users> findById(Integer usersId) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("Users.findById");
        query.setInteger(0, usersId);
        users = query.list();
        session.getTransaction().commit();

        return users;
    }

    public static Users findByUserName(String usersName) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("Users.findByUsername");
        query.setString(0, usersName);
        users = query.list();
        session.getTransaction().commit();
        if (users.size() > 0) {
            user = users.get(0);
        }
        return user;
    }

    public static List<Users> findByPassword(String password) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("Users.findByPassword");
        query.setString(0, password);
        users = query.list();
        session.getTransaction().commit();

        return users;
    }

    public static boolean delete(Users usrs) {
        boolean done = false;
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        try {
            Users usr = (Users) session.get(Users.class, usrs.getId());
            session.delete(usr);
            session.flush();
            session.getTransaction().commit();
            done = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

        }
        return done;
    }

    public static boolean insert(Users usrs) {
        boolean done = false;

        session = getInstance().getCurrentSession();
        session.beginTransaction();
        try {
            session.save(usrs);
            session.getTransaction().commit();
            done = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

        }
        return done;

    }

    public static boolean update(Users usrs) {
        boolean done = false;
        session = getInstance().getCurrentSession();
        session.beginTransaction();

        try {
            Users usr = (Users) session.get(Users.class, usrs.getId());;
            usr.setUsername(usrs.getUsername());
            usr.setPassword(usrs.getPassword());
            usr.setEmployeeId(usrs.getEmployeeId());
            usr.setRoleId(usrs.getRoleId());
            session.getTransaction().commit();
            done = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

        }
        return done;
    }
}
