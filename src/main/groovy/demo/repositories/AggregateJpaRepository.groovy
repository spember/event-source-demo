package demo.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

/**
 * @author Steve Pember
 */
@NoRepositoryBean
interface AggregateJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    int selectRevisionById(UUID id)
}