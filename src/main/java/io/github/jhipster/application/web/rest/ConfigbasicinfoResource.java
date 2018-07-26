package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.Configbasicinfo;
import io.github.jhipster.application.repository.ConfigbasicinfoRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Configbasicinfo.
 */
@RestController
@RequestMapping("/api")
public class ConfigbasicinfoResource {

    private final Logger log = LoggerFactory.getLogger(ConfigbasicinfoResource.class);

    private static final String ENTITY_NAME = "configbasicinfo";

    private final ConfigbasicinfoRepository configbasicinfoRepository;

    public ConfigbasicinfoResource(ConfigbasicinfoRepository configbasicinfoRepository) {
        this.configbasicinfoRepository = configbasicinfoRepository;
    }

    /**
     * POST  /configbasicinfos : Create a new configbasicinfo.
     *
     * @param configbasicinfo the configbasicinfo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new configbasicinfo, or with status 400 (Bad Request) if the configbasicinfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/configbasicinfos")
    @Timed
    public ResponseEntity<Configbasicinfo> createConfigbasicinfo(@RequestBody Configbasicinfo configbasicinfo) throws URISyntaxException {
        log.debug("REST request to save Configbasicinfo : {}", configbasicinfo);
        if (configbasicinfo.getId() != null) {
            throw new BadRequestAlertException("A new configbasicinfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Configbasicinfo result = configbasicinfoRepository.save(configbasicinfo);
        return ResponseEntity.created(new URI("/api/configbasicinfos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /configbasicinfos : Updates an existing configbasicinfo.
     *
     * @param configbasicinfo the configbasicinfo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated configbasicinfo,
     * or with status 400 (Bad Request) if the configbasicinfo is not valid,
     * or with status 500 (Internal Server Error) if the configbasicinfo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/configbasicinfos")
    @Timed
    public ResponseEntity<Configbasicinfo> updateConfigbasicinfo(@RequestBody Configbasicinfo configbasicinfo) throws URISyntaxException {
        log.debug("REST request to update Configbasicinfo : {}", configbasicinfo);
        if (configbasicinfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Configbasicinfo result = configbasicinfoRepository.save(configbasicinfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, configbasicinfo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /configbasicinfos : get all the configbasicinfos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of configbasicinfos in body
     */
    @GetMapping("/configbasicinfos")
    @Timed
    public List<Configbasicinfo> getAllConfigbasicinfos() {
        log.debug("REST request to get all Configbasicinfos");
        return configbasicinfoRepository.findAll();
    }

    /**
     * GET  /configbasicinfos/:id : get the "id" configbasicinfo.
     *
     * @param id the id of the configbasicinfo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the configbasicinfo, or with status 404 (Not Found)
     */
    @GetMapping("/configbasicinfos/{id}")
    @Timed
    public ResponseEntity<Configbasicinfo> getConfigbasicinfo(@PathVariable Long id) {
        log.debug("REST request to get Configbasicinfo : {}", id);
        Optional<Configbasicinfo> configbasicinfo = configbasicinfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(configbasicinfo);
    }

    /**
     * DELETE  /configbasicinfos/:id : delete the "id" configbasicinfo.
     *
     * @param id the id of the configbasicinfo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/configbasicinfos/{id}")
    @Timed
    public ResponseEntity<Void> deleteConfigbasicinfo(@PathVariable Long id) {
        log.debug("REST request to delete Configbasicinfo : {}", id);

        configbasicinfoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
