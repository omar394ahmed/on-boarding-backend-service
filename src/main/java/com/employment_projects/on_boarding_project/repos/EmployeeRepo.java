package com.employment_projects.on_boarding_project.repos;

import com.employment_projects.on_boarding_project.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepo extends JpaRepository<Employee , Long> {

    boolean existsByName(String name);

}
