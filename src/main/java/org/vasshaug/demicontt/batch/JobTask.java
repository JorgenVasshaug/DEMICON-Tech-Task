package org.vasshaug.demicontt.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.vasshaug.demicontt.domain.Result;
import org.vasshaug.demicontt.service.ResultsService;
import org.vasshaug.demicontt.utility.RandomuserAPI;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class JobTask {

    private static final Logger log = LoggerFactory.getLogger(JobTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private ResultsService resultsService;

    //inject configuration from application.properties
    @Value("${randomuser.endpoint.url}")
    private String url;

    @Value("${randomuser.endpoint.userSize}")
    private String userSize;

    public JobTask(ResultsService resultsService) {
        this.resultsService = resultsService;
    }

    // To test that Scheduling works
    @Scheduled(fixedRate = 300000)
    public void reportCurrentTime() {
        log.info("Jobtask running - The time is now {}", dateFormat.format(new Date()));
    }

    // Fetch randomusers from API and save in DB
    @Scheduled(fixedRateString = "${jobtask.period.in.milliseconds}")
    public void getAndSaveAPIresults() {
        Result results = null;
        try {
            results = RandomuserAPI.getResults(url, userSize);
        } catch ( RestClientException e) {
            log.error("" + e);
        }
        log.info("Results = " + results);

        resultsService.save(results);

        log.info("Results saved!");
    }

}
