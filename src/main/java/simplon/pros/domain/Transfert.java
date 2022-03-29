package simplon.pros.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Transfert.
 */
@Entity
@Table(name = "transfert")
public class Transfert implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @NotNull
    @Column(name = "montant", nullable = false)
    private Double montant;

    @NotNull
    @Size(max = 10)
    @Column(name = "typeoperation", length = 10, nullable = false)
    private String typeoperation;

    @NotNull
    @Column(name = "numcompte", nullable = false)
    private Integer numcompte;

    @ManyToOne
    @JsonIgnoreProperties(value = "transferts", allowSetters = true)
    private Compte compte;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Transfert date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getMontant() {
        return montant;
    }

    public Transfert montant(Double montant) {
        this.montant = montant;
        return this;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getTypeoperation() {
        return typeoperation;
    }

    public Transfert typeoperation(String typeoperation) {
        this.typeoperation = typeoperation;
        return this;
    }

    public void setTypeoperation(String typeoperation) {
        this.typeoperation = typeoperation;
    }

    public Integer getNumcompte() {
        return numcompte;
    }

    public Transfert numcompte(Integer numcompte) {
        this.numcompte = numcompte;
        return this;
    }

    public void setNumcompte(Integer numcompte) {
        this.numcompte = numcompte;
    }

    public Compte getCompte() {
        return compte;
    }

    public Transfert compte(Compte compte) {
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
        if (!(o instanceof Transfert)) {
            return false;
        }
        return id != null && id.equals(((Transfert) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Transfert{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", montant=" + getMontant() +
            ", typeoperation='" + getTypeoperation() + "'" +
            ", numcompte=" + getNumcompte() +
            "}";
    }
}
