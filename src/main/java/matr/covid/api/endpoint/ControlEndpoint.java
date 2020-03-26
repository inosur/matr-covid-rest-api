package matr.covid.api.endpoint;

import matr.covid.api.service.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author osvaldo
 */
@RestController
@RequestMapping("control")
public class ControlEndpoint {

    @Autowired
    private SyncService service;

    @PostMapping("sync")
    public void runSync() {
        service.runSync();
    }

}
