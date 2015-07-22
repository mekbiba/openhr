package com.openhr.settings.form;

import org.apache.struts.action.ActionForm;

public class SettingsForm extends ActionForm {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
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
