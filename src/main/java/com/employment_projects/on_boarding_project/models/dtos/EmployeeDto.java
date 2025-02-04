package com.employment_projects.on_boarding_project.models.dtos;

import java.time.LocalDateTime;

public class EmployeeDto {

    private Long id;
    private String name;
    private String title;
    private String department;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public EmployeeDto(Long id, String name, String title, String department, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.department = department;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public String getName() {
        return name;
    }

    public EmployeeDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public EmployeeDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public EmployeeDto setDepartment(String department) {
        this.department = department;
        return this;
    }
    public Long getId() {
        return id;
    }

    public EmployeeDto setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public EmployeeDto setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public EmployeeDto setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }
    //  private String department;
}
