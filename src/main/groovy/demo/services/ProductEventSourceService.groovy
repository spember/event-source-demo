package demo.services

import com.thirdchannel.eventsource.EventSourceService
import demo.aggregates.Product
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author Steve Pember
 */
@Slf4j
@CompileStatic
@Service
class ProductEventSourceService extends EventSourceService<Product> {

    @Autowired ProductEventSourceService(ProductAggregateService pas, EventService es) {
        log.info("Using ${pas} and ${es}")
        aggregateService = pas
        eventService = es
    }
}
