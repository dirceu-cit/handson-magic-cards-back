package magiccards.repositories;

import magiccards.entities.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface CardRepository extends PagingAndSortingRepository<Card,String> {

    @Query(value = "SELECT * FROM magiccard m where m.expansionId = :id",
            nativeQuery = true)
    Collection<Card> findByExpansionId(@Param("id") Integer id);

}