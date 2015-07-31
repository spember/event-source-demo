package demo.events.product

import demo.aggregates.Product
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

/**
 * @author Steve Pember
 */
@Entity
@EqualsAndHashCode
@DiscriminatorValue("product:inventory:add")
@CompileStatic
class InventoryAddedEvent extends InventoryEvent {
    @Override
    void process(Product aggregate) {
        aggregate.inventoryOnHand += count
    }
}
