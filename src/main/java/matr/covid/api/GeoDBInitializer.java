package matr.covid.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author osvaldo
 */
@Component
public class GeoDBInitializer implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(GeoDBInitializer.class);

    @Autowired
    private JdbcTemplate template;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        LOG.info("initalize geoDb.");

        template.execute("CREATE ALIAS InitGeoDB for \"geodb.GeoDB.InitGeoDB\";");
        template.execute("CALL InitGeoDB();");
    }
}
