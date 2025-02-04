package com.employment_projects.on_boarding_project.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) // Excludes null fields from response
public class ApiResponse<T> {
    private String message;
    private int status;
    private String resourceUri; // HATEOAS: Link to the updated resource
    private T data; // Generic payload (e.g., Employee, List<Employee>)

    public ApiResponse(String message, int status, String resourceUri, T data) {
        this.message = message;
        this.status = status;
        this.resourceUri = resourceUri;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

