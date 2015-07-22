package com.openhr.data;

public class StaffLoanEligibility extends PayrollSetting implements java.io.Serializable {

    private int expInYears;
    private float rate;

    public int getExpInYears() {
        return expInYears;
    }

    public void setExpInYears(int expInYears) {
        this.expInYears = expInYears;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public static String selectAllQuery() {

        return "";

    }

    public static String insertQuery() {

        return "";

    }

    public static String updateQuery() {

        return "";

    }

}
