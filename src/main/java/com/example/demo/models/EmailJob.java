package com.example.demo.models;

import com.example.demo.models.interfaces.JobInterface;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EmailJob implements JobInterface {
    private final int id;
    private final String message;
    private String status;
    private String result;

    public EmailJob(@JsonProperty("id") int id, @JsonProperty("message") String message) {
        this.id = id;
        this.message = message;
    }

    public EmailJob(int id, String message, String status) {
        this.id = id;
        this.message = message;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }
}
