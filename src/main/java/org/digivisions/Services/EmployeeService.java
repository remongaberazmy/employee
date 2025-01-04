package org.digivisions.Services;

import lombok.extern.slf4j.Slf4j;
import org.digivisions.entities.EmployeeEntity;
import org.digivisions.exceptions.EmployeeNotFoundException;
import org.digivisions.exceptions.InvalidInputException;
import org.digivisions.models.BaseModel;
import org.digivisions.models.EmployeeDTO;
import org.digivisions.models.EmployeeList;
import org.digivisions.repositories.EmployeeRepository;
import org.digivisions.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public EmployeeDTO findOne(Long id){
		return map(employeeRepository.findByIdAndDeletedFalse(id).orElseThrow(EmployeeNotFoundException::new));
	}

	public EmployeeList listAll(){
		EmployeeList employeeList = new EmployeeList();
		List<EmployeeEntity> employees = employeeRepository.findAllAndDeletedFalse();
		employeeList.setEmployees(
			employees.parallelStream().map(this::map).collect(Collectors.toList())
		);
		return employeeList;
	}

	public BaseModel create(EmployeeDTO employeeDTO){
		BaseModel baseModel = new BaseModel();
		log.info("Trying to create Employee: {}", employeeDTO);
		employeeRepository.save(map(employeeDTO));
		baseModel.setReplyMessage(Constants.CREATED);
		baseModel.setReplyCode(Constants.OK);
		return baseModel;
	}

	public void update(EmployeeDTO employeeDTO){
		Long employeeId = Optional.ofNullable(employeeDTO.getId()).orElseThrow(InvalidInputException::new);
		EmployeeEntity employeeEntity = employeeRepository.findByIdAndDeletedFalse(employeeId).orElseThrow(EmployeeNotFoundException::new);
		employeeRepository.save(map(employeeDTO, employeeEntity));
	}

	public void delete(Long employeeId){
		log.info("Trying to delete Employee with id:{}", employeeId);
		EmployeeEntity employeeEntity = employeeRepository.findByIdAndDeletedFalse(employeeId).orElseThrow(EmployeeNotFoundException::new);
		employeeEntity.setDeleted(true);
		employeeRepository.save(employeeEntity);
	}

	private EmployeeDTO map(EmployeeEntity employeeEntity){
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setId(employeeEntity.getId());
		employeeDTO.setFirstName(employeeEntity.getFirstName());
		employeeDTO.setLastName(employeeEntity.getLastName());
		employeeDTO.setEmail(employeeEntity.getEmail());
		employeeDTO.setDepartment(employeeEntity.getDepartment());
		employeeDTO.setSalary(employeeEntity.getSalary());
		return employeeDTO;
	}

	private EmployeeEntity map(EmployeeDTO employeeDTO){
		EmployeeEntity employeeEntity = new EmployeeEntity();
		employeeEntity.setFirstName(employeeDTO.getFirstName());
		employeeEntity.setLastName(employeeDTO.getLastName());
		employeeEntity.setEmail(employeeDTO.getEmail());
		employeeEntity.setDepartment(employeeDTO.getDepartment());
		employeeEntity.setSalary(employeeDTO.getSalary());
		return employeeEntity;
	}

	private EmployeeEntity map(EmployeeDTO employeeDTO, EmployeeEntity employeeEntity){
		employeeEntity.setFirstName(employeeDTO.getFirstName());
		employeeEntity.setLastName(employeeDTO.getLastName());
		employeeEntity.setEmail(employeeDTO.getEmail());
		employeeEntity.setDepartment(employeeDTO.getDepartment());
		employeeEntity.setSalary(employeeDTO.getSalary());
		return employeeEntity;
	}

}
