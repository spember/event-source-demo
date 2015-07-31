package demo.services

import demo.AbstractIntegrationSpec
import demo.aggregates.Product
import demo.events.product.InventoryAddedEvent
import demo.events.product.InventoryEvent
import demo.events.product.NameChangedEvent
import demo.events.product.PriceChangedEvent
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Subject

/**
 * @author Steve Pember
 */
@Slf4j
class ProductEventSourceServiceSpec extends AbstractIntegrationSpec {

    @Subject @Autowired ProductEventSourceService productEventSourceService

    void "When applying changes, the current state of the Product should reflect those changes" () {
        when:
            Product product = new Product(sku: "TEST-01")

            product.applyChanges([
                    new InventoryAddedEvent(count: 10),
                    new NameChangedEvent(name: "Test Product")
            ])
            productEventSourceService.save(product)

            Product check = productEventSourceService.getCurrent(product.id)


        then:
            check.name == "Test Product"
            check.inventoryOnHand == 10
    }

    void "After applying changes, event state is retrievable at a particular revision"() {

        when:
        Product product = new Product(sku: "TEST-01")
        product.applyChanges([new NameChangedEvent(name: "Test Product"), new PriceChangedEvent(priceInCents: 3000, dateEffective: new Date()-32)])

        for (int i = 30; i > 0; i--) {
            product.applyChange(new PriceChangedEvent(priceInCents: product.priceInCents*0.95, dateEffective: new Date()-i))
            productEventSourceService.save(product)
        }

        // todo: make this 'time travel' a bit more streamlined
        // but, let's see what the price was 10 days ago
        Product check = new Product()
        check.id = product.id
        productEventSourceService.loadHistoryUpTo(check, new Date()-10)


        then:

        check.priceInCents == 1015
        // vs current
        product.priceInCents == 635
    }
}
