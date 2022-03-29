package simplon.pros.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Comptecou.
 */
@Entity
@Table(name = "comptecou")
public class Comptecou implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "comptecouid", nullable = false)
    private Integer comptecouid;

    @NotNull
    @Column(name = "fraistrans", nullable = false)
    private Integer fraistrans;

    @NotNull
    @Column(name = "soldemin", nullable = false)
    private Double soldemin;

    @NotNull
    @Column(name = "compteid", nullable = false)
    private Integer compteid;

    @ManyToOne
    @JsonIgnoreProperties(value = "comptecous", allowSetters = true)
    private Compte compte;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getComptecouid() {
        return comptecouid;
    }

    public Comptecou comptecouid(Integer comptecouid) {
        this.comptecouid = comptecouid;
        return this;
    }

    public void setComptecouid(Integer comptecouid) {
        this.comptecouid = comptecouid;
    }

    public Integer getFraistrans() {
        return fraistrans;
    }

    public Comptecou fraistrans(Integer fraistrans) {
        this.fraistrans = fraistrans;
        return this;
    }

    public void setFraistrans(Integer fraistrans) {
        this.fraistrans = fraistrans;
    }

    public Double getSoldemin() {
        return soldemin;
    }

    public Comptecou soldemin(Double soldemin) {
        this.soldemin = soldemin;
        return this;
    }

    public void setSoldemin(Double soldemin) {
        this.soldemin = soldemin;
    }

    public Integer getCompteid() {
        return compteid;
    }

    public Comptecou compteid(Integer compteid) {
        this.compteid = compteid;
        return this;
    }

    public void setCompteid(Integer compteid) {
        this.compteid = compteid;
    }

    public Compte getCompte() {
        return compte;
    }

    public Comptecou compte(Compte compte) {
        this.compte = compte;
        return this;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Comptecou)) {
            return false;
        }
        return id != null && id.equals(((Comptecou) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Comptecou{" +
            "id=" + getId() +
            ", comptecouid=" + getComptecouid() +
            ", fraistrans=" + getFraistrans() +
            ", soldemin=" + getSoldemin() +
            ", compteid=" + getCompteid() +
            "}";
    }
}
