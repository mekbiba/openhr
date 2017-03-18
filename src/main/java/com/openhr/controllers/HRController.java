package com.openhr.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.openhr.Report;
import com.openhr.data.Employee;
import com.openhr.service.impl.EmployeeService;

@RestController
@RequestMapping(value = "/rest-api/hr")
public class HRController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "employee/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Employee> listEmployees() throws Exception {
        return employeeService.findAll();
    }

    @RequestMapping(value = "employee/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean saveEmployee(@RequestBody Employee employee) throws Exception {
        return employeeService.save(employee);
    }

    @RequestMapping(value = "employee/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean updateEmployee(@RequestBody Employee employee) throws Exception {
        return employeeService.update(employee);
    }

    @RequestMapping(value = "payroll", method = RequestMethod.GET)
    @ResponseBody
    public void listEmployees(HttpServletResponse response) throws Exception {
        new Report().run(response);
    }
}
