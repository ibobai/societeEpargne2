package simplon.pros.repository;

import simplon.pros.domain.Compte;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Compte entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {
}
