package com.example.demo.models.interfaces;

import java.util.*;

public interface JobManagerInterface {
    Queue<JobInterface> queue = new LinkedList<>();
    Set<JobInterface> finishedJobs = new HashSet<>();
    Queue<JobInterface> getAllActiveJobs();
    Set<JobInterface> getFinishedJobs();
    boolean addJob(JobInterface newJob);
    void serveJob();
    void removeJob();
    String getJobStatus(int id);
}
