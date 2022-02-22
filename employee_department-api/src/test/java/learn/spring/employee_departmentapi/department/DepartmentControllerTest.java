package learn.spring.employee_departmentapi.department;

import static io.restassured.RestAssured.given;
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

class DepartmentControllerTest {
	@InjectMocks
	DepartmentController departmentController;
	@Mock
	DepartmentService departmentService;
	Department dept;
	public final String CONTEXT_PATH="/department";
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	final void testGetDepartment() {
		dept=new Department();
		dept.setId(0);
		dept.setName("Tech User Platform");
		dept.setLocation("Virtual");
		when(departmentService.getDepartment(anyInt())).thenReturn(java.util.Optional.of(dept));
		java.util.Optional<Department> opDeptRes=departmentController.getDepartment(0);
		Department resDept=opDeptRes.get();
		assertNotNull(resDept);
		assertEquals("Tech User Platform",resDept.getName());
		assertEquals("Virtual",resDept.getLocation());
		assertEquals(0,resDept.getId());
	}
	
	
	@Test
	final void testPostDepartment() {
		RestAssured.baseURI="http://localhost";
		RestAssured.port=8080;
		Map<String,Object> deptDtls=new HashMap<>();
		deptDtls.put("name", "Tech User Platform");
		deptDtls.put("location", "Virtual");
		Response response = given().
		contentType("application/json").
		accept("application/json").
		body(deptDtls).
		when().
		post(CONTEXT_PATH).
		then().
		statusCode(200).
		contentType("").
		extract().
		response();
		//assertNull(response.jsonPath());
	}

}
