package simplon.pros.repository;

import simplon.pros.domain.Compteepa;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Compteepa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompteepaRepository extends JpaRepository<Compteepa, Long> {
}
