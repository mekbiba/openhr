package com.openhr;

import static java.lang.Thread.sleep;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author HuyLV
 *
 */
public class LongProcessAction extends Action {

    private static final String DEFAULT_PROCESS_FINISHED_ATTRIBUTE = "processFinished";

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession(true);
        Boolean finished = (Boolean) session
                .getAttribute(DEFAULT_PROCESS_FINISHED_ATTRIBUTE);

        if (finished != null) {
            if (finished) {
                session.setAttribute(DEFAULT_PROCESS_FINISHED_ATTRIBUTE, null);
                return mapping.findForward("success");
            } else {
                return mapping.findForward("wait");
            }
        }

        session.setAttribute(DEFAULT_PROCESS_FINISHED_ATTRIBUTE, false);

        new Thread(new RunFinish(session)).start();
        return mapping.findForward("wait");
    }

    class RunFinish implements Runnable {

        private HttpSession session;

        RunFinish(HttpSession session) {
            this.session = session;
        }

        public void run() {
            try {
                sleep(20000);
                session.setAttribute(DEFAULT_PROCESS_FINISHED_ATTRIBUTE, true);
            } catch (Exception e) {
            }
        }
    }
}
