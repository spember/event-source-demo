package demo.events.product

import com.thirdchannel.eventsource.annotation.EventData
import demo.aggregates.Product
import demo.entities.Event
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.Transient

/**
 * @author Steve Pember
 */
@Entity
@EqualsAndHashCode
@DiscriminatorValue("product:price:changed")
@CompileStatic
class PriceChangedEvent extends Event<Product> {

    @Transient
    @EventData
    int priceInCents

    @Override
    void restoreData(Map data) {
        priceInCents = data.priceInCents as int
    }

    @Override
    void process(Product aggregate) {
        aggregate.priceInCents = priceInCents
    }
}
