package simplon.pros.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Compteepa.
 */
@Entity
@Table(name = "compteepa")
public class Compteepa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "tauxinteret", nullable = false)
    private Integer tauxinteret;

    @NotNull
    @Column(name = "plafond", nullable = false)
    private Integer plafond;

    @ManyToOne
    @JsonIgnoreProperties(value = "compteepas", allowSetters = true)
    private Compte compte;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTauxinteret() {
        return tauxinteret;
    }

    public Compteepa tauxinteret(Integer tauxinteret) {
        this.tauxinteret = tauxinteret;
        return this;
    }

    public void setTauxinteret(Integer tauxinteret) {
        this.tauxinteret = tauxinteret;
    }

    public Integer getPlafond() {
        return plafond;
    }

    public Compteepa plafond(Integer plafond) {
        this.plafond = plafond;
        return this;
    }

    public void setPlafond(Integer plafond) {
        this.plafond = plafond;
    }

    public Compte getCompte() {
        return compte;
    }

    public Compteepa compte(Compte compte) {
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
        if (!(o instanceof Compteepa)) {
            return false;
        }
        return id != null && id.equals(((Compteepa) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Compteepa{" +
            "id=" + getId() +
            ", tauxinteret=" + getTauxinteret() +
            ", plafond=" + getPlafond() +
            "}";
    }
}
