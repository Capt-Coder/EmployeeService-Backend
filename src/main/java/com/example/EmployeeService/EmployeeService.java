package com.example.EmployeeService;

import java.util.List;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class EmployeeService 

{
  @Autowired
  private EmployeeRepository repo;
        
      public List<Employee> listAll()
      {
    	  return repo.findAll();
      }
      
      public void save(Employee admin)
      {
    	  repo.save(admin);
      }
      
      public Employee get(Integer Id)
      {
    	  return repo.findById(Id).get();
      }
      
      public void delete(Integer Id)
      {
    	  repo.deleteById(Id);
      }
  
}
