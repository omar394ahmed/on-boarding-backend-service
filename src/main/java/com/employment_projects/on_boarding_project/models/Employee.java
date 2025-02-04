package com.employment_projects.on_boarding_project.models;


@jakarta.persistence.Entity
public class Employee extends BaseEntity {

    private String name;
    private String title;
    private String department;


    public String getName() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Employee setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public Employee setDepartment(String department) {
        this.department = department;
        return this;
    }
    //  private String department;

}
