package org.vasshaug.demicontt.batch;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.sql.OracleJoinFragment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.vasshaug.demicontt.json.ResultElement;
import org.vasshaug.demicontt.utility.RandomuserAPI;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class JobTask {

    private static final Logger log = LoggerFactory.getLogger(JobTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //inject configuration from application.properties
    @Value("${randomuser.endpoint.url}")
    private String url;

    @Value("${randomuser.endpoint.userSize}")
    private String userSize;

    // To test that Scheduling works
    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }

    @Scheduled(fixedRateString = "${jobtask.period.in.milliseconds}")
    public void getAndSaveAPIresults() {
        RandomuserAPI randomuserAPI = new RandomuserAPI();

        ResultElement results = randomuserAPI.getResults(url, userSize);
        log.info("Results = " + results);

        ObjectMapper mapper = new ObjectMapper();
    }

}
