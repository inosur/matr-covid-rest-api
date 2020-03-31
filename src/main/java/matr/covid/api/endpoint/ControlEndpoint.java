package matr.covid.api.endpoint;

import matr.covid.api.service.SyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

/**
 *
 * @author osvaldo
 */
@RestController
@RequestMapping("control")
public class ControlEndpoint {

    private static final Logger LOG = LoggerFactory.getLogger(ControlEndpoint.class);
    @Autowired
    private SyncService service;

    @PostMapping("sync")
    public void runSync() {
//        return new WebAsyncTask(() -> {
//            LOG.debug("Starting async data synchronzation.");
            service.runSync();
//            return null;
//        });

    }

}
