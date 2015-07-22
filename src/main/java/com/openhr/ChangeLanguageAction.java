package com.openhr;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ChangeLanguageAction extends DispatchAction {

    public ActionForward amharic(ActionMapping map, ActionForm form,
            HttpServletRequest request, HttpServletResponse reponse)
            throws Exception {
        request.getSession().setAttribute(Globals.LOCALE_KEY,
                new Locale("am"));

        return map.findForward("");
    }

    public ActionForward english(ActionMapping map, ActionForm form,
            HttpServletRequest request, HttpServletResponse reponse)
            throws Exception {
        request.getSession().setAttribute(Globals.LOCALE_KEY,
                Locale.ENGLISH);

        return map.findForward("");
    }

    public ActionForward oromifa(ActionMapping map, ActionForm form,
            HttpServletRequest request, HttpServletResponse reponse)
            throws Exception {
        request.getSession().setAttribute(Globals.LOCALE_KEY,
                new Locale("om"));

        return map.findForward("continue");
    }
}
