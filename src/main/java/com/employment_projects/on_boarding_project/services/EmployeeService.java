package com.employment_projects.on_boarding_project.services;


import com.employment_projects.on_boarding_project.models.Employee;
import com.employment_projects.on_boarding_project.models.dtos.EmployeeDto;
import com.employment_projects.on_boarding_project.repos.EmployeeRepo;
import com.employment_projects.on_boarding_project.utils.exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    final EmployeeRepo employeeRepo;
    List<Employee> employees = new ArrayList<>();

    EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    private EmployeeDto mapEmployeetoEmployeeDto(Employee employee) {
        return new EmployeeDto(employee.getId(), employee.getName(), employee.getTitle(), employee.getDepartment(), employee.getCreatedDate(), employee.getUpdatedDate());
    }

    public Employee addNewEmployee(Employee employee) {
        if(employeeRepo.existsByName(employee.getName())){
            throw new CustomException(HttpStatus.BAD_REQUEST, String.format("Employee with name %s already exists", employee.getName()));
        }
        return employeeRepo.save(employee);
    }

    public List<EmployeeDto> getEmployees() {
        List<Employee> all = employeeRepo.findAll();

        if (employees != null) {
            return all.stream().map(employee -> {
                return mapEmployeetoEmployeeDto(employee);
            }).toList();
        }
        return Collections.emptyList();

    }

    public EmployeeDto updateEmployee(Long id, Employee updatedEmployee) {
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setName(updatedEmployee.getName());
            employee.setTitle(updatedEmployee.getTitle());
            employee.setDepartment(updatedEmployee.getDepartment());
            Employee updated = employeeRepo.save(employee);
            return mapEmployeetoEmployeeDto(updated); // Save updated employee

        } else {
            throw new CustomException(HttpStatus.NOT_FOUND, String.format("Employee with ID %d not found", id));
        }
    }

}
