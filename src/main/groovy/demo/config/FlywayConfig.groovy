package demo.config

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.flywaydb.core.Flyway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

/**
 * @author Steve Pember
 */
@CompileStatic
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
class FlywayConfig {

    @Autowired
    FlywayInitialization(Flyway flyway) {
        int count = flyway.migrate()
        log.info 'Flyway performed {} migrations', count
    }
}
