package com.luv2code.springboot.thymeleafdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// that's it ... no need to write any code LOL!

    //add a method to sort the employees by last name
    public List<Employee> findAllByOrderByLastNameAsc();
    //above, Spring Data JPA will parse the method name, will look for a specific format and pattern
    // and it will create the appropriate query behind the scenes
    // behind the scenes, Spring Data JPA will say "from Employee order by lastName asc"
}
