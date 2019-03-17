package com.rav.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class LoadCustomerTransactions {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @RequestMapping(name = "/load", method = RequestMethod.GET)
    public String load() {
        JobExecution exec = null;
        try {
            exec = jobLauncher.run(job, new JobParameters());
            while (exec.isRunning()) {
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return String.format("job start : %tT end : %tT status : %s ",
                exec.getCreateTime(),
                exec.getEndTime(),
                exec.getStatus());
    }

}
