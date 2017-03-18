package com.openhr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.openhr.data.Employee;
import com.openhr.factories.EmployeeFactory;
import com.openhr.service.IEmployeeService;

@Component
public class EmployeeService implements IEmployeeService {
	
	@Autowired
	EmployeeFactory employeeFactory;
	
	@Override
	public List<Employee> findAll() throws Exception{
		return employeeFactory.findAll();
	}

	@Override
	public Boolean save(Employee employee) throws Exception{
		return employeeFactory.insert(employee);
	}

    @Override
    public Boolean update(Employee employee) throws Exception {
        return employeeFactory.update(employee);
    }

}
