package com.openhr.employee.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class EmployeePhotoForm extends ActionForm {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private FormFile photoFile;

    public FormFile getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(FormFile photoFile) {
        this.photoFile = photoFile;
    }
}
