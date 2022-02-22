package learn.spring.employee_departmentapi.employee;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import antlr.collections.List;

public interface EmployeeRepository extends CrudRepository<Employee,Integer> {
	
	//@Query("select e from Employee where departmentid = ?1")
	//public List<Employee> findByDepartmentid(String department_id);
	@Query("select e from Employee e where e.department.id = ?1")
	public java.util.List<Employee> findByDepartmentId(int department_id);
}
