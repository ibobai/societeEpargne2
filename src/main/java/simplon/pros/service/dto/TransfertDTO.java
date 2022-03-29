package simplon.pros.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link simplon.pros.domain.Transfert} entity.
 */
public class TransfertDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer transfertid;

    private LocalDate date;

    @NotNull
    private Double montant;

    @NotNull
    @Size(max = 10)
    private String typeoperation;

    @NotNull
    private Integer numcompte;

    @NotNull
    private Integer conseillerid;


    private Long compteId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTransfertid() {
        return transfertid;
    }

    public void setTransfertid(Integer transfertid) {
        this.transfertid = transfertid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public String getTypeoperation() {
        return typeoperation;
    }

    public void setTypeoperation(String typeoperation) {
        this.typeoperation = typeoperation;
    }

    public Integer getNumcompte() {
        return numcompte;
    }

    public void setNumcompte(Integer numcompte) {
        this.numcompte = numcompte;
    }

    public Integer getConseillerid() {
        return conseillerid;
    }

    public void setConseillerid(Integer conseillerid) {
        this.conseillerid = conseillerid;
    }

    public Long getCompteId() {
        return compteId;
    }

    public void setCompteId(Long compteId) {
        this.compteId = compteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransfertDTO)) {
            return false;
        }

        return id != null && id.equals(((TransfertDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TransfertDTO{" +
            "id=" + getId() +
            ", transfertid=" + getTransfertid() +
            ", date='" + getDate() + "'" +
            ", montant=" + getMontant() +
            ", typeoperation='" + getTypeoperation() + "'" +
            ", numcompte=" + getNumcompte() +
            ", conseillerid=" + getConseillerid() +
            ", compteId=" + getCompteId() +
            "}";
    }
}
