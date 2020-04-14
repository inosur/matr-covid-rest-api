package matr.covid.api.scheduler;

import matr.covid.api.service.SyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author osvaldo
 */
@Component
public class SchedulerManager {

    /**
     * logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(SchedulerManager.class);
    @Autowired
    private SyncService syncService;

    @Scheduled(cron = "${layers.refresh.cron:0 0 6 * * ? }") //everyday 6am.
    public void runScheduledSync() {
        long startTime = System.currentTimeMillis();
        LOG.info(("Starting layer synchronization: "));
        syncService.runSync();
        long endTime = (startTime - System.currentTimeMillis()) / 1000l;
        LOG.info("Finishing layer synchronization, it took {} seconds.", endTime);

    }

}
