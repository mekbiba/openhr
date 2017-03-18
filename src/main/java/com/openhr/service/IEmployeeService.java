package com.openhr.service;

import java.util.List;

import com.openhr.data.Employee;

public interface IEmployeeService {
	public List<Employee> findAll()  throws Exception;
	public Boolean save(Employee employee)  throws Exception;
        public Boolean update(Employee employee)  throws Exception;
}
