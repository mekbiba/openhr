/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openhr.factories;

import static com.openhr.factories.common.OpenHRSessionFactory.getInstance;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.openhr.data.Position;

/**
 *
 * @author Mekbib
 */
public class PositionFactory implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static Session session;
    private static Query query;
    private static List<Position> positions;

    public PositionFactory() {
    }

    public static List<Position> findAll() {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("Position.findAll");
        positions = query.list();
        session.getTransaction().commit();

        return positions;
    }

    public static List<Position> findById(Integer positionId) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("Position.findById");
        query.setInteger(0, positionId);
        positions = query.list();
        session.getTransaction().commit();

        return positions;
    }

    public static List<Position> findByName(String positionName) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("Position.findByName");
        query.setString(0, positionName);
        positions = query.list();
        session.getTransaction().commit();

        return positions;
    }

    public static List<Position> findBySalary(Double salary) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("Position.findBySalary");
        query.setDouble(0, salary);
        positions = query.list();
        session.getTransaction().commit();

        return positions;
    }

    public static List<Position> findByRaise(Double raise) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("Position.findByRaisePerYear");
        query.setDouble(0, raise);
        positions = query.list();
        session.getTransaction().commit();

        return positions;
    }

    public static boolean delete(Position p) {
        boolean done = false;
        session = getInstance().getCurrentSession();
        session.beginTransaction();

        Position pos = (Position) session.get(Position.class, p.getId());
        session.delete(pos);
        session.flush();
        session.getTransaction().commit();
        done = true;

        return done;
    }

    public static boolean insert(Position p) {
        boolean done = false;

        session = getInstance().getCurrentSession();
        session.beginTransaction();

        session.save(p);
        session.getTransaction().commit();
        done = true;

        return done;
    }

    public static boolean update(Position p) {
        boolean done = false;
        session = getInstance().getCurrentSession();
        session.beginTransaction();

        Position pos = (Position) session.get(Position.class, p.getId());
        pos.setName(p.getName());
        pos.setSalary(p.getSalary());
        pos.setRaisePerYear(p.getRaisePerYear());
        session.getTransaction().commit();
        done = true;

        return done;
    }
}
