package com.haifeng.organizationservice.client;

import java.util.List;

import com.haifeng.organizationservice.config.ClientConfiguration;
import com.haifeng.organizationservice.model.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "employee-service", configuration = ClientConfiguration.class)
@FeignClient(name = "employee-service")
public interface EmployeeClient {

	@GetMapping("/organization/{organizationId}")
	List<Employee> findByOrganization(@PathVariable("organizationId") Long organizationId);
	
}
