package learn.spring.employee_departmentapi.department;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class DepartmentServiceTest {

	@InjectMocks
	DepartmentService departmentService;
	@Mock
	DepartmentRepository departmentRepository;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	void testGetDepartment() {
		Department dept=new Department();
		dept.setId(0);
		dept.setName("Tech User Platform");
		dept.setLocation("Virtual");
		
		
		when(departmentRepository.findById(anyInt())).thenReturn(java.util.Optional.of(dept));
		java.util.Optional<Department> opDeptRes=departmentService.getDepartment(0);
		Department resDept=opDeptRes.get();
		assertNotNull(resDept);
		assertEquals("Tech User Platform",resDept.getName());
		assertEquals("Virtual",resDept.getLocation());
		assertEquals(0,resDept.getId());
	}

}
