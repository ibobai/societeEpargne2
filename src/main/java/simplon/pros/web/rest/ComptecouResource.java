package simplon.pros.web.rest;

import simplon.pros.service.ComptecouService;
import simplon.pros.web.rest.errors.BadRequestAlertException;
import simplon.pros.service.dto.ComptecouDTO;

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
 * REST controller for managing {@link simplon.pros.domain.Comptecou}.
 */
@RestController
@RequestMapping("/api")
public class ComptecouResource {

    private final Logger log = LoggerFactory.getLogger(ComptecouResource.class);

    private static final String ENTITY_NAME = "comptecou";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ComptecouService comptecouService;

    public ComptecouResource(ComptecouService comptecouService) {
        this.comptecouService = comptecouService;
    }

    /**
     * {@code POST  /comptecous} : Create a new comptecou.
     *
     * @param comptecouDTO the comptecouDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new comptecouDTO, or with status {@code 400 (Bad Request)} if the comptecou has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/comptecous")
    public ResponseEntity<ComptecouDTO> createComptecou(@Valid @RequestBody ComptecouDTO comptecouDTO) throws URISyntaxException {
        log.debug("REST request to save Comptecou : {}", comptecouDTO);
        if (comptecouDTO.getId() != null) {
            throw new BadRequestAlertException("A new comptecou cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ComptecouDTO result = comptecouService.save(comptecouDTO);
        return ResponseEntity.created(new URI("/api/comptecous/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /comptecous} : Updates an existing comptecou.
     *
     * @param comptecouDTO the comptecouDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated comptecouDTO,
     * or with status {@code 400 (Bad Request)} if the comptecouDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the comptecouDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/comptecous")
    public ResponseEntity<ComptecouDTO> updateComptecou(@Valid @RequestBody ComptecouDTO comptecouDTO) throws URISyntaxException {
        log.debug("REST request to update Comptecou : {}", comptecouDTO);
        if (comptecouDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ComptecouDTO result = comptecouService.save(comptecouDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, comptecouDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /comptecous} : get all the comptecous.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of comptecous in body.
     */
    @GetMapping("/comptecous")
    public ResponseEntity<List<ComptecouDTO>> getAllComptecous(Pageable pageable) {
        log.debug("REST request to get a page of Comptecous");
        Page<ComptecouDTO> page = comptecouService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /comptecous/:id} : get the "id" comptecou.
     *
     * @param id the id of the comptecouDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the comptecouDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/comptecous/{id}")
    public ResponseEntity<ComptecouDTO> getComptecou(@PathVariable Long id) {
        log.debug("REST request to get Comptecou : {}", id);
        Optional<ComptecouDTO> comptecouDTO = comptecouService.findOne(id);
        return ResponseUtil.wrapOrNotFound(comptecouDTO);
    }

    /**
     * {@code DELETE  /comptecous/:id} : delete the "id" comptecou.
     *
     * @param id the id of the comptecouDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/comptecous/{id}")
    public ResponseEntity<Void> deleteComptecou(@PathVariable Long id) {
        log.debug("REST request to delete Comptecou : {}", id);
        comptecouService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
