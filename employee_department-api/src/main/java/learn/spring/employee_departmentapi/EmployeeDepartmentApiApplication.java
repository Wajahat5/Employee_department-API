package learn.spring.employee_departmentapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import learn.spring.employee_departmentapi.department.DepartmentService;

@SpringBootApplication
public class EmployeeDepartmentApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeDepartmentApiApplication.class, args);
	}

}
