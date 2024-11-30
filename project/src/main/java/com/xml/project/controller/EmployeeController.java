package com.xml.project.controller;
import com.xml.project.model.Employee;
import com.xml.project.service.EmployeeService;
import com.xml.project.service.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController //this annotation is a combination of @Controller and @ResponseBody.
@RequestMapping("/adminDashboard/employees")//All methods in this controller will be accessible under URLs starting with /adminDashboard/employees
public class EmployeeController {
	private final EmployeeService employeeService;
	public EmployeeController() {
		this.employeeService=new EmployeeServiceImpl();
	}
	//fetch an employee by id
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id){
		Employee employee=employeeService.findEmployeeById(id);
		if(employee!= null) {
			return ResponseEntity.ok(employee);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		}
	//fetch all employees
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees(){
		List<Employee> employees=employeeService.getAllEmployees();
		return ResponseEntity.ok(employees);
	}
	//add new employee
	 @PostMapping
	    public ResponseEntity<String> addEmployee(@RequestBody Employee newEmployee) {
	        employeeService.addEmployee(newEmployee);
	        return ResponseEntity.status(HttpStatus.CREATED).body("Employee added successfully");
	    }
	// Update an employee by ID
	    @PutMapping("/{id}")
	    //@PathVariable: Extracts the {id} from the URL
	    public ResponseEntity<String> updateEmployee(@PathVariable int id, @RequestBody Employee updatedEmployee) {
	        Employee employee = employeeService.updateEmployeeById(id, updatedEmployee);
	        if (employee != null) {
	            return ResponseEntity.ok("Employee updated successfully");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
	        }
	    }
	    // Delete an employee by ID
	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
	        boolean removed = employeeService.deleteEmployeeById(id);
	        if (removed) {
	            return ResponseEntity.ok("Employee deleted successfully");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
	        }
	    }
	    //Used to handle HTTP responses with appropriate status codes (e.g., 200 OK, 201 Created, 404 Not Found).
	
	
	}
	


