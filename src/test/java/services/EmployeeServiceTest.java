package services;

import org.digivisions.Services.EmployeeService;
import org.digivisions.models.BaseModel;
import org.digivisions.models.EmployeeDTO;
import org.digivisions.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;

	void testCreate(){
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setFirstName("Test");
		employeeDTO.setLastName("Test");
		employeeDTO.setEmail("test@test.com");
		employeeDTO.setDepartment("Test");
		employeeDTO.setSalary(BigDecimal.ZERO);

		BaseModel baseModel = employeeService.create(employeeDTO);
		assertEquals(Constants.CREATED, baseModel.getReplyMessage(), "Employee should be created");
	}
}
