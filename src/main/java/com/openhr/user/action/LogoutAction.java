/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openhr.user.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Mekbib
 */
public class LogoutAction extends Action {

    @Override
    public ActionForward execute(ActionMapping map,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse reponse) throws Exception {
        //LogoutForm logoutForm = (LogoutForm) form;
        request.getSession().invalidate();
        return map.findForward("continue");
    }
}
