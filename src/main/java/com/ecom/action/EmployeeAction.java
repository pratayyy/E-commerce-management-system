package com.ecom.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ecom.dto.EmployeeDto;
import com.ecom.manager.EmployeeManager;
import com.ecom.pojo.Employee;

/**
 * @author pratay.roy
 * @version 0.0.1
 */
public class EmployeeAction {
	private Integer employeeId;
	private Employee employee;
	private List<Employee> employees;
	private Map<String, Object> root;
	private HttpServletResponse response;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Map<String, Object> getRoot() {
		return root;
	}

	public void setRoot(Map<String, Object> root) {
		this.root = root;
	}

	private EmployeeManager employeeManager;

	public EmployeeManager getEmployeeManager() {
		return employeeManager;
	}

	public void setEmployeeManager(EmployeeManager employeeManager) {
		this.employeeManager = employeeManager;
	}

	/**
	 * Constructor to get beans and response body initializations
	 */
	public EmployeeAction() {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml")) {
			employeeManager = (EmployeeManager) context.getBean("employeeManager");
		} catch (Exception e) {
			e.printStackTrace();
		}
		root = new HashMap<String, Object>();
		response = ServletActionContext.getResponse();
	}

	/**
	 * Method to set response when receiving a list of employees as output
	 * 
	 * @param employees
	 */
	public void handleResponse(List<EmployeeDto> employees) {
		if (employees == null) {
			root.put("message", employeeManager.getErrorMessage());
			response.setStatus(employeeManager.getStatusCode());
		} else {
			root.put("employees", employees);
		}
	}

	/**
	 * Method to set response when receiving a single employee as output
	 * 
	 * @param employee
	 */
	public void handleResponse(EmployeeDto employee) {
		if (employee == null) {
			root.put("message", employeeManager.getErrorMessage());
			response.setStatus(employeeManager.getStatusCode());
		} else {
			root.put("employee", employee);
		}
	}

	/**
	 * Method to set response when recieving result as output
	 * 
	 * @param result
	 */
	public void handleResponse(Integer result) {
		root.put("result", result);
		if (result == 0) {
			root.put("message", employeeManager.getErrorMessage());
			response.setStatus(employeeManager.getStatusCode());
		}
	}

	/**
	 * Method to get all employees
	 * 
	 * @return success
	 */
	public String readAll() {
		List<EmployeeDto> employees = employeeManager.getAllEmployees();
		handleResponse(employees);
		return "success";
	}

	/**
	 * Method to get employee by employee ID
	 * 
	 * @return success
	 */
	public String readById() {
		EmployeeDto employee = employeeManager.getEmployeeById(employeeId);
		handleResponse(employee);
		return "success";
	}

	/**
	 * Method to add a single employee
	 * 
	 * @return success
	 */
	public String addSingle() {
		Integer result = employeeManager.addNewEmployee(employee);
		handleResponse(result);
		return "success";
	}

	/**
	 * Method to add multiple employee
	 * 
	 * @return success
	 */
	public String addMultiple() {
		Integer result = employeeManager.addNewEmployees(employees);
		handleResponse(result);
		return "success";
	}

	/**
	 * Method to update existing employee
	 * 
	 * @return success
	 */
	public String update() {
		Integer result = employeeManager.updateEmployee(employeeId, employee);
		handleResponse(result);
		return "success";
	}

	/**
	 * Method to delete existing employee
	 * 
	 * @return success
	 */
	public String delete() {
		Integer result = employeeManager.deleteEmployee(employeeId);
		handleResponse(result);
		return "success";
	}

}
