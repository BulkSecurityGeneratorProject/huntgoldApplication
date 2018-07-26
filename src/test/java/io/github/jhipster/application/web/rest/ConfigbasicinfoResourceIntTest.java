package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.HuntgoldApplicationApp;

import io.github.jhipster.application.domain.Configbasicinfo;
import io.github.jhipster.application.repository.ConfigbasicinfoRepository;
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
 * Test class for the ConfigbasicinfoResource REST controller.
 *
 * @see ConfigbasicinfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HuntgoldApplicationApp.class)
public class ConfigbasicinfoResourceIntTest {

    private static final String DEFAULT_CONFIGCODE = "AAAAAAAAAA";
    private static final String UPDATED_CONFIGCODE = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIGNAME = "AAAAAAAAAA";
    private static final String UPDATED_CONFIGNAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIGVALUE = "AAAAAAAAAA";
    private static final String UPDATED_CONFIGVALUE = "BBBBBBBBBB";

    private static final String DEFAULT_PARENTID = "AAAAAAAAAA";
    private static final String UPDATED_PARENTID = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIGTYPENAME = "AAAAAAAAAA";
    private static final String UPDATED_CONFIGTYPENAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONFIGTYPE = "AAAAAAAAAA";
    private static final String UPDATED_CONFIGTYPE = "BBBBBBBBBB";

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
    private ConfigbasicinfoRepository configbasicinfoRepository;


    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restConfigbasicinfoMockMvc;

    private Configbasicinfo configbasicinfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConfigbasicinfoResource configbasicinfoResource = new ConfigbasicinfoResource(configbasicinfoRepository);
        this.restConfigbasicinfoMockMvc = MockMvcBuilders.standaloneSetup(configbasicinfoResource)
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
    public static Configbasicinfo createEntity(EntityManager em) {
        Configbasicinfo configbasicinfo = new Configbasicinfo()
            .configcode(DEFAULT_CONFIGCODE)
            .configname(DEFAULT_CONFIGNAME)
            .configvalue(DEFAULT_CONFIGVALUE)
            .parentid(DEFAULT_PARENTID)
            .configtypename(DEFAULT_CONFIGTYPENAME)
            .configtype(DEFAULT_CONFIGTYPE)
            .createby(DEFAULT_CREATEBY)
            .createdate(DEFAULT_CREATEDATE)
            .updateby(DEFAULT_UPDATEBY)
            .updatedate(DEFAULT_UPDATEDATE)
            .remarks(DEFAULT_REMARKS)
            .delflag(DEFAULT_DELFLAG);
        return configbasicinfo;
    }

    @Before
    public void initTest() {
        configbasicinfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createConfigbasicinfo() throws Exception {
        int databaseSizeBeforeCreate = configbasicinfoRepository.findAll().size();

        // Create the Configbasicinfo
        restConfigbasicinfoMockMvc.perform(post("/api/configbasicinfos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configbasicinfo)))
            .andExpect(status().isCreated());

        // Validate the Configbasicinfo in the database
        List<Configbasicinfo> configbasicinfoList = configbasicinfoRepository.findAll();
        assertThat(configbasicinfoList).hasSize(databaseSizeBeforeCreate + 1);
        Configbasicinfo testConfigbasicinfo = configbasicinfoList.get(configbasicinfoList.size() - 1);
        assertThat(testConfigbasicinfo.getConfigcode()).isEqualTo(DEFAULT_CONFIGCODE);
        assertThat(testConfigbasicinfo.getConfigname()).isEqualTo(DEFAULT_CONFIGNAME);
        assertThat(testConfigbasicinfo.getConfigvalue()).isEqualTo(DEFAULT_CONFIGVALUE);
        assertThat(testConfigbasicinfo.getParentid()).isEqualTo(DEFAULT_PARENTID);
        assertThat(testConfigbasicinfo.getConfigtypename()).isEqualTo(DEFAULT_CONFIGTYPENAME);
        assertThat(testConfigbasicinfo.getConfigtype()).isEqualTo(DEFAULT_CONFIGTYPE);
        assertThat(testConfigbasicinfo.getCreateby()).isEqualTo(DEFAULT_CREATEBY);
        assertThat(testConfigbasicinfo.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testConfigbasicinfo.getUpdateby()).isEqualTo(DEFAULT_UPDATEBY);
        assertThat(testConfigbasicinfo.getUpdatedate()).isEqualTo(DEFAULT_UPDATEDATE);
        assertThat(testConfigbasicinfo.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testConfigbasicinfo.getDelflag()).isEqualTo(DEFAULT_DELFLAG);
    }

    @Test
    @Transactional
    public void createConfigbasicinfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = configbasicinfoRepository.findAll().size();

        // Create the Configbasicinfo with an existing ID
        configbasicinfo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConfigbasicinfoMockMvc.perform(post("/api/configbasicinfos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configbasicinfo)))
            .andExpect(status().isBadRequest());

        // Validate the Configbasicinfo in the database
        List<Configbasicinfo> configbasicinfoList = configbasicinfoRepository.findAll();
        assertThat(configbasicinfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllConfigbasicinfos() throws Exception {
        // Initialize the database
        configbasicinfoRepository.saveAndFlush(configbasicinfo);

        // Get all the configbasicinfoList
        restConfigbasicinfoMockMvc.perform(get("/api/configbasicinfos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(configbasicinfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].configcode").value(hasItem(DEFAULT_CONFIGCODE.toString())))
            .andExpect(jsonPath("$.[*].configname").value(hasItem(DEFAULT_CONFIGNAME.toString())))
            .andExpect(jsonPath("$.[*].configvalue").value(hasItem(DEFAULT_CONFIGVALUE.toString())))
            .andExpect(jsonPath("$.[*].parentid").value(hasItem(DEFAULT_PARENTID.toString())))
            .andExpect(jsonPath("$.[*].configtypename").value(hasItem(DEFAULT_CONFIGTYPENAME.toString())))
            .andExpect(jsonPath("$.[*].configtype").value(hasItem(DEFAULT_CONFIGTYPE.toString())))
            .andExpect(jsonPath("$.[*].createby").value(hasItem(DEFAULT_CREATEBY.toString())))
            .andExpect(jsonPath("$.[*].createdate").value(hasItem(DEFAULT_CREATEDATE.toString())))
            .andExpect(jsonPath("$.[*].updateby").value(hasItem(DEFAULT_UPDATEBY.toString())))
            .andExpect(jsonPath("$.[*].updatedate").value(hasItem(DEFAULT_UPDATEDATE.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
            .andExpect(jsonPath("$.[*].delflag").value(hasItem(DEFAULT_DELFLAG)));
    }
    

    @Test
    @Transactional
    public void getConfigbasicinfo() throws Exception {
        // Initialize the database
        configbasicinfoRepository.saveAndFlush(configbasicinfo);

        // Get the configbasicinfo
        restConfigbasicinfoMockMvc.perform(get("/api/configbasicinfos/{id}", configbasicinfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(configbasicinfo.getId().intValue()))
            .andExpect(jsonPath("$.configcode").value(DEFAULT_CONFIGCODE.toString()))
            .andExpect(jsonPath("$.configname").value(DEFAULT_CONFIGNAME.toString()))
            .andExpect(jsonPath("$.configvalue").value(DEFAULT_CONFIGVALUE.toString()))
            .andExpect(jsonPath("$.parentid").value(DEFAULT_PARENTID.toString()))
            .andExpect(jsonPath("$.configtypename").value(DEFAULT_CONFIGTYPENAME.toString()))
            .andExpect(jsonPath("$.configtype").value(DEFAULT_CONFIGTYPE.toString()))
            .andExpect(jsonPath("$.createby").value(DEFAULT_CREATEBY.toString()))
            .andExpect(jsonPath("$.createdate").value(DEFAULT_CREATEDATE.toString()))
            .andExpect(jsonPath("$.updateby").value(DEFAULT_UPDATEBY.toString()))
            .andExpect(jsonPath("$.updatedate").value(DEFAULT_UPDATEDATE.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()))
            .andExpect(jsonPath("$.delflag").value(DEFAULT_DELFLAG));
    }
    @Test
    @Transactional
    public void getNonExistingConfigbasicinfo() throws Exception {
        // Get the configbasicinfo
        restConfigbasicinfoMockMvc.perform(get("/api/configbasicinfos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConfigbasicinfo() throws Exception {
        // Initialize the database
        configbasicinfoRepository.saveAndFlush(configbasicinfo);

        int databaseSizeBeforeUpdate = configbasicinfoRepository.findAll().size();

        // Update the configbasicinfo
        Configbasicinfo updatedConfigbasicinfo = configbasicinfoRepository.findById(configbasicinfo.getId()).get();
        // Disconnect from session so that the updates on updatedConfigbasicinfo are not directly saved in db
        em.detach(updatedConfigbasicinfo);
        updatedConfigbasicinfo
            .configcode(UPDATED_CONFIGCODE)
            .configname(UPDATED_CONFIGNAME)
            .configvalue(UPDATED_CONFIGVALUE)
            .parentid(UPDATED_PARENTID)
            .configtypename(UPDATED_CONFIGTYPENAME)
            .configtype(UPDATED_CONFIGTYPE)
            .createby(UPDATED_CREATEBY)
            .createdate(UPDATED_CREATEDATE)
            .updateby(UPDATED_UPDATEBY)
            .updatedate(UPDATED_UPDATEDATE)
            .remarks(UPDATED_REMARKS)
            .delflag(UPDATED_DELFLAG);

        restConfigbasicinfoMockMvc.perform(put("/api/configbasicinfos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedConfigbasicinfo)))
            .andExpect(status().isOk());

        // Validate the Configbasicinfo in the database
        List<Configbasicinfo> configbasicinfoList = configbasicinfoRepository.findAll();
        assertThat(configbasicinfoList).hasSize(databaseSizeBeforeUpdate);
        Configbasicinfo testConfigbasicinfo = configbasicinfoList.get(configbasicinfoList.size() - 1);
        assertThat(testConfigbasicinfo.getConfigcode()).isEqualTo(UPDATED_CONFIGCODE);
        assertThat(testConfigbasicinfo.getConfigname()).isEqualTo(UPDATED_CONFIGNAME);
        assertThat(testConfigbasicinfo.getConfigvalue()).isEqualTo(UPDATED_CONFIGVALUE);
        assertThat(testConfigbasicinfo.getParentid()).isEqualTo(UPDATED_PARENTID);
        assertThat(testConfigbasicinfo.getConfigtypename()).isEqualTo(UPDATED_CONFIGTYPENAME);
        assertThat(testConfigbasicinfo.getConfigtype()).isEqualTo(UPDATED_CONFIGTYPE);
        assertThat(testConfigbasicinfo.getCreateby()).isEqualTo(UPDATED_CREATEBY);
        assertThat(testConfigbasicinfo.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testConfigbasicinfo.getUpdateby()).isEqualTo(UPDATED_UPDATEBY);
        assertThat(testConfigbasicinfo.getUpdatedate()).isEqualTo(UPDATED_UPDATEDATE);
        assertThat(testConfigbasicinfo.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testConfigbasicinfo.getDelflag()).isEqualTo(UPDATED_DELFLAG);
    }

    @Test
    @Transactional
    public void updateNonExistingConfigbasicinfo() throws Exception {
        int databaseSizeBeforeUpdate = configbasicinfoRepository.findAll().size();

        // Create the Configbasicinfo

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restConfigbasicinfoMockMvc.perform(put("/api/configbasicinfos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(configbasicinfo)))
            .andExpect(status().isBadRequest());

        // Validate the Configbasicinfo in the database
        List<Configbasicinfo> configbasicinfoList = configbasicinfoRepository.findAll();
        assertThat(configbasicinfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteConfigbasicinfo() throws Exception {
        // Initialize the database
        configbasicinfoRepository.saveAndFlush(configbasicinfo);

        int databaseSizeBeforeDelete = configbasicinfoRepository.findAll().size();

        // Get the configbasicinfo
        restConfigbasicinfoMockMvc.perform(delete("/api/configbasicinfos/{id}", configbasicinfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Configbasicinfo> configbasicinfoList = configbasicinfoRepository.findAll();
        assertThat(configbasicinfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Configbasicinfo.class);
        Configbasicinfo configbasicinfo1 = new Configbasicinfo();
        configbasicinfo1.setId(1L);
        Configbasicinfo configbasicinfo2 = new Configbasicinfo();
        configbasicinfo2.setId(configbasicinfo1.getId());
        assertThat(configbasicinfo1).isEqualTo(configbasicinfo2);
        configbasicinfo2.setId(2L);
        assertThat(configbasicinfo1).isNotEqualTo(configbasicinfo2);
        configbasicinfo1.setId(null);
        assertThat(configbasicinfo1).isNotEqualTo(configbasicinfo2);
    }
}
