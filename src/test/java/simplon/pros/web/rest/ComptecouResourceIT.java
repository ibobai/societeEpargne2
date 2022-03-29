package simplon.pros.web.rest;

import simplon.pros.BriefGroupeJHipsterApp;
import simplon.pros.domain.Comptecou;
import simplon.pros.repository.ComptecouRepository;
import simplon.pros.service.ComptecouService;
import simplon.pros.service.dto.ComptecouDTO;
import simplon.pros.service.mapper.ComptecouMapper;

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
 * Integration tests for the {@link ComptecouResource} REST controller.
 */
@SpringBootTest(classes = BriefGroupeJHipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ComptecouResourceIT {

    private static final Integer DEFAULT_FRAISTRANS = 1;
    private static final Integer UPDATED_FRAISTRANS = 2;

    private static final Double DEFAULT_SOLDEMIN = 1D;
    private static final Double UPDATED_SOLDEMIN = 2D;

    @Autowired
    private ComptecouRepository comptecouRepository;

    @Autowired
    private ComptecouMapper comptecouMapper;

    @Autowired
    private ComptecouService comptecouService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restComptecouMockMvc;

    private Comptecou comptecou;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Comptecou createEntity(EntityManager em) {
        Comptecou comptecou = new Comptecou()
            .fraistrans(DEFAULT_FRAISTRANS)
            .soldemin(DEFAULT_SOLDEMIN);
        return comptecou;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Comptecou createUpdatedEntity(EntityManager em) {
        Comptecou comptecou = new Comptecou()
            .fraistrans(UPDATED_FRAISTRANS)
            .soldemin(UPDATED_SOLDEMIN);
        return comptecou;
    }

    @BeforeEach
    public void initTest() {
        comptecou = createEntity(em);
    }

    @Test
    @Transactional
    public void createComptecou() throws Exception {
        int databaseSizeBeforeCreate = comptecouRepository.findAll().size();
        // Create the Comptecou
        ComptecouDTO comptecouDTO = comptecouMapper.toDto(comptecou);
        restComptecouMockMvc.perform(post("/api/comptecous")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(comptecouDTO)))
            .andExpect(status().isCreated());

        // Validate the Comptecou in the database
        List<Comptecou> comptecouList = comptecouRepository.findAll();
        assertThat(comptecouList).hasSize(databaseSizeBeforeCreate + 1);
        Comptecou testComptecou = comptecouList.get(comptecouList.size() - 1);
        assertThat(testComptecou.getFraistrans()).isEqualTo(DEFAULT_FRAISTRANS);
        assertThat(testComptecou.getSoldemin()).isEqualTo(DEFAULT_SOLDEMIN);
    }

    @Test
    @Transactional
    public void createComptecouWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = comptecouRepository.findAll().size();

        // Create the Comptecou with an existing ID
        comptecou.setId(1L);
        ComptecouDTO comptecouDTO = comptecouMapper.toDto(comptecou);

        // An entity with an existing ID cannot be created, so this API call must fail
        restComptecouMockMvc.perform(post("/api/comptecous")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(comptecouDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Comptecou in the database
        List<Comptecou> comptecouList = comptecouRepository.findAll();
        assertThat(comptecouList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFraistransIsRequired() throws Exception {
        int databaseSizeBeforeTest = comptecouRepository.findAll().size();
        // set the field null
        comptecou.setFraistrans(null);

        // Create the Comptecou, which fails.
        ComptecouDTO comptecouDTO = comptecouMapper.toDto(comptecou);


        restComptecouMockMvc.perform(post("/api/comptecous")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(comptecouDTO)))
            .andExpect(status().isBadRequest());

        List<Comptecou> comptecouList = comptecouRepository.findAll();
        assertThat(comptecouList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSoldeminIsRequired() throws Exception {
        int databaseSizeBeforeTest = comptecouRepository.findAll().size();
        // set the field null
        comptecou.setSoldemin(null);

        // Create the Comptecou, which fails.
        ComptecouDTO comptecouDTO = comptecouMapper.toDto(comptecou);


        restComptecouMockMvc.perform(post("/api/comptecous")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(comptecouDTO)))
            .andExpect(status().isBadRequest());

        List<Comptecou> comptecouList = comptecouRepository.findAll();
        assertThat(comptecouList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllComptecous() throws Exception {
        // Initialize the database
        comptecouRepository.saveAndFlush(comptecou);

        // Get all the comptecouList
        restComptecouMockMvc.perform(get("/api/comptecous?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(comptecou.getId().intValue())))
            .andExpect(jsonPath("$.[*].fraistrans").value(hasItem(DEFAULT_FRAISTRANS)))
            .andExpect(jsonPath("$.[*].soldemin").value(hasItem(DEFAULT_SOLDEMIN.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getComptecou() throws Exception {
        // Initialize the database
        comptecouRepository.saveAndFlush(comptecou);

        // Get the comptecou
        restComptecouMockMvc.perform(get("/api/comptecous/{id}", comptecou.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(comptecou.getId().intValue()))
            .andExpect(jsonPath("$.fraistrans").value(DEFAULT_FRAISTRANS))
            .andExpect(jsonPath("$.soldemin").value(DEFAULT_SOLDEMIN.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingComptecou() throws Exception {
        // Get the comptecou
        restComptecouMockMvc.perform(get("/api/comptecous/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateComptecou() throws Exception {
        // Initialize the database
        comptecouRepository.saveAndFlush(comptecou);

        int databaseSizeBeforeUpdate = comptecouRepository.findAll().size();

        // Update the comptecou
        Comptecou updatedComptecou = comptecouRepository.findById(comptecou.getId()).get();
        // Disconnect from session so that the updates on updatedComptecou are not directly saved in db
        em.detach(updatedComptecou);
        updatedComptecou
            .fraistrans(UPDATED_FRAISTRANS)
            .soldemin(UPDATED_SOLDEMIN);
        ComptecouDTO comptecouDTO = comptecouMapper.toDto(updatedComptecou);

        restComptecouMockMvc.perform(put("/api/comptecous")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(comptecouDTO)))
            .andExpect(status().isOk());

        // Validate the Comptecou in the database
        List<Comptecou> comptecouList = comptecouRepository.findAll();
        assertThat(comptecouList).hasSize(databaseSizeBeforeUpdate);
        Comptecou testComptecou = comptecouList.get(comptecouList.size() - 1);
        assertThat(testComptecou.getFraistrans()).isEqualTo(UPDATED_FRAISTRANS);
        assertThat(testComptecou.getSoldemin()).isEqualTo(UPDATED_SOLDEMIN);
    }

    @Test
    @Transactional
    public void updateNonExistingComptecou() throws Exception {
        int databaseSizeBeforeUpdate = comptecouRepository.findAll().size();

        // Create the Comptecou
        ComptecouDTO comptecouDTO = comptecouMapper.toDto(comptecou);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restComptecouMockMvc.perform(put("/api/comptecous")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(comptecouDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Comptecou in the database
        List<Comptecou> comptecouList = comptecouRepository.findAll();
        assertThat(comptecouList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteComptecou() throws Exception {
        // Initialize the database
        comptecouRepository.saveAndFlush(comptecou);

        int databaseSizeBeforeDelete = comptecouRepository.findAll().size();

        // Delete the comptecou
        restComptecouMockMvc.perform(delete("/api/comptecous/{id}", comptecou.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Comptecou> comptecouList = comptecouRepository.findAll();
        assertThat(comptecouList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
