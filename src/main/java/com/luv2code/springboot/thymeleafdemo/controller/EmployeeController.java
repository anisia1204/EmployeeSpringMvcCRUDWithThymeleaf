package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	@Autowired // optional
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		//get the employees from the db
		List<Employee> employees = employeeService.findAll();

		// add the pulled data from the db to the spring model to be used by the list-employees thymeleaf template
		theModel.addAttribute("employees", employees);

		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){
		//create the model attribute to bind the form data
		Employee theEmployee = new Employee();

		//we then add that as an attribute to the model, give the attribute name of employee and then give a reference to theEmployee
		//the nice thing here is that our thymeleaf template will access this data for binding the form data
		theModel.addAttribute("employee", theEmployee);

		return "employees/employee-form"; //returning the actual template that we want to use for this form
	}

	@PostMapping("/save")// we pass in a model attribute(from the form) to the method which is the actual form data being passed in using the data binding
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
			//save the employee
			employeeService.save(theEmployee);
			//use a redirect to prevent duplicate submissions
			return "redirect:/employees/list";
		}
	}










