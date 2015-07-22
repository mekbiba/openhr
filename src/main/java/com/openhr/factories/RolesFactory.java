/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openhr.factories;

import static com.openhr.factories.common.OpenHRSessionFactory.getInstance;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.openhr.data.Roles;

/**
 *
 * @author Mekbib
 */
public class RolesFactory {

    private static Session session;
    private static Query query;
    private static List<Roles> roles;

    public RolesFactory() {
    }

    public static List<Roles> findAll() {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("Roles.findAll");
        roles = query.list();
        session.getTransaction().commit();
        return roles;
    }

    public static List<Roles> findById(Integer roleId) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("Roles.findById");
        query.setInteger(0, roleId);
        roles = query.list();
        session.getTransaction().commit();
        return roles;
    }

    public static List<Roles> findByName(String roleName) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("Roles.findByName");
        query.setString(0, roleName);
        roles = query.list();
        session.getTransaction().commit();
        return roles;
    }

    public static boolean delete(Roles roles) {
        boolean done = false;
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        try {
            Roles role = (Roles) session.get(Roles.class, roles.getId());
            session.delete(role);
            session.getTransaction().commit();
            done = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

        }
        return done;
    }

    public static boolean insert(Roles roles) {
        boolean done = false;

        session = getInstance().getCurrentSession();
        session.beginTransaction();
        try {
            session.save(roles);
            session.getTransaction().commit();
            done = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

        }
        return done;

    }

    public static boolean update(Roles roles) {
        boolean done = false;
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        try {
            Roles role = (Roles) session.get(Roles.class, roles.getId());
            role.setName(roles.getName());
            session.getTransaction().commit();
            done = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return done;
    }
}
