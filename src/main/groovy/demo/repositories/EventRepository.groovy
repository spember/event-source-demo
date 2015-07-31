package demo.repositories

import demo.entities.Event
import org.springframework.data.repository.CrudRepository

/**
 * @author Steve Pember
 */
interface EventRepository extends CrudRepository<Event, UUID> {
    List<Event> findByAggregateIdOrderByRevisionAsc(UUID aggregateId)
    List<Event> findAllByAggregateIdInOrderByRevisionAsc(List<UUID> aggregateIds)

    List<Event> findAllByAggregateIdAndDateEffectiveBeforeOrderByRevisionAsc(UUID aggregateId, Date date)
    List<Event> findAllByAggregateIdAndRevisionLessThanOrderByRevisionAsc(UUID aggregateId, int revision)

}