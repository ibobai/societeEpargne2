package simplon.pros.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Compte.
 */
@Entity
@Table(name = "compte")
public class Compte implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "numcom", nullable = false)
    private Integer numcom;

    @NotNull
    @Column(name = "solde", nullable = false)
    private Double solde;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumcom() {
        return numcom;
    }

    public Compte numcom(Integer numcom) {
        this.numcom = numcom;
        return this;
    }

    public void setNumcom(Integer numcom) {
        this.numcom = numcom;
    }

    public Double getSolde() {
        return solde;
    }

    public Compte solde(Double solde) {
        this.solde = solde;
        return this;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Compte)) {
            return false;
        }
        return id != null && id.equals(((Compte) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Compte{" +
            "id=" + getId() +
            ", numcom=" + getNumcom() +
            ", solde=" + getSolde() +
            "}";
    }
}
