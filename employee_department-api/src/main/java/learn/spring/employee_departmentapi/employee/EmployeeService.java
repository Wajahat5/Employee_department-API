package learn.spring.employee_departmentapi.employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> getAllEmployees() {
		List<Employee> employees=new ArrayList<>();
		employeeRepository.findAll()
		.forEach(employees::add);
		return employees;
	}
	
	public Optional<Employee> getEmployee(int id) {
		Optional<Employee> emp=employeeRepository.findById(id);
		/*
		if(emp.get()==null)
			throw new Exception(Integer.toString(id));
		*/
		System.out.println("GetEmp by id method, db hit");
		//System.out.println(dept);
		return emp;
	}
	
	public List<Employee> getEmployeeByDeptId(int department_id) {
		List<Employee> emps=(List<Employee>) employeeRepository.findByDepartmentId(department_id);
		//System.out.println("Successful"+emps);
		return emps;
		//return new ArrayList<Employee>();
	}
	
	public void addEmployee(Employee emp) {
		employeeRepository.save(emp);
	}
	
	public void updateEmployee(Employee emp,int id) {
		employeeRepository.save(emp);
	}
	
	public void deleteEmployee(int id) {
		employeeRepository.deleteById(id);
	}
	
	static class AddEmployees implements Runnable{
		public List<Employee> emps;
		public int department_id;
		public EmployeeRepository empRepository;
		public AddEmployees(List<Employee> emps,int department_id,EmployeeRepository empRepository) {
			this.emps=emps;
			this.department_id=department_id;
			this.empRepository=empRepository;
		}
		public void run() {
			for(Employee emp: emps) emp.setDepartment(department_id);
			System.out.println(emps);
			//System.out.println(emps.size());
			//EmployeeRepository empRepository=new EmployeeRepository();
			empRepository.saveAll(emps);
		}
	}
	
	public ResponseEntity<?> addEmployeesFromFile(MultipartFile file,int department_id) {
		ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
	    try {
	        TypeFactory typeFactory = objectMapper.getTypeFactory();
	        CollectionType collectionType = typeFactory.constructCollectionType(
	            List.class, Employee.class);
	        ArrayList<Employee> employeeList = objectMapper.readValue(file.getInputStream(), collectionType);
	        if(employeeList.size()<10) return new ResponseEntity<>("Failed: Array size should not be less than 10.",HttpStatus.METHOD_FAILURE);
	        int seg_size=employeeList.size()/4;
	        int ix=0;
	        ExecutorService service=Executors.newFixedThreadPool(4);
	        for(int i=0;i<4;i++) {
	        	//System.out.println(temp);
	        	service.execute(new AddEmployees(employeeList.subList(ix, Math.min(employeeList.size(),ix+seg_size)),department_id,employeeRepository));
	        	ix+=seg_size;
	        }
	        
	        //System.out.println(employeeList.size());
	        //System.out.println(employeeSubLists);
	        

	    } catch (Exception e) {
	    	System.out.println(e);
	    	return new ResponseEntity<>("Failed due to exception",HttpStatus.METHOD_FAILURE);
	    }
	    return new ResponseEntity<>("Success",HttpStatus.OK);
	}
}
