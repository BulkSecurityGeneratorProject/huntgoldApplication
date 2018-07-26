package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Configbasicinfo.
 */
@Entity
@Table(name = "configbasicinfo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Configbasicinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "configcode")
    private String configcode;

    @Column(name = "configname")
    private String configname;

    @Column(name = "configvalue")
    private String configvalue;

    @Column(name = "parentid")
    private String parentid;

    @Column(name = "configtypename")
    private String configtypename;

    @Column(name = "configtype")
    private String configtype;

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

    public String getConfigcode() {
        return configcode;
    }

    public Configbasicinfo configcode(String configcode) {
        this.configcode = configcode;
        return this;
    }

    public void setConfigcode(String configcode) {
        this.configcode = configcode;
    }

    public String getConfigname() {
        return configname;
    }

    public Configbasicinfo configname(String configname) {
        this.configname = configname;
        return this;
    }

    public void setConfigname(String configname) {
        this.configname = configname;
    }

    public String getConfigvalue() {
        return configvalue;
    }

    public Configbasicinfo configvalue(String configvalue) {
        this.configvalue = configvalue;
        return this;
    }

    public void setConfigvalue(String configvalue) {
        this.configvalue = configvalue;
    }

    public String getParentid() {
        return parentid;
    }

    public Configbasicinfo parentid(String parentid) {
        this.parentid = parentid;
        return this;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getConfigtypename() {
        return configtypename;
    }

    public Configbasicinfo configtypename(String configtypename) {
        this.configtypename = configtypename;
        return this;
    }

    public void setConfigtypename(String configtypename) {
        this.configtypename = configtypename;
    }

    public String getConfigtype() {
        return configtype;
    }

    public Configbasicinfo configtype(String configtype) {
        this.configtype = configtype;
        return this;
    }

    public void setConfigtype(String configtype) {
        this.configtype = configtype;
    }

    public String getCreateby() {
        return createby;
    }

    public Configbasicinfo createby(String createby) {
        this.createby = createby;
        return this;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public Instant getCreatedate() {
        return createdate;
    }

    public Configbasicinfo createdate(Instant createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(Instant createdate) {
        this.createdate = createdate;
    }

    public String getUpdateby() {
        return updateby;
    }

    public Configbasicinfo updateby(String updateby) {
        this.updateby = updateby;
        return this;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby;
    }

    public Instant getUpdatedate() {
        return updatedate;
    }

    public Configbasicinfo updatedate(Instant updatedate) {
        this.updatedate = updatedate;
        return this;
    }

    public void setUpdatedate(Instant updatedate) {
        this.updatedate = updatedate;
    }

    public String getRemarks() {
        return remarks;
    }

    public Configbasicinfo remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public Configbasicinfo delflag(Integer delflag) {
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
        Configbasicinfo configbasicinfo = (Configbasicinfo) o;
        if (configbasicinfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), configbasicinfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Configbasicinfo{" +
            "id=" + getId() +
            ", configcode='" + getConfigcode() + "'" +
            ", configname='" + getConfigname() + "'" +
            ", configvalue='" + getConfigvalue() + "'" +
            ", parentid='" + getParentid() + "'" +
            ", configtypename='" + getConfigtypename() + "'" +
            ", configtype='" + getConfigtype() + "'" +
            ", createby='" + getCreateby() + "'" +
            ", createdate='" + getCreatedate() + "'" +
            ", updateby='" + getUpdateby() + "'" +
            ", updatedate='" + getUpdatedate() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", delflag=" + getDelflag() +
            "}";
    }
}
