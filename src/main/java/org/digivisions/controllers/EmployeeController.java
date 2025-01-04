package org.digivisions.controllers;

import org.digivisions.Services.EmployeeService;
import org.digivisions.models.EmployeeDTO;
import org.digivisions.models.EmployeeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public void create(@Valid @RequestBody EmployeeDTO employeeDTO){
		employeeService.create(employeeDTO);
	}

	@PutMapping
	public void update(@Valid @RequestBody EmployeeDTO employeeDTO){
		employeeService.update(employeeDTO);
	}

	@DeleteMapping("/{id}")
	public void delete(@NotNull @PathVariable("id") Long id){
		employeeService.delete(id);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDTO> retrieve(@PathVariable("id") Long id){
		return ResponseEntity.ok(employeeService.findOne(id));
	}

	@GetMapping()
	public ResponseEntity<EmployeeList> retrieve(){
		return ResponseEntity.ok(employeeService.listAll());
	}
}
