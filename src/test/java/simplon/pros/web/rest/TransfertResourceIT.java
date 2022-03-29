package simplon.pros.web.rest;

import simplon.pros.BriefGroupeJHipsterApp;
import simplon.pros.domain.Transfert;
import simplon.pros.repository.TransfertRepository;
import simplon.pros.service.TransfertService;
import simplon.pros.service.dto.TransfertDTO;
import simplon.pros.service.mapper.TransfertMapper;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TransfertResource} REST controller.
 */
@SpringBootTest(classes = BriefGroupeJHipsterApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TransfertResourceIT {

    private static final Integer DEFAULT_TRANSFERTID = 1;
    private static final Integer UPDATED_TRANSFERTID = 2;

    private static final LocalDate DEFAULT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_MONTANT = 1D;
    private static final Double UPDATED_MONTANT = 2D;

    private static final String DEFAULT_TYPEOPERATION = "AAAAAAAAAA";
    private static final String UPDATED_TYPEOPERATION = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMCOMPTE = 1;
    private static final Integer UPDATED_NUMCOMPTE = 2;

    private static final Integer DEFAULT_CONSEILLERID = 1;
    private static final Integer UPDATED_CONSEILLERID = 2;

    @Autowired
    private TransfertRepository transfertRepository;

    @Autowired
    private TransfertMapper transfertMapper;

    @Autowired
    private TransfertService transfertService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTransfertMockMvc;

    private Transfert transfert;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Transfert createEntity(EntityManager em) {
        Transfert transfert = new Transfert()
            .transfertid(DEFAULT_TRANSFERTID)
            .date(DEFAULT_DATE)
            .montant(DEFAULT_MONTANT)
            .typeoperation(DEFAULT_TYPEOPERATION)
            .numcompte(DEFAULT_NUMCOMPTE)
            .conseillerid(DEFAULT_CONSEILLERID);
        return transfert;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Transfert createUpdatedEntity(EntityManager em) {
        Transfert transfert = new Transfert()
            .transfertid(UPDATED_TRANSFERTID)
            .date(UPDATED_DATE)
            .montant(UPDATED_MONTANT)
            .typeoperation(UPDATED_TYPEOPERATION)
            .numcompte(UPDATED_NUMCOMPTE)
            .conseillerid(UPDATED_CONSEILLERID);
        return transfert;
    }

    @BeforeEach
    public void initTest() {
        transfert = createEntity(em);
    }

    @Test
    @Transactional
    public void createTransfert() throws Exception {
        int databaseSizeBeforeCreate = transfertRepository.findAll().size();
        // Create the Transfert
        TransfertDTO transfertDTO = transfertMapper.toDto(transfert);
        restTransfertMockMvc.perform(post("/api/transferts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transfertDTO)))
            .andExpect(status().isCreated());

        // Validate the Transfert in the database
        List<Transfert> transfertList = transfertRepository.findAll();
        assertThat(transfertList).hasSize(databaseSizeBeforeCreate + 1);
        Transfert testTransfert = transfertList.get(transfertList.size() - 1);
        assertThat(testTransfert.getTransfertid()).isEqualTo(DEFAULT_TRANSFERTID);
        assertThat(testTransfert.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testTransfert.getMontant()).isEqualTo(DEFAULT_MONTANT);
        assertThat(testTransfert.getTypeoperation()).isEqualTo(DEFAULT_TYPEOPERATION);
        assertThat(testTransfert.getNumcompte()).isEqualTo(DEFAULT_NUMCOMPTE);
        assertThat(testTransfert.getConseillerid()).isEqualTo(DEFAULT_CONSEILLERID);
    }

    @Test
    @Transactional
    public void createTransfertWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = transfertRepository.findAll().size();

        // Create the Transfert with an existing ID
        transfert.setId(1L);
        TransfertDTO transfertDTO = transfertMapper.toDto(transfert);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransfertMockMvc.perform(post("/api/transferts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transfertDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Transfert in the database
        List<Transfert> transfertList = transfertRepository.findAll();
        assertThat(transfertList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTransfertidIsRequired() throws Exception {
        int databaseSizeBeforeTest = transfertRepository.findAll().size();
        // set the field null
        transfert.setTransfertid(null);

        // Create the Transfert, which fails.
        TransfertDTO transfertDTO = transfertMapper.toDto(transfert);


        restTransfertMockMvc.perform(post("/api/transferts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transfertDTO)))
            .andExpect(status().isBadRequest());

        List<Transfert> transfertList = transfertRepository.findAll();
        assertThat(transfertList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMontantIsRequired() throws Exception {
        int databaseSizeBeforeTest = transfertRepository.findAll().size();
        // set the field null
        transfert.setMontant(null);

        // Create the Transfert, which fails.
        TransfertDTO transfertDTO = transfertMapper.toDto(transfert);


        restTransfertMockMvc.perform(post("/api/transferts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transfertDTO)))
            .andExpect(status().isBadRequest());

        List<Transfert> transfertList = transfertRepository.findAll();
        assertThat(transfertList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeoperationIsRequired() throws Exception {
        int databaseSizeBeforeTest = transfertRepository.findAll().size();
        // set the field null
        transfert.setTypeoperation(null);

        // Create the Transfert, which fails.
        TransfertDTO transfertDTO = transfertMapper.toDto(transfert);


        restTransfertMockMvc.perform(post("/api/transferts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transfertDTO)))
            .andExpect(status().isBadRequest());

        List<Transfert> transfertList = transfertRepository.findAll();
        assertThat(transfertList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumcompteIsRequired() throws Exception {
        int databaseSizeBeforeTest = transfertRepository.findAll().size();
        // set the field null
        transfert.setNumcompte(null);

        // Create the Transfert, which fails.
        TransfertDTO transfertDTO = transfertMapper.toDto(transfert);


        restTransfertMockMvc.perform(post("/api/transferts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transfertDTO)))
            .andExpect(status().isBadRequest());

        List<Transfert> transfertList = transfertRepository.findAll();
        assertThat(transfertList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkConseilleridIsRequired() throws Exception {
        int databaseSizeBeforeTest = transfertRepository.findAll().size();
        // set the field null
        transfert.setConseillerid(null);

        // Create the Transfert, which fails.
        TransfertDTO transfertDTO = transfertMapper.toDto(transfert);


        restTransfertMockMvc.perform(post("/api/transferts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transfertDTO)))
            .andExpect(status().isBadRequest());

        List<Transfert> transfertList = transfertRepository.findAll();
        assertThat(transfertList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTransferts() throws Exception {
        // Initialize the database
        transfertRepository.saveAndFlush(transfert);

        // Get all the transfertList
        restTransfertMockMvc.perform(get("/api/transferts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transfert.getId().intValue())))
            .andExpect(jsonPath("$.[*].transfertid").value(hasItem(DEFAULT_TRANSFERTID)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].montant").value(hasItem(DEFAULT_MONTANT.doubleValue())))
            .andExpect(jsonPath("$.[*].typeoperation").value(hasItem(DEFAULT_TYPEOPERATION)))
            .andExpect(jsonPath("$.[*].numcompte").value(hasItem(DEFAULT_NUMCOMPTE)))
            .andExpect(jsonPath("$.[*].conseillerid").value(hasItem(DEFAULT_CONSEILLERID)));
    }
    
    @Test
    @Transactional
    public void getTransfert() throws Exception {
        // Initialize the database
        transfertRepository.saveAndFlush(transfert);

        // Get the transfert
        restTransfertMockMvc.perform(get("/api/transferts/{id}", transfert.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(transfert.getId().intValue()))
            .andExpect(jsonPath("$.transfertid").value(DEFAULT_TRANSFERTID))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.montant").value(DEFAULT_MONTANT.doubleValue()))
            .andExpect(jsonPath("$.typeoperation").value(DEFAULT_TYPEOPERATION))
            .andExpect(jsonPath("$.numcompte").value(DEFAULT_NUMCOMPTE))
            .andExpect(jsonPath("$.conseillerid").value(DEFAULT_CONSEILLERID));
    }
    @Test
    @Transactional
    public void getNonExistingTransfert() throws Exception {
        // Get the transfert
        restTransfertMockMvc.perform(get("/api/transferts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransfert() throws Exception {
        // Initialize the database
        transfertRepository.saveAndFlush(transfert);

        int databaseSizeBeforeUpdate = transfertRepository.findAll().size();

        // Update the transfert
        Transfert updatedTransfert = transfertRepository.findById(transfert.getId()).get();
        // Disconnect from session so that the updates on updatedTransfert are not directly saved in db
        em.detach(updatedTransfert);
        updatedTransfert
            .transfertid(UPDATED_TRANSFERTID)
            .date(UPDATED_DATE)
            .montant(UPDATED_MONTANT)
            .typeoperation(UPDATED_TYPEOPERATION)
            .numcompte(UPDATED_NUMCOMPTE)
            .conseillerid(UPDATED_CONSEILLERID);
        TransfertDTO transfertDTO = transfertMapper.toDto(updatedTransfert);

        restTransfertMockMvc.perform(put("/api/transferts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transfertDTO)))
            .andExpect(status().isOk());

        // Validate the Transfert in the database
        List<Transfert> transfertList = transfertRepository.findAll();
        assertThat(transfertList).hasSize(databaseSizeBeforeUpdate);
        Transfert testTransfert = transfertList.get(transfertList.size() - 1);
        assertThat(testTransfert.getTransfertid()).isEqualTo(UPDATED_TRANSFERTID);
        assertThat(testTransfert.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testTransfert.getMontant()).isEqualTo(UPDATED_MONTANT);
        assertThat(testTransfert.getTypeoperation()).isEqualTo(UPDATED_TYPEOPERATION);
        assertThat(testTransfert.getNumcompte()).isEqualTo(UPDATED_NUMCOMPTE);
        assertThat(testTransfert.getConseillerid()).isEqualTo(UPDATED_CONSEILLERID);
    }

    @Test
    @Transactional
    public void updateNonExistingTransfert() throws Exception {
        int databaseSizeBeforeUpdate = transfertRepository.findAll().size();

        // Create the Transfert
        TransfertDTO transfertDTO = transfertMapper.toDto(transfert);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransfertMockMvc.perform(put("/api/transferts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(transfertDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Transfert in the database
        List<Transfert> transfertList = transfertRepository.findAll();
        assertThat(transfertList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTransfert() throws Exception {
        // Initialize the database
        transfertRepository.saveAndFlush(transfert);

        int databaseSizeBeforeDelete = transfertRepository.findAll().size();

        // Delete the transfert
        restTransfertMockMvc.perform(delete("/api/transferts/{id}", transfert.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Transfert> transfertList = transfertRepository.findAll();
        assertThat(transfertList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
