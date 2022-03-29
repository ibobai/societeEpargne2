package simplon.pros.web.rest;

import simplon.pros.BriefGroupeJHipsterApp;
import simplon.pros.domain.Conseiller;
import simplon.pros.repository.ConseillerRepository;
import simplon.pros.service.ConseillerService;
import simplon.pros.service.dto.ConseillerDTO;
import simplon.pros.service.mapper.ConseillerMapper;

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
 * Integration tests for the {@link ConseillerResource} REST controller.
 */
@SpringBootTest(classes = BriefGroupeJHipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ConseillerResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_NOMUTILISATEUR = "AAAAAAAAAA";
    private static final String UPDATED_NOMUTILISATEUR = "BBBBBBBBBB";

    private static final String DEFAULT_MOTDEPASSE = "AAAAAAAAAA";
    private static final String UPDATED_MOTDEPASSE = "BBBBBBBBBB";

    @Autowired
    private ConseillerRepository conseillerRepository;

    @Autowired
    private ConseillerMapper conseillerMapper;

    @Autowired
    private ConseillerService conseillerService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restConseillerMockMvc;

    private Conseiller conseiller;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Conseiller createEntity(EntityManager em) {
        Conseiller conseiller = new Conseiller()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .tel(DEFAULT_TEL)
            .email(DEFAULT_EMAIL)
            .nomutilisateur(DEFAULT_NOMUTILISATEUR)
            .motdepasse(DEFAULT_MOTDEPASSE);
        return conseiller;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Conseiller createUpdatedEntity(EntityManager em) {
        Conseiller conseiller = new Conseiller()
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .tel(UPDATED_TEL)
            .email(UPDATED_EMAIL)
            .nomutilisateur(UPDATED_NOMUTILISATEUR)
            .motdepasse(UPDATED_MOTDEPASSE);
        return conseiller;
    }

    @BeforeEach
    public void initTest() {
        conseiller = createEntity(em);
    }

    @Test
    @Transactional
    public void createConseiller() throws Exception {
        int databaseSizeBeforeCreate = conseillerRepository.findAll().size();
        // Create the Conseiller
        ConseillerDTO conseillerDTO = conseillerMapper.toDto(conseiller);
        restConseillerMockMvc.perform(post("/api/conseillers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(conseillerDTO)))
            .andExpect(status().isCreated());

        // Validate the Conseiller in the database
        List<Conseiller> conseillerList = conseillerRepository.findAll();
        assertThat(conseillerList).hasSize(databaseSizeBeforeCreate + 1);
        Conseiller testConseiller = conseillerList.get(conseillerList.size() - 1);
        assertThat(testConseiller.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testConseiller.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testConseiller.getTel()).isEqualTo(DEFAULT_TEL);
        assertThat(testConseiller.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testConseiller.getNomutilisateur()).isEqualTo(DEFAULT_NOMUTILISATEUR);
        assertThat(testConseiller.getMotdepasse()).isEqualTo(DEFAULT_MOTDEPASSE);
    }

    @Test
    @Transactional
    public void createConseillerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = conseillerRepository.findAll().size();

        // Create the Conseiller with an existing ID
        conseiller.setId(1L);
        ConseillerDTO conseillerDTO = conseillerMapper.toDto(conseiller);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConseillerMockMvc.perform(post("/api/conseillers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(conseillerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Conseiller in the database
        List<Conseiller> conseillerList = conseillerRepository.findAll();
        assertThat(conseillerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = conseillerRepository.findAll().size();
        // set the field null
        conseiller.setNom(null);

        // Create the Conseiller, which fails.
        ConseillerDTO conseillerDTO = conseillerMapper.toDto(conseiller);


        restConseillerMockMvc.perform(post("/api/conseillers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(conseillerDTO)))
            .andExpect(status().isBadRequest());

        List<Conseiller> conseillerList = conseillerRepository.findAll();
        assertThat(conseillerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = conseillerRepository.findAll().size();
        // set the field null
        conseiller.setPrenom(null);

        // Create the Conseiller, which fails.
        ConseillerDTO conseillerDTO = conseillerMapper.toDto(conseiller);


        restConseillerMockMvc.perform(post("/api/conseillers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(conseillerDTO)))
            .andExpect(status().isBadRequest());

        List<Conseiller> conseillerList = conseillerRepository.findAll();
        assertThat(conseillerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTelIsRequired() throws Exception {
        int databaseSizeBeforeTest = conseillerRepository.findAll().size();
        // set the field null
        conseiller.setTel(null);

        // Create the Conseiller, which fails.
        ConseillerDTO conseillerDTO = conseillerMapper.toDto(conseiller);


        restConseillerMockMvc.perform(post("/api/conseillers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(conseillerDTO)))
            .andExpect(status().isBadRequest());

        List<Conseiller> conseillerList = conseillerRepository.findAll();
        assertThat(conseillerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = conseillerRepository.findAll().size();
        // set the field null
        conseiller.setEmail(null);

        // Create the Conseiller, which fails.
        ConseillerDTO conseillerDTO = conseillerMapper.toDto(conseiller);


        restConseillerMockMvc.perform(post("/api/conseillers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(conseillerDTO)))
            .andExpect(status().isBadRequest());

        List<Conseiller> conseillerList = conseillerRepository.findAll();
        assertThat(conseillerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomutilisateurIsRequired() throws Exception {
        int databaseSizeBeforeTest = conseillerRepository.findAll().size();
        // set the field null
        conseiller.setNomutilisateur(null);

        // Create the Conseiller, which fails.
        ConseillerDTO conseillerDTO = conseillerMapper.toDto(conseiller);


        restConseillerMockMvc.perform(post("/api/conseillers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(conseillerDTO)))
            .andExpect(status().isBadRequest());

        List<Conseiller> conseillerList = conseillerRepository.findAll();
        assertThat(conseillerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMotdepasseIsRequired() throws Exception {
        int databaseSizeBeforeTest = conseillerRepository.findAll().size();
        // set the field null
        conseiller.setMotdepasse(null);

        // Create the Conseiller, which fails.
        ConseillerDTO conseillerDTO = conseillerMapper.toDto(conseiller);


        restConseillerMockMvc.perform(post("/api/conseillers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(conseillerDTO)))
            .andExpect(status().isBadRequest());

        List<Conseiller> conseillerList = conseillerRepository.findAll();
        assertThat(conseillerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllConseillers() throws Exception {
        // Initialize the database
        conseillerRepository.saveAndFlush(conseiller);

        // Get all the conseillerList
        restConseillerMockMvc.perform(get("/api/conseillers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(conseiller.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].nomutilisateur").value(hasItem(DEFAULT_NOMUTILISATEUR)))
            .andExpect(jsonPath("$.[*].motdepasse").value(hasItem(DEFAULT_MOTDEPASSE)));
    }
    
    @Test
    @Transactional
    public void getConseiller() throws Exception {
        // Initialize the database
        conseillerRepository.saveAndFlush(conseiller);

        // Get the conseiller
        restConseillerMockMvc.perform(get("/api/conseillers/{id}", conseiller.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(conseiller.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.tel").value(DEFAULT_TEL))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.nomutilisateur").value(DEFAULT_NOMUTILISATEUR))
            .andExpect(jsonPath("$.motdepasse").value(DEFAULT_MOTDEPASSE));
    }
    @Test
    @Transactional
    public void getNonExistingConseiller() throws Exception {
        // Get the conseiller
        restConseillerMockMvc.perform(get("/api/conseillers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConseiller() throws Exception {
        // Initialize the database
        conseillerRepository.saveAndFlush(conseiller);

        int databaseSizeBeforeUpdate = conseillerRepository.findAll().size();

        // Update the conseiller
        Conseiller updatedConseiller = conseillerRepository.findById(conseiller.getId()).get();
        // Disconnect from session so that the updates on updatedConseiller are not directly saved in db
        em.detach(updatedConseiller);
        updatedConseiller
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .tel(UPDATED_TEL)
            .email(UPDATED_EMAIL)
            .nomutilisateur(UPDATED_NOMUTILISATEUR)
            .motdepasse(UPDATED_MOTDEPASSE);
        ConseillerDTO conseillerDTO = conseillerMapper.toDto(updatedConseiller);

        restConseillerMockMvc.perform(put("/api/conseillers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(conseillerDTO)))
            .andExpect(status().isOk());

        // Validate the Conseiller in the database
        List<Conseiller> conseillerList = conseillerRepository.findAll();
        assertThat(conseillerList).hasSize(databaseSizeBeforeUpdate);
        Conseiller testConseiller = conseillerList.get(conseillerList.size() - 1);
        assertThat(testConseiller.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testConseiller.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testConseiller.getTel()).isEqualTo(UPDATED_TEL);
        assertThat(testConseiller.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testConseiller.getNomutilisateur()).isEqualTo(UPDATED_NOMUTILISATEUR);
        assertThat(testConseiller.getMotdepasse()).isEqualTo(UPDATED_MOTDEPASSE);
    }

    @Test
    @Transactional
    public void updateNonExistingConseiller() throws Exception {
        int databaseSizeBeforeUpdate = conseillerRepository.findAll().size();

        // Create the Conseiller
        ConseillerDTO conseillerDTO = conseillerMapper.toDto(conseiller);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restConseillerMockMvc.perform(put("/api/conseillers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(conseillerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Conseiller in the database
        List<Conseiller> conseillerList = conseillerRepository.findAll();
        assertThat(conseillerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteConseiller() throws Exception {
        // Initialize the database
        conseillerRepository.saveAndFlush(conseiller);

        int databaseSizeBeforeDelete = conseillerRepository.findAll().size();

        // Delete the conseiller
        restConseillerMockMvc.perform(delete("/api/conseillers/{id}", conseiller.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Conseiller> conseillerList = conseillerRepository.findAll();
        assertThat(conseillerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
