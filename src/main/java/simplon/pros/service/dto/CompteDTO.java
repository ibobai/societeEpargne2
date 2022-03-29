package simplon.pros.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link simplon.pros.domain.Compte} entity.
 */
public class CompteDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer compteid;

    @NotNull
    private Integer numcom;

    @NotNull
    private Double solde;

    @NotNull
    private Integer clientid;


    private Long compteId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCompteid() {
        return compteid;
    }

    public void setCompteid(Integer compteid) {
        this.compteid = compteid;
    }

    public Integer getNumcom() {
        return numcom;
    }

    public void setNumcom(Integer numcom) {
        this.numcom = numcom;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public Integer getClientid() {
        return clientid;
    }

    public void setClientid(Integer clientid) {
        this.clientid = clientid;
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
        if (!(o instanceof CompteDTO)) {
            return false;
        }

        return id != null && id.equals(((CompteDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CompteDTO{" +
            "id=" + getId() +
            ", compteid=" + getCompteid() +
            ", numcom=" + getNumcom() +
            ", solde=" + getSolde() +
            ", clientid=" + getClientid() +
            ", compteId=" + getCompteId() +
            "}";
    }
}
