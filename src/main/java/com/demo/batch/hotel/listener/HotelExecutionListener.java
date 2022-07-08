package com.demo.batch.hotel.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class HotelExecutionListener implements JobExecutionListener {

    Logger logger = LoggerFactory.getLogger(HotelExecutionListener.class);

    public void beforeJob(JobExecution jobExecution) {
        logger.info("Called beforeJob().");
    }

    public void afterJob(JobExecution jobExecution) {
        logger.info("Called afterJob().");
    }
}
