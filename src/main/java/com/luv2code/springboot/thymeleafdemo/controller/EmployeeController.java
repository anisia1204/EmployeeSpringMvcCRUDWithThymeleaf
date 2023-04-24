package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int id, Model model) {
		//get the employee from the service
		Employee theEmployee = employeeService.findById(id); //id is the employeeId that was passed in from that embedded link
		//set the employee in the model as a model attribute to prepopulate the form
		model.addAttribute("employee", theEmployee);
		//send over to our form
		return  "employees/employee-form";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int id) {
		//delete the employee
		employeeService.deleteById(id);
		//redirect to the /employees/list
		return "redirect:/employees/list";
	}
}










