package com.employment_projects.on_boarding_project.utils.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

import java.time.LocalDateTime;
import java.util.Locale;


public class CustomErrorResponse implements ErrorResponse {

    private final HttpStatusCode statusCode;
    private final int status;
    private final String message;
    private final String path;
    private final LocalDateTime timestamp;

    public CustomErrorResponse(HttpStatusCode statusCode, int status, String message, String path) {
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

    @Override
    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return headers;
    }

    @Override
    public ProblemDetail getBody() {
        ProblemDetail problemDetail = ProblemDetail.forStatus(statusCode);
        problemDetail.setDetail(message);
        problemDetail.setProperty("timestamp", timestamp.toString());
        problemDetail.setProperty("path", path);
        return problemDetail;
    }

    @Override
    public String getTypeMessageCode() {
        return "error.type";
    }

    @Override
    public String getTitleMessageCode() {
        return "error.title";
    }

    @Override
    public String getDetailMessageCode() {
        return "error.detail";
    }

    @Override
    public Object[] getDetailMessageArguments() {
        return new Object[]{message};
    }

    @Override
    public Object[] getDetailMessageArguments(MessageSource messageSource, Locale locale) {
        return new Object[]{messageSource.getMessage("error.detail", null, locale)};
    }

    @Override
    public ProblemDetail updateAndGetBody(MessageSource messageSource, Locale locale) {
        ProblemDetail problemDetail = getBody();
        problemDetail.setProperty("localizedMessage", messageSource.getMessage("error.detail", null, locale));
        return problemDetail;
    }
}
