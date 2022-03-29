package simplon.pros.service;

import simplon.pros.domain.Compte;
import simplon.pros.repository.CompteRepository;
import simplon.pros.service.dto.CompteDTO;
import simplon.pros.service.mapper.CompteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Compte}.
 */
@Service
@Transactional
public class CompteService {

    private final Logger log = LoggerFactory.getLogger(CompteService.class);

    private final CompteRepository compteRepository;

    private final CompteMapper compteMapper;

    public CompteService(CompteRepository compteRepository, CompteMapper compteMapper) {
        this.compteRepository = compteRepository;
        this.compteMapper = compteMapper;
    }

    /**
     * Save a compte.
     *
     * @param compteDTO the entity to save.
     * @return the persisted entity.
     */
    public CompteDTO save(CompteDTO compteDTO) {
        log.debug("Request to save Compte : {}", compteDTO);
        Compte compte = compteMapper.toEntity(compteDTO);
        compte = compteRepository.save(compte);
        return compteMapper.toDto(compte);
    }

    /**
     * Get all the comptes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CompteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Comptes");
        return compteRepository.findAll(pageable)
            .map(compteMapper::toDto);
    }


    /**
     * Get one compte by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CompteDTO> findOne(Long id) {
        log.debug("Request to get Compte : {}", id);
        return compteRepository.findById(id)
            .map(compteMapper::toDto);
    }

    /**
     * Delete the compte by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Compte : {}", id);
        compteRepository.deleteById(id);
    }
}
