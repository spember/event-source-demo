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
@DiscriminatorValue("product:name:changed")
@CompileStatic
class NameChangedEvent extends Event<Product> {
    @Transient
    @EventData
    String name

    @Override
    void restoreData(Map data) {
        name = data.name.toString()
    }

    @Override
    void process(Product aggregate) {
        aggregate.name = name
    }
}
