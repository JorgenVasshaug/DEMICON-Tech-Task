package com.github.jorgenvasshaug.springreactRU.batch;

import com.github.jorgenvasshaug.springreactRU.domain.entity.randomuser.Result;
import com.github.jorgenvasshaug.springreactRU.domain.service.ResultsService;
import com.github.jorgenvasshaug.springreactRU.utility.RandomuserAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import com.github.jorgenvasshaug.springreactRU.domain.entity.string.RawString;
import com.github.jorgenvasshaug.springreactRU.domain.service.RawStringService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class JobTask {

    private static final Logger log = LoggerFactory.getLogger(JobTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private ResultsService resultsService;
    private RawStringService rawStringService;

    //inject configuration from application.properties
    @Value("${randomuser.endpoint.url}")
    private String url;

    @Value("${randomuser.endpoint.userSize}")
    private String userSize;

    public JobTask(ResultsService resultsService, RawStringService rawStringService) {
        this.resultsService = resultsService;
        this.rawStringService = rawStringService;
    }

    // To test that Scheduling works
    @Scheduled(fixedRate = 300000)
    public void reportCurrentTime() {
        log.info("Jobtask running - The time is now {}", dateFormat.format(new Date()));
    }

    // Fetch randomusers from API and save in DB as String
    @Scheduled(fixedRateString = "${jobtask.period.in.milliseconds}")
    public void getAndSaveAPIrawString() {
        String rawStringresult = null;
        try {
            rawStringresult = RandomuserAPI.getRaw(url, userSize);
        } catch ( RestClientException e) {
            log.error("" + e);
        }
        log.info("Results = " + rawStringresult);

        RawString rawString = new RawString();
        rawString.setRawString(rawStringresult);

        rawStringService.save(rawString);

        log.info("Results saved!");
    }

    // Fetch randomusers from API and save in DB as Objects
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
