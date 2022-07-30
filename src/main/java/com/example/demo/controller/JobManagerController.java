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
    private static final Logger logger = LoggerFactory.getLogger(JobManagerController.class);

    @GetMapping("/")
    public Queue<JobInterface> getAllJobs() {
        return jobManager.getAllJobs();
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
}
