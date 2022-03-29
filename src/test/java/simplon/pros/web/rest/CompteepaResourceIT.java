package simplon.pros.web.rest;

import simplon.pros.BriefGroupeJHipsterApp;
import simplon.pros.domain.Compteepa;
import simplon.pros.repository.CompteepaRepository;
import simplon.pros.service.CompteepaService;
import simplon.pros.service.dto.CompteepaDTO;
import simplon.pros.service.mapper.CompteepaMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link CompteepaResource} REST controller.
 */
@SpringBootTest(classes = BriefGroupeJHipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class CompteepaResourceIT {

    private static final Integer DEFAULT_TAUXINTERET = 1;
    private static final Integer UPDATED_TAUXINTERET = 2;

    private static final Integer DEFAULT_PLAFOND = 1;
    private static final Integer UPDATED_PLAFOND = 2;

    @Autowired
    private CompteepaRepository compteepaRepository;

    @Autowired
    private CompteepaMapper compteepaMapper;

    @Autowired
    private CompteepaService compteepaService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCompteepaMockMvc;

    private Compteepa compteepa;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Compteepa createEntity(EntityManager em) {
        Compteepa compteepa = new Compteepa()
            .tauxinteret(DEFAULT_TAUXINTERET)
            .plafond(DEFAULT_PLAFOND);
        return compteepa;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Compteepa createUpdatedEntity(EntityManager em) {
        Compteepa compteepa = new Compteepa()
            .tauxinteret(UPDATED_TAUXINTERET)
            .plafond(UPDATED_PLAFOND);
        return compteepa;
    }

    @BeforeEach
    public void initTest() {
        compteepa = createEntity(em);
    }

    @Test
    @Transactional
    public void createCompteepa() throws Exception {
        int databaseSizeBeforeCreate = compteepaRepository.findAll().size();
        // Create the Compteepa
        CompteepaDTO compteepaDTO = compteepaMapper.toDto(compteepa);
        restCompteepaMockMvc.perform(post("/api/compteepas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(compteepaDTO)))
            .andExpect(status().isCreated());

        // Validate the Compteepa in the database
        List<Compteepa> compteepaList = compteepaRepository.findAll();
        assertThat(compteepaList).hasSize(databaseSizeBeforeCreate + 1);
        Compteepa testCompteepa = compteepaList.get(compteepaList.size() - 1);
        assertThat(testCompteepa.getTauxinteret()).isEqualTo(DEFAULT_TAUXINTERET);
        assertThat(testCompteepa.getPlafond()).isEqualTo(DEFAULT_PLAFOND);
    }

    @Test
    @Transactional
    public void createCompteepaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = compteepaRepository.findAll().size();

        // Create the Compteepa with an existing ID
        compteepa.setId(1L);
        CompteepaDTO compteepaDTO = compteepaMapper.toDto(compteepa);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompteepaMockMvc.perform(post("/api/compteepas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(compteepaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Compteepa in the database
        List<Compteepa> compteepaList = compteepaRepository.findAll();
        assertThat(compteepaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTauxinteretIsRequired() throws Exception {
        int databaseSizeBeforeTest = compteepaRepository.findAll().size();
        // set the field null
        compteepa.setTauxinteret(null);

        // Create the Compteepa, which fails.
        CompteepaDTO compteepaDTO = compteepaMapper.toDto(compteepa);


        restCompteepaMockMvc.perform(post("/api/compteepas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(compteepaDTO)))
            .andExpect(status().isBadRequest());

        List<Compteepa> compteepaList = compteepaRepository.findAll();
        assertThat(compteepaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPlafondIsRequired() throws Exception {
        int databaseSizeBeforeTest = compteepaRepository.findAll().size();
        // set the field null
        compteepa.setPlafond(null);

        // Create the Compteepa, which fails.
        CompteepaDTO compteepaDTO = compteepaMapper.toDto(compteepa);


        restCompteepaMockMvc.perform(post("/api/compteepas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(compteepaDTO)))
            .andExpect(status().isBadRequest());

        List<Compteepa> compteepaList = compteepaRepository.findAll();
        assertThat(compteepaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCompteepas() throws Exception {
        // Initialize the database
        compteepaRepository.saveAndFlush(compteepa);

        // Get all the compteepaList
        restCompteepaMockMvc.perform(get("/api/compteepas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(compteepa.getId().intValue())))
            .andExpect(jsonPath("$.[*].tauxinteret").value(hasItem(DEFAULT_TAUXINTERET)))
            .andExpect(jsonPath("$.[*].plafond").value(hasItem(DEFAULT_PLAFOND)));
    }
    
    @Test
    @Transactional
    public void getCompteepa() throws Exception {
        // Initialize the database
        compteepaRepository.saveAndFlush(compteepa);

        // Get the compteepa
        restCompteepaMockMvc.perform(get("/api/compteepas/{id}", compteepa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(compteepa.getId().intValue()))
            .andExpect(jsonPath("$.tauxinteret").value(DEFAULT_TAUXINTERET))
            .andExpect(jsonPath("$.plafond").value(DEFAULT_PLAFOND));
    }
    @Test
    @Transactional
    public void getNonExistingCompteepa() throws Exception {
        // Get the compteepa
        restCompteepaMockMvc.perform(get("/api/compteepas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCompteepa() throws Exception {
        // Initialize the database
        compteepaRepository.saveAndFlush(compteepa);

        int databaseSizeBeforeUpdate = compteepaRepository.findAll().size();

        // Update the compteepa
        Compteepa updatedCompteepa = compteepaRepository.findById(compteepa.getId()).get();
        // Disconnect from session so that the updates on updatedCompteepa are not directly saved in db
        em.detach(updatedCompteepa);
        updatedCompteepa
            .tauxinteret(UPDATED_TAUXINTERET)
            .plafond(UPDATED_PLAFOND);
        CompteepaDTO compteepaDTO = compteepaMapper.toDto(updatedCompteepa);

        restCompteepaMockMvc.perform(put("/api/compteepas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(compteepaDTO)))
            .andExpect(status().isOk());

        // Validate the Compteepa in the database
        List<Compteepa> compteepaList = compteepaRepository.findAll();
        assertThat(compteepaList).hasSize(databaseSizeBeforeUpdate);
        Compteepa testCompteepa = compteepaList.get(compteepaList.size() - 1);
        assertThat(testCompteepa.getTauxinteret()).isEqualTo(UPDATED_TAUXINTERET);
        assertThat(testCompteepa.getPlafond()).isEqualTo(UPDATED_PLAFOND);
    }

    @Test
    @Transactional
    public void updateNonExistingCompteepa() throws Exception {
        int databaseSizeBeforeUpdate = compteepaRepository.findAll().size();

        // Create the Compteepa
        CompteepaDTO compteepaDTO = compteepaMapper.toDto(compteepa);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCompteepaMockMvc.perform(put("/api/compteepas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(compteepaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Compteepa in the database
        List<Compteepa> compteepaList = compteepaRepository.findAll();
        assertThat(compteepaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCompteepa() throws Exception {
        // Initialize the database
        compteepaRepository.saveAndFlush(compteepa);

        int databaseSizeBeforeDelete = compteepaRepository.findAll().size();

        // Delete the compteepa
        restCompteepaMockMvc.perform(delete("/api/compteepas/{id}", compteepa.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Compteepa> compteepaList = compteepaRepository.findAll();
        assertThat(compteepaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
