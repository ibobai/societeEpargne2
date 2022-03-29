package simplon.pros.service;

import simplon.pros.domain.Conseiller;
import simplon.pros.repository.ConseillerRepository;
import simplon.pros.service.dto.ConseillerDTO;
import simplon.pros.service.mapper.ConseillerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Conseiller}.
 */
@Service
@Transactional
public class ConseillerService {

    private final Logger log = LoggerFactory.getLogger(ConseillerService.class);

    private final ConseillerRepository conseillerRepository;

    private final ConseillerMapper conseillerMapper;

    public ConseillerService(ConseillerRepository conseillerRepository, ConseillerMapper conseillerMapper) {
        this.conseillerRepository = conseillerRepository;
        this.conseillerMapper = conseillerMapper;
    }

    /**
     * Save a conseiller.
     *
     * @param conseillerDTO the entity to save.
     * @return the persisted entity.
     */
    public ConseillerDTO save(ConseillerDTO conseillerDTO) {
        log.debug("Request to save Conseiller : {}", conseillerDTO);
        Conseiller conseiller = conseillerMapper.toEntity(conseillerDTO);
        conseiller = conseillerRepository.save(conseiller);
        return conseillerMapper.toDto(conseiller);
    }

    /**
     * Get all the conseillers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ConseillerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Conseillers");
        return conseillerRepository.findAll(pageable)
            .map(conseillerMapper::toDto);
    }


    /**
     * Get one conseiller by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ConseillerDTO> findOne(Long id) {
        log.debug("Request to get Conseiller : {}", id);
        return conseillerRepository.findById(id)
            .map(conseillerMapper::toDto);
    }

    /**
     * Delete the conseiller by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Conseiller : {}", id);
        conseillerRepository.deleteById(id);
    }
}
