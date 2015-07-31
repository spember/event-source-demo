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
@DiscriminatorValue("product:inventory")
@CompileStatic
abstract class InventoryEvent extends Event<Product> {
    @Transient
    @EventData
    int count

    @Override
    void restoreData(Map data) {
        count = data.count as int
    }

}
