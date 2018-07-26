package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Configuserkey.
 */
@Entity
@Table(name = "configuserkey")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Configuserkey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "platform")
    private String platform;

    @Column(name = "apikey")
    private String apikey;

    @Column(name = "secretkey")
    private String secretkey;

    @Column(name = "createby")
    private String createby;

    @Column(name = "createdate")
    private Instant createdate;

    @Column(name = "updateby")
    private String updateby;

    @Column(name = "updatedate")
    private Instant updatedate;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "delflag")
    private Integer delflag;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public Configuserkey platform(String platform) {
        this.platform = platform;
        return this;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getApikey() {
        return apikey;
    }

    public Configuserkey apikey(String apikey) {
        this.apikey = apikey;
        return this;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getSecretkey() {
        return secretkey;
    }

    public Configuserkey secretkey(String secretkey) {
        this.secretkey = secretkey;
        return this;
    }

    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey;
    }

    public String getCreateby() {
        return createby;
    }

    public Configuserkey createby(String createby) {
        this.createby = createby;
        return this;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public Instant getCreatedate() {
        return createdate;
    }

    public Configuserkey createdate(Instant createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(Instant createdate) {
        this.createdate = createdate;
    }

    public String getUpdateby() {
        return updateby;
    }

    public Configuserkey updateby(String updateby) {
        this.updateby = updateby;
        return this;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby;
    }

    public Instant getUpdatedate() {
        return updatedate;
    }

    public Configuserkey updatedate(Instant updatedate) {
        this.updatedate = updatedate;
        return this;
    }

    public void setUpdatedate(Instant updatedate) {
        this.updatedate = updatedate;
    }

    public String getRemarks() {
        return remarks;
    }

    public Configuserkey remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public Configuserkey delflag(Integer delflag) {
        this.delflag = delflag;
        return this;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Configuserkey configuserkey = (Configuserkey) o;
        if (configuserkey.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), configuserkey.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Configuserkey{" +
            "id=" + getId() +
            ", platform='" + getPlatform() + "'" +
            ", apikey='" + getApikey() + "'" +
            ", secretkey='" + getSecretkey() + "'" +
            ", createby='" + getCreateby() + "'" +
            ", createdate='" + getCreatedate() + "'" +
            ", updateby='" + getUpdateby() + "'" +
            ", updatedate='" + getUpdatedate() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", delflag=" + getDelflag() +
            "}";
    }
}
