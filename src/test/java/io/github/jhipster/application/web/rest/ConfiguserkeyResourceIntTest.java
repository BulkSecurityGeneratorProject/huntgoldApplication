package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.HuntgoldApplicationApp;

import io.github.jhipster.application.domain.Configuserkey;
import io.github.jhipster.application.repository.ConfiguserkeyRepository;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ConfiguserkeyResource REST controller.
 *
 * @see ConfiguserkeyResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HuntgoldApplicationApp.class)
public class ConfiguserkeyResourceIntTest {

    private static final String DEFAULT_PLATFORM = "AAAAAAAAAA";
    private static final String UPDATED_PLATFORM = "BBBBBBBBBB";

    private static final String DEFAULT_APIKEY = "AAAAAAAAAA";
    private static final String UPDATED_APIKEY = "BBBBBBBBBB";

    private static final String DEFAULT_SECRETKEY = "AAAAAAAAAA";
    private static final String UPDATED_SECRETKEY = "BBBBBBBBBB";

    private static final String DEFAULT_CREATEBY = "AAAAAAAAAA";
    private static final String UPDATED_CREATEBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATEDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATEDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_UPDATEBY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATEBY = "BBBBBBBBBB";

    private static final Instant DEFAULT_UPDATEDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATEDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final Integer DEFAULT_DELFLAG = 1;
    private static final Integer UPDATED_DELFLAG = 2;

    @Autowired
    private ConfiguserkeyRepository configuserkeyRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restConfiguserkeyMockMvc;

    private Configuserkey configuserkey;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConfiguserkeyResource configuserkeyResource = new ConfiguserkeyResource(configuserkeyRepository);
        this.restConfiguserkeyMockMvc = MockMvcBuilders.standaloneSetup(configuserkeyResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Configuserkey createEntity(EntityManager em) {
        Configuserkey configuserkey = new Configuserkey()
            .platform(DEFAULT_PLATFORM)
            .apikey(DEFAULT_APIKEY)
            .secretkey(DEFAULT_SECRETKEY)
            .createby(DEFAULT_CREATEBY)
            .createdate(DEFAULT_CREATEDATE)
            .updateby(DEFAULT_UPDATEBY)
            .updatedate(DEFAULT_UPDATEDATE)
            .remarks(DEFAULT_REMARKS)
            .delflag(DEFAULT_DELFLAG);
        return configuserkey;
    }

    @Before
    public void initTest() {
        configuserkey = createEntity(em);
    }

    @Test
    @Transactional
    public void createConfiguserkey() throws Exception {
        int databaseSizeBeforeCreate = configuserkeyRepository.findAll().size();

        // Create the Configuserkey
        restConfiguserkeyMockMvc.perform(post("/api/configuserkeys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configuserkey)))
            .andExpect(status().isCreated());

        // Validate the Configuserkey in the database
        List<Configuserkey> configuserkeyList = configuserkeyRepository.findAll();
        assertThat(configuserkeyList).hasSize(databaseSizeBeforeCreate + 1);
        Configuserkey testConfiguserkey = configuserkeyList.get(configuserkeyList.size() - 1);
        assertThat(testConfiguserkey.getPlatform()).isEqualTo(DEFAULT_PLATFORM);
        assertThat(testConfiguserkey.getApikey()).isEqualTo(DEFAULT_APIKEY);
        assertThat(testConfiguserkey.getSecretkey()).isEqualTo(DEFAULT_SECRETKEY);
        assertThat(testConfiguserkey.getCreateby()).isEqualTo(DEFAULT_CREATEBY);
        assertThat(testConfiguserkey.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testConfiguserkey.getUpdateby()).isEqualTo(DEFAULT_UPDATEBY);
        assertThat(testConfiguserkey.getUpdatedate()).isEqualTo(DEFAULT_UPDATEDATE);
        assertThat(testConfiguserkey.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testConfiguserkey.getDelflag()).isEqualTo(DEFAULT_DELFLAG);
    }

    @Test
    @Transactional
    public void createConfiguserkeyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = configuserkeyRepository.findAll().size();

        // Create the Configuserkey with an existing ID
        configuserkey.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConfiguserkeyMockMvc.perform(post("/api/configuserkeys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configuserkey)))
            .andExpect(status().isBadRequest());

        // Validate the Configuserkey in the database
        List<Configuserkey> configuserkeyList = configuserkeyRepository.findAll();
        assertThat(configuserkeyList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllConfiguserkeys() throws Exception {
        // Initialize the database
        configuserkeyRepository.saveAndFlush(configuserkey);

        // Get all the configuserkeyList
        restConfiguserkeyMockMvc.perform(get("/api/configuserkeys?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(configuserkey.getId().intValue())))
            .andExpect(jsonPath("$.[*].platform").value(hasItem(DEFAULT_PLATFORM.toString())))
            .andExpect(jsonPath("$.[*].apikey").value(hasItem(DEFAULT_APIKEY.toString())))
            .andExpect(jsonPath("$.[*].secretkey").value(hasItem(DEFAULT_SECRETKEY.toString())))
            .andExpect(jsonPath("$.[*].createby").value(hasItem(DEFAULT_CREATEBY.toString())))
            .andExpect(jsonPath("$.[*].createdate").value(hasItem(DEFAULT_CREATEDATE.toString())))
            .andExpect(jsonPath("$.[*].updateby").value(hasItem(DEFAULT_UPDATEBY.toString())))
            .andExpect(jsonPath("$.[*].updatedate").value(hasItem(DEFAULT_UPDATEDATE.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
            .andExpect(jsonPath("$.[*].delflag").value(hasItem(DEFAULT_DELFLAG)));
    }
    

    @Test
    @Transactional
    public void getConfiguserkey() throws Exception {
        // Initialize the database
        configuserkeyRepository.saveAndFlush(configuserkey);

        // Get the configuserkey
        restConfiguserkeyMockMvc.perform(get("/api/configuserkeys/{id}", configuserkey.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(configuserkey.getId().intValue()))
            .andExpect(jsonPath("$.platform").value(DEFAULT_PLATFORM.toString()))
            .andExpect(jsonPath("$.apikey").value(DEFAULT_APIKEY.toString()))
            .andExpect(jsonPath("$.secretkey").value(DEFAULT_SECRETKEY.toString()))
            .andExpect(jsonPath("$.createby").value(DEFAULT_CREATEBY.toString()))
            .andExpect(jsonPath("$.createdate").value(DEFAULT_CREATEDATE.toString()))
            .andExpect(jsonPath("$.updateby").value(DEFAULT_UPDATEBY.toString()))
            .andExpect(jsonPath("$.updatedate").value(DEFAULT_UPDATEDATE.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()))
            .andExpect(jsonPath("$.delflag").value(DEFAULT_DELFLAG));
    }
    @Test
    @Transactional
    public void getNonExistingConfiguserkey() throws Exception {
        // Get the configuserkey
        restConfiguserkeyMockMvc.perform(get("/api/configuserkeys/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConfiguserkey() throws Exception {
        // Initialize the database
        configuserkeyRepository.saveAndFlush(configuserkey);

        int databaseSizeBeforeUpdate = configuserkeyRepository.findAll().size();

        // Update the configuserkey
        Configuserkey updatedConfiguserkey = configuserkeyRepository.findById(configuserkey.getId()).get();
        // Disconnect from session so that the updates on updatedConfiguserkey are not directly saved in db
        em.detach(updatedConfiguserkey);
        updatedConfiguserkey
            .platform(UPDATED_PLATFORM)
            .apikey(UPDATED_APIKEY)
            .secretkey(UPDATED_SECRETKEY)
            .createby(UPDATED_CREATEBY)
            .createdate(UPDATED_CREATEDATE)
            .updateby(UPDATED_UPDATEBY)
            .updatedate(UPDATED_UPDATEDATE)
            .remarks(UPDATED_REMARKS)
            .delflag(UPDATED_DELFLAG);

        restConfiguserkeyMockMvc.perform(put("/api/configuserkeys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedConfiguserkey)))
            .andExpect(status().isOk());

        // Validate the Configuserkey in the database
        List<Configuserkey> configuserkeyList = configuserkeyRepository.findAll();
        assertThat(configuserkeyList).hasSize(databaseSizeBeforeUpdate);
        Configuserkey testConfiguserkey = configuserkeyList.get(configuserkeyList.size() - 1);
        assertThat(testConfiguserkey.getPlatform()).isEqualTo(UPDATED_PLATFORM);
        assertThat(testConfiguserkey.getApikey()).isEqualTo(UPDATED_APIKEY);
        assertThat(testConfiguserkey.getSecretkey()).isEqualTo(UPDATED_SECRETKEY);
        assertThat(testConfiguserkey.getCreateby()).isEqualTo(UPDATED_CREATEBY);
        assertThat(testConfiguserkey.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testConfiguserkey.getUpdateby()).isEqualTo(UPDATED_UPDATEBY);
        assertThat(testConfiguserkey.getUpdatedate()).isEqualTo(UPDATED_UPDATEDATE);
        assertThat(testConfiguserkey.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testConfiguserkey.getDelflag()).isEqualTo(UPDATED_DELFLAG);
    }

    @Test
    @Transactional
    public void updateNonExistingConfiguserkey() throws Exception {
        int databaseSizeBeforeUpdate = configuserkeyRepository.findAll().size();

        // Create the Configuserkey

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restConfiguserkeyMockMvc.perform(put("/api/configuserkeys")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configuserkey)))
            .andExpect(status().isBadRequest());

        // Validate the Configuserkey in the database
        List<Configuserkey> configuserkeyList = configuserkeyRepository.findAll();
        assertThat(configuserkeyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteConfiguserkey() throws Exception {
        // Initialize the database
        configuserkeyRepository.saveAndFlush(configuserkey);

        int databaseSizeBeforeDelete = configuserkeyRepository.findAll().size();

        // Get the configuserkey
        restConfiguserkeyMockMvc.perform(delete("/api/configuserkeys/{id}", configuserkey.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Configuserkey> configuserkeyList = configuserkeyRepository.findAll();
        assertThat(configuserkeyList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Configuserkey.class);
        Configuserkey configuserkey1 = new Configuserkey();
        configuserkey1.setId(1L);
        Configuserkey configuserkey2 = new Configuserkey();
        configuserkey2.setId(configuserkey1.getId());
        assertThat(configuserkey1).isEqualTo(configuserkey2);
        configuserkey2.setId(2L);
        assertThat(configuserkey1).isNotEqualTo(configuserkey2);
        configuserkey1.setId(null);
        assertThat(configuserkey1).isNotEqualTo(configuserkey2);
    }
}
