package learn.spring.employee_departmentapi.employee;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

class EmployeeControllerTest {
	@InjectMocks
	EmployeeController employeeController;
	@Mock
	EmployeeService employeeService;
	Employee emp;
	public final String CONTEXT_PATH="/employee";
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	final void testGetEmployee() {
		emp=new Employee();
		emp.setId(0);
		emp.setDepartment(0);
		emp.setName("Wajahat");
		emp.setLocation("Dibrugarh");
		emp.setSalary(80000);
		when(employeeService.getEmployee(anyInt())).thenReturn(java.util.Optional.of(emp));
		java.util.Optional<Employee> opEmpRes=employeeController.getEmployee(0);
		Employee resEmp=opEmpRes.get();
		assertNotNull(resEmp);
		assertEquals("Wajahat",resEmp.getName());
		assertEquals("Dibrugarh",resEmp.getLocation());
		assertEquals(80000,resEmp.getSalary());
		assertEquals(0,resEmp.getDepartmentId());
		assertEquals(0,resEmp.getId());
	}
	
	
	@Test
	final void testPostEmployee() {
		RestAssured.baseURI="http://localhost";
		RestAssured.port=8080;
		Map<String,Object> empDtls=new HashMap<>();
		empDtls.put("name", "Wajahat");
		empDtls.put("location", "Dibrugarh");
		empDtls.put("salary", 80000);
		empDtls.put("hire_date", "2022-01-01");
		Response response = given().
		contentType("application/json").
		accept("application/json").
		body(empDtls).
		when().
		post(CONTEXT_PATH+"/19").
		then().
		statusCode(200).
		contentType("").
		extract().
		response();
		//assertNull(response.jsonPath());
	}
	

}
