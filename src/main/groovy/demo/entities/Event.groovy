package demo.entities

import com.thirdchannel.eventsource.Aggregate
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.hibernate.annotations.Type

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

/**
 * @author Steve Pember
 */
@CompileStatic
@Table(name="event")
@Slf4j
@Entity
abstract class Event<A extends Aggregate> implements com.thirdchannel.eventsource.Event<A> {

    @Id
    @Type(type="pg-uuid")
    UUID id = UUID.randomUUID()

    @NotNull
    @Min(0l)
    int revision = 0

    @NotNull
    @Type(type="pg-uuid")
    UUID aggregateId

    @NotNull
    Date date = new Date()

    @NotNull
    Date dateEffective = new Date()

    @NotNull
    String data

    @NotNull
    String userId

    // not terribly useful for JPA, as we have discriminator values and multi-table inheritance
    String getClazz() {
        ""
    }

    void setClazz(String c) {

    }
}