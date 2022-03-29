package simplon.pros.web.rest;

import simplon.pros.service.TransfertService;
import simplon.pros.web.rest.errors.BadRequestAlertException;
import simplon.pros.service.dto.TransfertDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link simplon.pros.domain.Transfert}.
 */
@RestController
@RequestMapping("/api")
public class TransfertResource {

    private final Logger log = LoggerFactory.getLogger(TransfertResource.class);

    private static final String ENTITY_NAME = "transfert";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TransfertService transfertService;

    public TransfertResource(TransfertService transfertService) {
        this.transfertService = transfertService;
    }

    /**
     * {@code POST  /transferts} : Create a new transfert.
     *
     * @param transfertDTO the transfertDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new transfertDTO, or with status {@code 400 (Bad Request)} if the transfert has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/transferts")
    public ResponseEntity<TransfertDTO> createTransfert(@Valid @RequestBody TransfertDTO transfertDTO) throws URISyntaxException {
        log.debug("REST request to save Transfert : {}", transfertDTO);
        if (transfertDTO.getId() != null) {
            throw new BadRequestAlertException("A new transfert cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TransfertDTO result = transfertService.save(transfertDTO);
        return ResponseEntity.created(new URI("/api/transferts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /transferts} : Updates an existing transfert.
     *
     * @param transfertDTO the transfertDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transfertDTO,
     * or with status {@code 400 (Bad Request)} if the transfertDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the transfertDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/transferts")
    public ResponseEntity<TransfertDTO> updateTransfert(@Valid @RequestBody TransfertDTO transfertDTO) throws URISyntaxException {
        log.debug("REST request to update Transfert : {}", transfertDTO);
        if (transfertDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TransfertDTO result = transfertService.save(transfertDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, transfertDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /transferts} : get all the transferts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of transferts in body.
     */
    @GetMapping("/transferts")
    public ResponseEntity<List<TransfertDTO>> getAllTransferts(Pageable pageable) {
        log.debug("REST request to get a page of Transferts");
        Page<TransfertDTO> page = transfertService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /transferts/:id} : get the "id" transfert.
     *
     * @param id the id of the transfertDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the transfertDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/transferts/{id}")
    public ResponseEntity<TransfertDTO> getTransfert(@PathVariable Long id) {
        log.debug("REST request to get Transfert : {}", id);
        Optional<TransfertDTO> transfertDTO = transfertService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transfertDTO);
    }

    /**
     * {@code DELETE  /transferts/:id} : delete the "id" transfert.
     *
     * @param id the id of the transfertDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/transferts/{id}")
    public ResponseEntity<Void> deleteTransfert(@PathVariable Long id) {
        log.debug("REST request to delete Transfert : {}", id);
        transfertService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
