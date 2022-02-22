package learn.spring.employee_departmentapi.department;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public List<Department> getAllDepartments() {
		List<Department> departments=new ArrayList<>();
		departmentRepository.findAll()
		.forEach(departments::add);
		return departments;
	}
	
	public Optional<Department> getDepartment(int id) {
		Optional<Department> dept=departmentRepository.findById(id);
		System.out.println("GetDept by id method, db hit");
		//System.out.println(dept);
		return dept;
	}
	
	public void addDepartment(Department dept) {
		departmentRepository.save(dept);
	}
	
	public void updateDepartment(Department dept,int id) {
		departmentRepository.save(dept);
	}
	
	public void deleteDepartment(int id) {
		departmentRepository.deleteById(id);
	}
	
	@Transactional
	public void updateMultipleDepartments(List<Department> depts) {
		//System.out.println(depts);
		departmentRepository.saveAll(depts);
	}
}
