package simplon.pros.repository;

import simplon.pros.domain.Comptecou;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Comptecou entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComptecouRepository extends JpaRepository<Comptecou, Long> {
}
