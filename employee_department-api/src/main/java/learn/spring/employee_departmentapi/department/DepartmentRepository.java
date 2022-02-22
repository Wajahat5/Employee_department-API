package learn.spring.employee_departmentapi.department;

import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department,Integer > {
	
}
