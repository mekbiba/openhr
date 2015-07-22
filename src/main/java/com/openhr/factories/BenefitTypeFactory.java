/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openhr.factories;

import static com.openhr.factories.common.OpenHRSessionFactory.getInstance;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.openhr.data.BenefitType;

/**
 *
 * @author Mekbib
 */
public class BenefitTypeFactory {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static Session session;
    private static Query query;
    private static List<BenefitType> benefitTypes;

    public BenefitTypeFactory() {
    }

    public static List<BenefitType> findAll() {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("BenefitType.findAll");
        benefitTypes = query.list();
        session.getTransaction().commit();

        return benefitTypes;
    }

    public static List<BenefitType> findById(Integer benefitTypeId) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("BenefitType.findById");
        query.setInteger(0, benefitTypeId);
        benefitTypes = query.list();
        session.getTransaction().commit();

        return benefitTypes;
    }

    public static List<BenefitType> findByName(String benefitTypeName) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("BenefitType.findByName");
        query.setString(0, benefitTypeName);
        benefitTypes = query.list();
        session.getTransaction().commit();

        return benefitTypes;
    }

    public static List<BenefitType> findByCap(Double cap) {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("BenefitType.findByCap");
        query.setDouble(0, cap);
        benefitTypes = query.list();
        session.getTransaction().commit();

        return benefitTypes;
    }

    public static boolean delete(BenefitType bt) {
        boolean done = false;
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        try {
            BenefitType benefitType = (BenefitType) session.get(BenefitType.class, bt.getId());
            session.delete(benefitType);
            session.flush();
            session.getTransaction().commit();
            done = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return done;
    }

    public static boolean insert(BenefitType bt) {
        boolean done = false;

        session = getInstance().getCurrentSession();
        session.beginTransaction();
        try {
            session.save(bt);
            session.getTransaction().commit();
            done = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return done;
    }

    public static boolean update(BenefitType bt) {
        boolean done = false;
        session = getInstance().getCurrentSession();
        session.beginTransaction();

        try {
            BenefitType benefitType = (BenefitType) session.get(BenefitType.class, bt.getId());
            benefitType.setName(bt.getName());
            benefitType.setCap(bt.getCap());
            session.getTransaction().commit();
            done = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return done;
    }
}
