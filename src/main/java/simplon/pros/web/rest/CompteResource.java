package simplon.pros.web.rest;

import simplon.pros.service.CompteService;
import simplon.pros.web.rest.errors.BadRequestAlertException;
import simplon.pros.service.dto.CompteDTO;

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
 * REST controller for managing {@link simplon.pros.domain.Compte}.
 */
@RestController
@RequestMapping("/api")
public class CompteResource {

    private final Logger log = LoggerFactory.getLogger(CompteResource.class);

    private static final String ENTITY_NAME = "compte";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CompteService compteService;

    public CompteResource(CompteService compteService) {
        this.compteService = compteService;
    }

    /**
     * {@code POST  /comptes} : Create a new compte.
     *
     * @param compteDTO the compteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new compteDTO, or with status {@code 400 (Bad Request)} if the compte has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/comptes")
    public ResponseEntity<CompteDTO> createCompte(@Valid @RequestBody CompteDTO compteDTO) throws URISyntaxException {
        log.debug("REST request to save Compte : {}", compteDTO);
        if (compteDTO.getId() != null) {
            throw new BadRequestAlertException("A new compte cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CompteDTO result = compteService.save(compteDTO);
        return ResponseEntity.created(new URI("/api/comptes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /comptes} : Updates an existing compte.
     *
     * @param compteDTO the compteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated compteDTO,
     * or with status {@code 400 (Bad Request)} if the compteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the compteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/comptes")
    public ResponseEntity<CompteDTO> updateCompte(@Valid @RequestBody CompteDTO compteDTO) throws URISyntaxException {
        log.debug("REST request to update Compte : {}", compteDTO);
        if (compteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CompteDTO result = compteService.save(compteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, compteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /comptes} : get all the comptes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of comptes in body.
     */
    @GetMapping("/comptes")
    public ResponseEntity<List<CompteDTO>> getAllComptes(Pageable pageable) {
        log.debug("REST request to get a page of Comptes");
        Page<CompteDTO> page = compteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /comptes/:id} : get the "id" compte.
     *
     * @param id the id of the compteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the compteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/comptes/{id}")
    public ResponseEntity<CompteDTO> getCompte(@PathVariable Long id) {
        log.debug("REST request to get Compte : {}", id);
        Optional<CompteDTO> compteDTO = compteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(compteDTO);
    }

    /**
     * {@code DELETE  /comptes/:id} : delete the "id" compte.
     *
     * @param id the id of the compteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/comptes/{id}")
    public ResponseEntity<Void> deleteCompte(@PathVariable Long id) {
        log.debug("REST request to delete Compte : {}", id);
        compteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
