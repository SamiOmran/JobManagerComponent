package com.example.demo.bootstrap;

import com.example.demo.models.interfaces.JobInterface;
import com.example.demo.models.interfaces.JobManagerInterface;
import com.example.demo.models.Constants;
import com.example.demo.models.EmailJob;
import com.example.demo.models.EmailJobManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class LoadData {
    private static final JobManagerInterface jobManager = new EmailJobManager();

    @Bean
    void generateJobs() {
        JobInterface newJob = new EmailJob(1, "First Email", Constants.STATUS_PENDING);
        jobManager.addJob(newJob);
    }
}
