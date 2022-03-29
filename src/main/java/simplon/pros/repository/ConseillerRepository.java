package simplon.pros.repository;

import simplon.pros.domain.Conseiller;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Conseiller entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConseillerRepository extends JpaRepository<Conseiller, Long> {
}
