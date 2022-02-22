package learn.spring.employee_departmentapi.employee;

import java.util.Arrays;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;




@RestController
@EnableCaching
public class EmployeeController {
	
	public final String HashKey="Dept_Emp";
	@Autowired
	private EmployeeService employeeService;
	@RequestMapping("/employee/all")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}
	
	@RequestMapping("/employee/{id}")
	@Cacheable(key="#id",value=HashKey)
	public Optional<Employee> getEmployee(@PathVariable int id) {
		//System.out.println("GetDept by id method"+id);
		return employeeService.getEmployee(id);
	}
	
	@RequestMapping("/employee/department/{department_id}")
	public List<Employee> getEmployeeByDeptId(@PathVariable int department_id) {
		//System.out.println("GetDept by id method"+id);
		return employeeService.getEmployeeByDeptId(department_id);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/employee/{department_id}")
	public void addDepartment(@RequestBody Employee emp,@PathVariable int department_id) {
		emp.setDepartment(department_id);
		System.out.println(emp);
		employeeService.addEmployee(emp);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/employee/{id}")
	@CachePut(key="#id",value=HashKey)
	public Employee updateDepartment(@RequestBody Employee emp,@PathVariable int id) {
		emp.setId(id);
		employeeService.updateEmployee(emp,id);
		return emp;
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/employee/{id}")
	@CacheEvict(key="#id",value=HashKey)
	public void deleteDepartment(@PathVariable int id) {
		employeeService.deleteEmployee(id);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/employee/from-file/{department_id}")
	public ResponseEntity<?> addEmployeesFromFile(@RequestBody MultipartFile file,@PathVariable int department_id) {
		if(file==null) return new ResponseEntity<>("Failed",HttpStatus.METHOD_FAILURE);
		return employeeService.addEmployeesFromFile(file,department_id);
	}
	
}
