package simplon.pros.repository;

import simplon.pros.domain.Transfert;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Transfert entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransfertRepository extends JpaRepository<Transfert, Long> {
}
