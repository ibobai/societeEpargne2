package simplon.pros.service;

import simplon.pros.domain.Comptecou;
import simplon.pros.repository.ComptecouRepository;
import simplon.pros.service.dto.ComptecouDTO;
import simplon.pros.service.mapper.ComptecouMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Comptecou}.
 */
@Service
@Transactional
public class ComptecouService {

    private final Logger log = LoggerFactory.getLogger(ComptecouService.class);

    private final ComptecouRepository comptecouRepository;

    private final ComptecouMapper comptecouMapper;

    public ComptecouService(ComptecouRepository comptecouRepository, ComptecouMapper comptecouMapper) {
        this.comptecouRepository = comptecouRepository;
        this.comptecouMapper = comptecouMapper;
    }

    /**
     * Save a comptecou.
     *
     * @param comptecouDTO the entity to save.
     * @return the persisted entity.
     */
    public ComptecouDTO save(ComptecouDTO comptecouDTO) {
        log.debug("Request to save Comptecou : {}", comptecouDTO);
        Comptecou comptecou = comptecouMapper.toEntity(comptecouDTO);
        comptecou = comptecouRepository.save(comptecou);
        return comptecouMapper.toDto(comptecou);
    }

    /**
     * Get all the comptecous.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ComptecouDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Comptecous");
        return comptecouRepository.findAll(pageable)
            .map(comptecouMapper::toDto);
    }


    /**
     * Get one comptecou by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ComptecouDTO> findOne(Long id) {
        log.debug("Request to get Comptecou : {}", id);
        return comptecouRepository.findById(id)
            .map(comptecouMapper::toDto);
    }

    /**
     * Delete the comptecou by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Comptecou : {}", id);
        comptecouRepository.deleteById(id);
    }
}
