package com.openhr.settings;

public class Settings {

    private String idPattern;
    private String promotionStrategy;
    private String payrollStrategy;

    public String getIdPattern() {
        return idPattern;
    }

    public void setIdPattern(String idPattern) {
        this.idPattern = idPattern;
    }

    public String getPromotionStrategy() {
        return promotionStrategy;
    }

    public void setPromotionStrategy(String promotionStrategy) {
        this.promotionStrategy = promotionStrategy;
    }

    public String getPayrollStrategy() {
        return payrollStrategy;
    }

    public void setPayrollStrategy(String payrollStrategy) {
        this.payrollStrategy = payrollStrategy;
    }

}
