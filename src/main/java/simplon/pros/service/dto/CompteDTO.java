package simplon.pros.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link simplon.pros.domain.Compte} entity.
 */
public class CompteDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer numcom;

    @NotNull
    private Double solde;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
            ", numcom=" + getNumcom() +
            ", solde=" + getSolde() +
            "}";
    }
}
