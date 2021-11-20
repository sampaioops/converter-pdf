package com.sampaio.converterpdf.job;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TestShedlockJob {
    private static final Logger logger = LoggerFactory.getLogger(TestShedlockJob.class);


    @Scheduled(cron = "0 0/1 * * * ?")
    @SchedulerLock(name = "TaskScheduler_scheduledTask", lockAtLeastFor = "PT30S", lockAtMostFor = "PT1M")
    public void process() {
        logger.info(LocalDateTime.now() + " - I'm job ");
    }
}
