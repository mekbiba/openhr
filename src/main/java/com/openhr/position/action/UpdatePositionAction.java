/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openhr.position.action;

import static com.openhr.factories.PositionFactory.update;
import static net.sf.json.JSONArray.fromObject;
import static net.sf.json.JSONArray.toCollection;

import java.io.BufferedReader;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.openhr.data.Position;
import com.openhr.position.form.PositionForm;

/**
 *
 * @author Mekbib
 */
public class UpdatePositionAction extends Action {

    @Override
    public ActionForward execute(ActionMapping map,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse reponse) throws Exception {
        PositionForm positionForm = (PositionForm) form;
        //JSONSerializer.toJava(json);
        System.out.println("FROM UPDATE POSITION ACTION CLASS : ");
        //System.out.println("SIZE OF THE COLLECTION : " + aCollection.size());
        BufferedReader bf = request.getReader();
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = bf.readLine()) != null) {
            sb.append(line);
        }

        List<JSONObject> json = fromObject(sb.toString());
        JSONArray n = new JSONArray();
        for (JSONObject obj : json) {
            obj.remove("employeeCollection");
            n.add(obj);
        }

        Collection<Position> aCollection = toCollection(n, Position.class);

        System.out.println(" Size of List for Update " + aCollection.size());

        Position p = new Position();
        for (Position pFromJSON : aCollection) {
            p.setId(pFromJSON.getId());
            p.setName(pFromJSON.getName());
            p.setSalary(pFromJSON.getSalary());
            p.setRaisePerYear(pFromJSON.getRaisePerYear());
            update(p);
        }

        return map.findForward("");
    }
}
