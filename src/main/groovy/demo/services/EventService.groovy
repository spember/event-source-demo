package demo.services

import com.thirdchannel.eventsource.Aggregate
import com.thirdchannel.eventsource.Event
import demo.repositories.EventRepository
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author Steve Pember
 */
@Slf4j
@CompileStatic
@Service
class EventService implements com.thirdchannel.eventsource.EventService {

    private EventRepository repository

    @Autowired EventService(EventRepository repo) {
        repository = repo
    }

    @Override
    List<Event> findAllEventsForAggregate(Aggregate aggregate) {
        repository.findAllByAggregateIdInOrderByRevisionAsc([aggregate.id])
    }

    @Override
    List<Event> findAllEventsForAggregates(List<? extends Aggregate> aggregates) {
        repository.findAllByAggregateIdInOrderByRevisionAsc(aggregates.collect {Aggregate aggregate -> aggregate.id})
    }

    @Override
    List<Event> findAllEventsForAggregateInRange(Aggregate aggregate, Date begin, Date end) {
        return null
    }

    @Override
    List<Event> findAllEventsForAggregatesInRange(List<? extends Aggregate> aggregate, Date begin, Date end) {
        return null
    }

    @Override
    List<Event> findAllEventsForAggregateUpToRevision(Aggregate aggregate, int revision) {
        repository.findAllByAggregateIdAndRevisionLessThanOrderByRevisionAsc(aggregate.id, revision+1)
    }

    @Override
    List<Event> findAllEventsForAggregateUpToDateEffective(Aggregate aggregate, Date date) {
        repository.findAllByAggregateIdAndDateEffectiveBeforeOrderByRevisionAsc(aggregate.id, date)
    }

    @Override
    boolean save(Event event) {
        save([event])
    }

    @Override
    boolean save(List<? extends Event> events) {
        if (events.size() == 0) {
            log.warn("No events provided!")
            false
        }
        events.each {Event event->

            if (!event.userId) {
                event.userId = "realuser@test.com"
            }
        }
        repository.save(events as Iterable)
    }
}
