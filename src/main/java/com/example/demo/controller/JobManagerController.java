package com.example.demo.controller;

import com.example.demo.models.Constants;
import com.example.demo.models.EmailJob;
import com.example.demo.models.interfaces.JobInterface;
import com.example.demo.models.interfaces.JobManagerInterface;
import com.example.demo.models.EmailJobManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Queue;
import java.util.Set;

@RestController
public class JobManagerController {
    JobManagerInterface jobManager = new EmailJobManager();

    @GetMapping("/active-jobs")
    public Queue<JobInterface> getAllActiveJobs() {
        return jobManager.getAllActiveJobs();
    }

    @GetMapping("/finished-jobs")
    public Set<JobInterface> getFinishedJobs() {
        return jobManager.getFinishedJobs();
    }

    @PostMapping("/")
    public boolean addNewJob(@RequestBody EmailJob newJob) {
        newJob.setStatus(Constants.STATUS_PENDING);
        return jobManager.addJob(newJob);
    }

    @GetMapping("/serve")
    public Set<JobInterface> serveJobs() {
        while(!jobManager.getAllActiveJobs().isEmpty()) {
            jobManager.serveJob();
        }
        return jobManager.getFinishedJobs();
    }
}
