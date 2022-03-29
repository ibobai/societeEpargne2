package simplon.pros.service;

import simplon.pros.domain.Transfert;
import simplon.pros.repository.TransfertRepository;
import simplon.pros.service.dto.TransfertDTO;
import simplon.pros.service.mapper.TransfertMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Transfert}.
 */
@Service
@Transactional
public class TransfertService {

    private final Logger log = LoggerFactory.getLogger(TransfertService.class);

    private final TransfertRepository transfertRepository;

    private final TransfertMapper transfertMapper;

    public TransfertService(TransfertRepository transfertRepository, TransfertMapper transfertMapper) {
        this.transfertRepository = transfertRepository;
        this.transfertMapper = transfertMapper;
    }

    /**
     * Save a transfert.
     *
     * @param transfertDTO the entity to save.
     * @return the persisted entity.
     */
    public TransfertDTO save(TransfertDTO transfertDTO) {
        log.debug("Request to save Transfert : {}", transfertDTO);
        Transfert transfert = transfertMapper.toEntity(transfertDTO);
        transfert = transfertRepository.save(transfert);
        return transfertMapper.toDto(transfert);
    }

    /**
     * Get all the transferts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TransfertDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Transferts");
        return transfertRepository.findAll(pageable)
            .map(transfertMapper::toDto);
    }


    /**
     * Get one transfert by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TransfertDTO> findOne(Long id) {
        log.debug("Request to get Transfert : {}", id);
        return transfertRepository.findById(id)
            .map(transfertMapper::toDto);
    }

    /**
     * Delete the transfert by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Transfert : {}", id);
        transfertRepository.deleteById(id);
    }
}
