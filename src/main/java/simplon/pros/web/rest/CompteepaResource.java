package simplon.pros.web.rest;

import simplon.pros.service.CompteepaService;
import simplon.pros.web.rest.errors.BadRequestAlertException;
import simplon.pros.service.dto.CompteepaDTO;

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
 * REST controller for managing {@link simplon.pros.domain.Compteepa}.
 */
@RestController
@RequestMapping("/api")
public class CompteepaResource {

    private final Logger log = LoggerFactory.getLogger(CompteepaResource.class);

    private static final String ENTITY_NAME = "compteepa";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CompteepaService compteepaService;

    public CompteepaResource(CompteepaService compteepaService) {
        this.compteepaService = compteepaService;
    }

    /**
     * {@code POST  /compteepas} : Create a new compteepa.
     *
     * @param compteepaDTO the compteepaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new compteepaDTO, or with status {@code 400 (Bad Request)} if the compteepa has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/compteepas")
    public ResponseEntity<CompteepaDTO> createCompteepa(@Valid @RequestBody CompteepaDTO compteepaDTO) throws URISyntaxException {
        log.debug("REST request to save Compteepa : {}", compteepaDTO);
        if (compteepaDTO.getId() != null) {
            throw new BadRequestAlertException("A new compteepa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CompteepaDTO result = compteepaService.save(compteepaDTO);
        return ResponseEntity.created(new URI("/api/compteepas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /compteepas} : Updates an existing compteepa.
     *
     * @param compteepaDTO the compteepaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated compteepaDTO,
     * or with status {@code 400 (Bad Request)} if the compteepaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the compteepaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/compteepas")
    public ResponseEntity<CompteepaDTO> updateCompteepa(@Valid @RequestBody CompteepaDTO compteepaDTO) throws URISyntaxException {
        log.debug("REST request to update Compteepa : {}", compteepaDTO);
        if (compteepaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CompteepaDTO result = compteepaService.save(compteepaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, compteepaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /compteepas} : get all the compteepas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of compteepas in body.
     */
    @GetMapping("/compteepas")
    public ResponseEntity<List<CompteepaDTO>> getAllCompteepas(Pageable pageable) {
        log.debug("REST request to get a page of Compteepas");
        Page<CompteepaDTO> page = compteepaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /compteepas/:id} : get the "id" compteepa.
     *
     * @param id the id of the compteepaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the compteepaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/compteepas/{id}")
    public ResponseEntity<CompteepaDTO> getCompteepa(@PathVariable Long id) {
        log.debug("REST request to get Compteepa : {}", id);
        Optional<CompteepaDTO> compteepaDTO = compteepaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(compteepaDTO);
    }

    /**
     * {@code DELETE  /compteepas/:id} : delete the "id" compteepa.
     *
     * @param id the id of the compteepaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/compteepas/{id}")
    public ResponseEntity<Void> deleteCompteepa(@PathVariable Long id) {
        log.debug("REST request to delete Compteepa : {}", id);
        compteepaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
