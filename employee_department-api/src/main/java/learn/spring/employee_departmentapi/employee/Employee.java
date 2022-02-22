package learn.spring.employee_departmentapi.employee;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import learn.spring.employee_departmentapi.department.Department;

@Entity
public class Employee implements Serializable {
	private static final long serialVersionUID = 6529685098267757690L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String location;
	private String manager_id;
	private String hire_date;
	private int salary;
	@Embedded
	@ManyToOne
	private Department department;
	//private int departmentid;
	public Employee() {
		
	}
	
	public Employee(String name, int department_id, String manager_id, String hire_date, int salary,
			String location) {
		super();
		//this.id = id;
		this.name = name;
		this.location = location;
		this.manager_id = manager_id;
		this.hire_date = hire_date;
		this.salary = salary;
		this.department=new Department();
		this.department.setId(department_id);
		//this.departmentid=department_id;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setDepartment(int department_id) {
		this.department=new Department();
		this.department.setId(department_id);
		//this.departmentid=department_id;
	}
	public int getDepartmentId() {
		return department.getId();
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", location=" + location + ", manager_id=" + manager_id
				+ ", hire_date=" + hire_date + ", salary=" + salary + ", department=" + department + "]";
	}
	
}
