package simplon.pros.service;

import simplon.pros.domain.Compteepa;
import simplon.pros.repository.CompteepaRepository;
import simplon.pros.service.dto.CompteepaDTO;
import simplon.pros.service.mapper.CompteepaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Compteepa}.
 */
@Service
@Transactional
public class CompteepaService {

    private final Logger log = LoggerFactory.getLogger(CompteepaService.class);

    private final CompteepaRepository compteepaRepository;

    private final CompteepaMapper compteepaMapper;

    public CompteepaService(CompteepaRepository compteepaRepository, CompteepaMapper compteepaMapper) {
        this.compteepaRepository = compteepaRepository;
        this.compteepaMapper = compteepaMapper;
    }

    /**
     * Save a compteepa.
     *
     * @param compteepaDTO the entity to save.
     * @return the persisted entity.
     */
    public CompteepaDTO save(CompteepaDTO compteepaDTO) {
        log.debug("Request to save Compteepa : {}", compteepaDTO);
        Compteepa compteepa = compteepaMapper.toEntity(compteepaDTO);
        compteepa = compteepaRepository.save(compteepa);
        return compteepaMapper.toDto(compteepa);
    }

    /**
     * Get all the compteepas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CompteepaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Compteepas");
        return compteepaRepository.findAll(pageable)
            .map(compteepaMapper::toDto);
    }


    /**
     * Get one compteepa by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CompteepaDTO> findOne(Long id) {
        log.debug("Request to get Compteepa : {}", id);
        return compteepaRepository.findById(id)
            .map(compteepaMapper::toDto);
    }

    /**
     * Delete the compteepa by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Compteepa : {}", id);
        compteepaRepository.deleteById(id);
    }
}
