package demo.services

import com.thirdchannel.eventsource.Aggregate
import com.thirdchannel.eventsource.AggregateService
import demo.repositories.AggregateJpaRepository
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author Steve Pember
 */
@Slf4j
@CompileStatic
abstract class AbstractBaseAggregateService<T extends Aggregate> implements AggregateService<T> {
    AggregateJpaRepository<T, UUID> repository

    abstract T createInstance()

    @Override
    T get(UUID id) {
        repository.getOne(id)
    }

    @Override
    List<T> getAll(List<UUID> ids) {
        repository.findAll(ids)
    }

    @Override
    T getOrCreate(UUID id) {
        T instance = (T)repository.getOne(id)
        if (!instance) {
            createInstance()
        }
    }

    @Override
    boolean exists(UUID id) {
        repository.exists(id)
    }

    @Override
    int getCurrentRevision(UUID id) {
        repository.selectRevisionById(id)
    }

    @Override
    int update(T aggregate, int expectedRevision) {
        save(aggregate)
    }

    @Override
    int save(T aggregate) {
        if (repository.save(aggregate)) {
            1
        } else {
            0
        }
    }

    @Override
    int save(List<T> aggregates) {
        repository.save(aggregates).size()
    }
}
