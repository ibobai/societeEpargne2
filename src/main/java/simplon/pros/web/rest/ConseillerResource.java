package simplon.pros.web.rest;

import simplon.pros.service.ConseillerService;
import simplon.pros.web.rest.errors.BadRequestAlertException;
import simplon.pros.service.dto.ConseillerDTO;

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
 * REST controller for managing {@link simplon.pros.domain.Conseiller}.
 */
@RestController
@RequestMapping("/api")
public class ConseillerResource {

    private final Logger log = LoggerFactory.getLogger(ConseillerResource.class);

    private static final String ENTITY_NAME = "conseiller";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ConseillerService conseillerService;

    public ConseillerResource(ConseillerService conseillerService) {
        this.conseillerService = conseillerService;
    }

    /**
     * {@code POST  /conseillers} : Create a new conseiller.
     *
     * @param conseillerDTO the conseillerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new conseillerDTO, or with status {@code 400 (Bad Request)} if the conseiller has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/conseillers")
    public ResponseEntity<ConseillerDTO> createConseiller(@Valid @RequestBody ConseillerDTO conseillerDTO) throws URISyntaxException {
        log.debug("REST request to save Conseiller : {}", conseillerDTO);
        if (conseillerDTO.getId() != null) {
            throw new BadRequestAlertException("A new conseiller cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConseillerDTO result = conseillerService.save(conseillerDTO);
        return ResponseEntity.created(new URI("/api/conseillers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /conseillers} : Updates an existing conseiller.
     *
     * @param conseillerDTO the conseillerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated conseillerDTO,
     * or with status {@code 400 (Bad Request)} if the conseillerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the conseillerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/conseillers")
    public ResponseEntity<ConseillerDTO> updateConseiller(@Valid @RequestBody ConseillerDTO conseillerDTO) throws URISyntaxException {
        log.debug("REST request to update Conseiller : {}", conseillerDTO);
        if (conseillerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ConseillerDTO result = conseillerService.save(conseillerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, conseillerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /conseillers} : get all the conseillers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of conseillers in body.
     */
    @GetMapping("/conseillers")
    public ResponseEntity<List<ConseillerDTO>> getAllConseillers(Pageable pageable) {
        log.debug("REST request to get a page of Conseillers");
        Page<ConseillerDTO> page = conseillerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /conseillers/:id} : get the "id" conseiller.
     *
     * @param id the id of the conseillerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the conseillerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/conseillers/{id}")
    public ResponseEntity<ConseillerDTO> getConseiller(@PathVariable Long id) {
        log.debug("REST request to get Conseiller : {}", id);
        Optional<ConseillerDTO> conseillerDTO = conseillerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(conseillerDTO);
    }

    /**
     * {@code DELETE  /conseillers/:id} : delete the "id" conseiller.
     *
     * @param id the id of the conseillerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/conseillers/{id}")
    public ResponseEntity<Void> deleteConseiller(@PathVariable Long id) {
        log.debug("REST request to delete Conseiller : {}", id);
        conseillerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
