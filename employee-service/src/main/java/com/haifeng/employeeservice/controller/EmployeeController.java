package com.haifeng.employeeservice.controller;

import com.haifeng.employeeservice.model.Employee;
import com.haifeng.employeeservice.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeRepository repository;

    @PostMapping
    public Employee add(@RequestBody Employee employee) {
        log.info("Employee add: {}", employee);
        return repository.add(employee);
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") Long id) {
        log.info("Employee find: id={}", id);
        return repository.findById(id);
    }

    @GetMapping
    public List findAll() {
        log.info("Employee find");
        return repository.findAll();
    }

    @GetMapping("/department/{departmentId}")
    public List findByDepartment(@PathVariable("departmentId") Long departmentId, Principal principal) {
        log.debug(principal.getName());
        log.info("Employee find: departmentId={}", departmentId);
        return repository.findByDepartment(departmentId);
    }

    @GetMapping("/organization/{organizationId}")
    public List findByOrganization(@PathVariable("organizationId") Long organizationId) {
        log.info("Employee find: organizationId={}", organizationId);
        return repository.findByOrganization(organizationId);
    }

}
