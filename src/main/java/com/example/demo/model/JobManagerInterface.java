package com.example.demo.model;

public interface JobManagerInterface {
    void serveJob();
    void storeJob(JobInterface job);
    JobInterface getJobStatus(int id);
}
