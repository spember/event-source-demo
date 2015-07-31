package demo.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.thirdchannel.eventsource.AbstractFunctionalAggregate
import groovy.transform.CompileStatic
import org.hibernate.annotations.Type

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType
import javax.persistence.Table
import javax.persistence.Transient
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

/**
 * @author Steve Pember
 */
@CompileStatic
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="aggregate")
@Entity
abstract class Aggregate extends AbstractFunctionalAggregate {

    @Id
    @Type(type="pg-uuid")
    UUID id = UUID.randomUUID()

    @JsonIgnore
    @NotNull
    @Min(0l)
    int revision = 0

    // Note that active is not marked as transient, yet is not a 'core' Aggregate property
    // It's useful to maintain active as a 'soft delete' flag and query over all
    // objects accordingly
    @NotNull
    @JsonIgnore
    Boolean active = true

    @JsonIgnore
    @Transient
    List<? extends com.thirdchannel.eventsource.Event> uncommittedEvents = []

}