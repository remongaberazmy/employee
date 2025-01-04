package org.digivisions.controllers;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.digivisions.Services.EmployeeService;
import org.digivisions.models.BaseModel;
import org.digivisions.models.EmployeeDTO;
import org.digivisions.models.EmployeeList;
import org.digivisions.utils.Constants;
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
	@RateLimiter(name = "apiLimiter", fallbackMethod = "rateLimitFallback")
	public ResponseEntity<BaseModel> create(@Valid @RequestBody EmployeeDTO employeeDTO){
		return handle(employeeService.create(employeeDTO));
	}

	@PutMapping
	public ResponseEntity<BaseModel> update(@Valid @RequestBody EmployeeDTO employeeDTO){
		return handle(employeeService.update(employeeDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<BaseModel> delete(@NotNull @PathVariable("id") Long id){
		return handle(employeeService.delete(id));
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDTO> retrieve(@PathVariable("id") Long id){
		return ResponseEntity.ok(employeeService.findOne(id));
	}

	@GetMapping()
	public ResponseEntity<EmployeeList> retrieve(){
		return ResponseEntity.ok(employeeService.listAll());
	}

	public ResponseEntity<BaseModel> rateLimitFallback(Exception e) {
		BaseModel baseModel = new BaseModel();
		baseModel.setReplyMessage(Constants.RATE_LIMIT_EXCEEDED);
		baseModel.setReplyCode(Constants.RATE_LIMIT_EXCEEDED_CODE);
		return handle(baseModel);
	}

	public <T extends BaseModel> ResponseEntity<T> handle(T data) {
		return ResponseEntity.status(data.getReplyCode()).body(data);
	}
}
