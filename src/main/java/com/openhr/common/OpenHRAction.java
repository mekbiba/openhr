/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openhr.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.openhr.user.form.LoginForm;

/**
 *
 * @author Mekbib
 */
public class OpenHRAction extends Action {

    @Override
    public ActionForward execute(ActionMapping map,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse reponse) throws Exception {
        LoginForm loginForm = (LoginForm) form;
        request.setAttribute("LoggedUsername", loginForm.getUsername());
        return map.findForward("continue");
    }
}
