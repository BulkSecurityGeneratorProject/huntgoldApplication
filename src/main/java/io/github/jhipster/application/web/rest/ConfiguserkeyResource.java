package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.Configuserkey;
import io.github.jhipster.application.repository.ConfiguserkeyRepository;
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
 * REST controller for managing Configuserkey.
 */
@RestController
@RequestMapping("/api")
public class ConfiguserkeyResource {

    private final Logger log = LoggerFactory.getLogger(ConfiguserkeyResource.class);

    private static final String ENTITY_NAME = "configuserkey";

    private final ConfiguserkeyRepository configuserkeyRepository;

    public ConfiguserkeyResource(ConfiguserkeyRepository configuserkeyRepository) {
        this.configuserkeyRepository = configuserkeyRepository;
    }

    /**
     * POST  /configuserkeys : Create a new configuserkey.
     *
     * @param configuserkey the configuserkey to create
     * @return the ResponseEntity with status 201 (Created) and with body the new configuserkey, or with status 400 (Bad Request) if the configuserkey has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/configuserkeys")
    @Timed
    public ResponseEntity<Configuserkey> createConfiguserkey(@RequestBody Configuserkey configuserkey) throws URISyntaxException {
        log.debug("REST request to save Configuserkey : {}", configuserkey);
        if (configuserkey.getId() != null) {
            throw new BadRequestAlertException("A new configuserkey cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Configuserkey result = configuserkeyRepository.save(configuserkey);
        return ResponseEntity.created(new URI("/api/configuserkeys/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /configuserkeys : Updates an existing configuserkey.
     *
     * @param configuserkey the configuserkey to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated configuserkey,
     * or with status 400 (Bad Request) if the configuserkey is not valid,
     * or with status 500 (Internal Server Error) if the configuserkey couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/configuserkeys")
    @Timed
    public ResponseEntity<Configuserkey> updateConfiguserkey(@RequestBody Configuserkey configuserkey) throws URISyntaxException {
        log.debug("REST request to update Configuserkey : {}", configuserkey);
        if (configuserkey.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Configuserkey result = configuserkeyRepository.save(configuserkey);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, configuserkey.getId().toString()))
            .body(result);
    }

    /**
     * GET  /configuserkeys : get all the configuserkeys.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of configuserkeys in body
     */
    @GetMapping("/configuserkeys")
    @Timed
    public List<Configuserkey> getAllConfiguserkeys() {
        log.debug("REST request to get all Configuserkeys");
        return configuserkeyRepository.findAll();
    }

    /**
     * GET  /configuserkeys/:id : get the "id" configuserkey.
     *
     * @param id the id of the configuserkey to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the configuserkey, or with status 404 (Not Found)
     */
    @GetMapping("/configuserkeys/{id}")
    @Timed
    public ResponseEntity<Configuserkey> getConfiguserkey(@PathVariable Long id) {
        log.debug("REST request to get Configuserkey : {}", id);
        Optional<Configuserkey> configuserkey = configuserkeyRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(configuserkey);
    }

    /**
     * DELETE  /configuserkeys/:id : delete the "id" configuserkey.
     *
     * @param id the id of the configuserkey to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/configuserkeys/{id}")
    @Timed
    public ResponseEntity<Void> deleteConfiguserkey(@PathVariable Long id) {
        log.debug("REST request to delete Configuserkey : {}", id);

        configuserkeyRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
