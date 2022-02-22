package learn.spring.employee_departmentapi.department;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableCaching
@EnableTransactionManagement 
public class DepartmentController {

	public final String HashKey="Dept_Emp";
	@Autowired
	private DepartmentService departmentService;
	@RequestMapping("/department/all")
	public List<Department> getAllDepartments() {
		return departmentService.getAllDepartments();
	}
	@RequestMapping("/department/{id}")
	@Cacheable(key="#id",value=HashKey)
	public Optional<Department> getDepartment(@PathVariable int id) {
		//System.out.println("GetDept by id method"+id);
		return departmentService.getDepartment(id);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/department")
	public void addDepartment(@RequestBody Department dept) {
		departmentService.addDepartment(dept);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/department/{id}")
	@CachePut(key="#id",value=HashKey)
	public Department updateDepartment(@RequestBody Department dept,@PathVariable int id) {
		dept.setId(id);
		departmentService.updateDepartment(dept,id);
		return dept;
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/department/{id}")
	@CacheEvict(key="#id",value=HashKey)
	public void deleteDepartment(@PathVariable int id) {
		departmentService.deleteDepartment(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/department/multiple")
	public void updateMultipleDepartments(@RequestBody List<Department> depts) {
		departmentService.updateMultipleDepartments(depts);
	}
	
}
