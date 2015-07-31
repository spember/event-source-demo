package demo.events

import com.thirdchannel.eventsource.annotation.EventData
import demo.entities.Aggregate
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
@DiscriminatorValue("aggregate:activated")
@CompileStatic
class ActivatedEvent extends Event<Aggregate>{

    @Transient
    @EventData
    boolean active

    @Override
    void restoreData(Map data) {
        active = data.active as boolean
    }

    @Override
    void process(Aggregate aggregate) {
        aggregate.active = active
    }
}
