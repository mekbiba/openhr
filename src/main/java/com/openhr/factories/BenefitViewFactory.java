package com.openhr.factories;

import static com.openhr.factories.common.OpenHRSessionFactory.getInstance;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class BenefitViewFactory {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static Session session;
    private static Query query;
    private static List<String> benefitTypes;

    public BenefitViewFactory() {
    }

    public static List<String> findAllBenefitTypes() {
        session = getInstance().getCurrentSession();
        session.beginTransaction();
        query = session.getNamedQuery("EmpBenefitView.findAvailablBenefitType");
        benefitTypes = query.list();
        session.getTransaction().commit();

        return benefitTypes;
    }
}
