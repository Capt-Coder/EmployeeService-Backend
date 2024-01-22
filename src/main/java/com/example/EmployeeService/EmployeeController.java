package com.example.EmployeeService;

import jakarta.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController 
{
  @Autowired(required=true)
  private EmployeeService service;
  
  @GetMapping("/empservice")
  public java.util.List<Employee>list()
  {
	  return service.listAll();
  }

  @GetMapping("/empservice/{Id}")
  public ResponseEntity <Employee> get(@PathVariable Integer Id)
  {
	  try
	  {
		Employee emp = service.get(Id);
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
	  }
	  catch(NoResultException e)
	  {
		  return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
	  }
   
  }

  @PostMapping("/SaveEmployee")
  public void add(@RequestBody Employee emp)
  {
	  service.save(emp);
  }


  @PutMapping("/UpdateEmployee/{emp_Id}")
	public ResponseEntity<?> update(@RequestBody Employee updatedEmployee, @PathVariable Integer emp_Id) {
		Employee existingEmployee = service.get(emp_Id);

		if (existingEmployee != null) {
			existingEmployee.setName(updatedEmployee.getName());
			service.save(existingEmployee);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}



  @DeleteMapping("/DeleteEmployee/{emp_Id}")
  public void delete (@PathVariable Integer emp_Id)
  {
	  service.delete(emp_Id);
  }
   
}
