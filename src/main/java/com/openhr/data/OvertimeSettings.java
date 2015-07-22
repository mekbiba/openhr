package com.openhr.data;

public class OvertimeSettings extends PayrollSetting implements java.io.Serializable {

    private float workday;
    private float weekend;
    private float holiday;

    public float getWorkday() {
        return workday;
    }

    public void setWorkday(float workday) {
        this.workday = workday;
    }

    public float getWeekend() {
        return weekend;
    }

    public void setWeekend(float weekend) {
        this.weekend = weekend;
    }

    public float getHoliday() {
        return holiday;
    }

    public void setHoliday(float holiday) {
        this.holiday = holiday;
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
