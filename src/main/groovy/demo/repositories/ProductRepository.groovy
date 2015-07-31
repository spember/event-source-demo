package demo.repositories

import demo.aggregates.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

/**
 * @author Steve Pember
 */
interface ProductRepository extends AggregateJpaRepository<Product, UUID> {
    @Override
    @Query(value = "select p.revision from Product p where p.id = ?1")
    int selectRevisionById(UUID id)
}
