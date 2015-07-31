package demo.aggregates

import demo.entities.Aggregate
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Transient

/**
 * @author Steve Pember
 */
@Entity
@Table(name = "product")
@EqualsAndHashCode
@CompileStatic
class Product extends Aggregate {
    String sku

    String name

    @Transient
    int priceInCents = 0

    @Transient
    int inventoryOnHand = 0

}
