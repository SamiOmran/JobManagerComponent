package com.example.demo.models;

import com.example.demo.models.interfaces.JobInterface;
import com.example.demo.models.interfaces.JobManagerInterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.Set;

public class EmailJobManager implements JobManagerInterface {
    private static final Logger logger = LoggerFactory.getLogger(EmailJobManager.class);
    @Override
    public Queue<JobInterface> getAllActiveJobs() {
        return queue;
    }

    @Override
    public Set<JobInterface> getFinishedJobs() {
        return finishedJobs;
    }

    @Override
    public boolean addJob(JobInterface newJob) {
        boolean added = queue.add(newJob);
        logging((EmailJob) newJob);
        return added;
    }

    @Override
    public void serveJob() {
        if (queue.isEmpty())
            return;

        EmailJob email = (EmailJob) queue.element();
        email.setStatus(Constants.STATUS_IN_PROGRESS);
        email.run();
        logging(email);
        removeJob();
    }

    @Override
    public void removeJob() {
        if (queue.isEmpty())
            return;
        EmailJob email = (EmailJob) queue.remove();
        email.setStatus(Constants.STATUS_DONE);
        email.setResult(Constants.RESULT_SUCCESS);
        finishedJobs.add(email);

        logging(email);
    }

    @Override
    public String getJobStatus(int id) {
        EmailJob[] emails = (EmailJob[]) convertQueue();

        for (EmailJob job : emails) {
            if (job.getId() == id) {
                return job.getStatus();
            }
        }
        return "Not found";
    }

    private void logging(EmailJob email) {
        logger.info("Email: " + email.getId() + " | " + email.getStatus() + ".");
    }
    private Object[] convertQueue() {
        return JobManagerInterface.queue.toArray();
    }
}
