package learn.spring.employee_departmentapi.employee;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

class EmployeeServiceTest {

	@InjectMocks
	EmployeeService employeeService;
	@Mock
	EmployeeRepository employeeRepository;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	void testGetEmployee() {
		Employee emp=new Employee();
		emp.setId(0);
		emp.setDepartment(0);
		emp.setName("Wajahat");
		emp.setLocation("Dibrugarh");
		emp.setSalary(80000);
		
		when(employeeRepository.findById(anyInt())).thenReturn(java.util.Optional.of(emp));
		java.util.Optional<Employee> opEmpRes=employeeService.getEmployee(0);
		Employee resEmp=opEmpRes.get();
		assertNotNull(resEmp);
		assertEquals("Wajahat",resEmp.getName());
		assertEquals("Dibrugarh",resEmp.getLocation());
		assertEquals(80000,resEmp.getSalary());
		assertEquals(0,resEmp.getDepartmentId());
		assertEquals(0,resEmp.getId());
	}

}
