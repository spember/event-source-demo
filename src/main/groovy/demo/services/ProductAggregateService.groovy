package demo.services

import com.thirdchannel.eventsource.AggregateService
import demo.aggregates.Product
import demo.repositories.ProductRepository
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author Steve Pember
 */
@Slf4j
@Service
@CompileStatic
class ProductAggregateService extends AbstractBaseAggregateService<Product> {

    @Autowired ProductAggregateService(ProductRepository repo) {
        repository = repo
        log.info("Set repo of ${repository}")
    }

    @Override
    Product createInstance() {
        new Product()
    }
}
