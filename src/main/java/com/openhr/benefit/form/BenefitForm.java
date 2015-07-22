package com.openhr.benefit.form;

import org.apache.struts.action.ActionForm;

import com.openhr.data.BenefitType;

public class BenefitForm extends ActionForm {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Double amount;
    private BenefitType typeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public BenefitType getTypeId() {
        return typeId;
    }

    public void setTypeId(BenefitType typeId) {
        this.typeId = typeId;
    }

}
