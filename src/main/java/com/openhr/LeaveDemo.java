package com.openhr;

import static com.openhr.factories.BenefitFactory.search;

import java.util.List;

import com.openhr.data.Benefit;

public class LeaveDemo {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Benefit b = new Benefit();
        b.setAmount(500);
        List<Benefit> benefits = search(b);
        for (Benefit b1 : benefits) {
            System.out.println(b1.getTypeId().getName());
        }
    }

}
