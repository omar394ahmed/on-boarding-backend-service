package com.employment_projects.on_boarding_project.controllers;


import com.employment_projects.on_boarding_project.models.Employee;
import com.employment_projects.on_boarding_project.models.dtos.EmployeeDto;
import com.employment_projects.on_boarding_project.services.EmployeeService;
import com.employment_projects.on_boarding_project.utils.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("employees")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeResource {

    final EmployeeService employeeService;


    EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<ApiResponse<Employee>> createEmployee(@RequestBody Employee employee, UriComponentsBuilder uriBuilder) {
        employee.setId(null);
        Employee createdEmployee = employeeService.addNewEmployee(employee);

        // Build the URI for the updated resource
        URI location = uriBuilder.path("/employees/{id}").buildAndExpand(createdEmployee.getId()) // Ensure getId() returns the correct value
                .toUri();

        // Create an enriched response object
        ApiResponse<Employee> response = new ApiResponse<>("Employee updated successfully", HttpStatus.CREATED.value(), location.toString(), createdEmployee);

        return ResponseEntity.created(location).header(HttpHeaders.LOCATION, location.toString()) // HATEOAS Header
                .body(response);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<ApiResponse> getAllEmployees() {
        // Fetch the list of employees
        List<EmployeeDto> employees = employeeService.getEmployees();

        // Check if the list is empty
        if (employees == null || employees.isEmpty()) {
            // Return a response for an empty list
            ApiResponse<List<EmployeeDto>> emptyResponse = new ApiResponse<>(
                    "No employees found",
                    HttpStatus.OK.value(),
                    null,
                    employees
            );

            return ResponseEntity.status(HttpStatus.OK)
                    .body(emptyResponse);
        }

        // If employees exist, return them with a success message
        ApiResponse<List<EmployeeDto>> response = new ApiResponse<>(
                "Successfully retrieved all employees",
                HttpStatus.OK.value(),
                null,
                employees
        );

        return ResponseEntity.ok().body(response);
    }


    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> updateEmployee(@PathVariable Long id,
                                                      @RequestBody Employee employee ,
                                                      UriComponentsBuilder uriBuilder) {
        EmployeeDto updateEmployee = employeeService.updateEmployee(id, employee);

        // Build the URI for the updated resource
        URI location = uriBuilder.path("/employees/{id}").buildAndExpand(updateEmployee.getId()) // Ensure getId() returns the correct value
                .toUri();

        // Create an enriched response object
        ApiResponse<EmployeeDto> response = new ApiResponse<>("Employee updated successfully", HttpStatus.CREATED.value(), location.toString(), updateEmployee);

        return ResponseEntity.ok().header(HttpHeaders.LOCATION, location.toString()) // HATEOAS Header
                .body(response);

    }

}
