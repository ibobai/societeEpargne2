package simplon.pros.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link simplon.pros.domain.Compteepa} entity.
 */
public class CompteepaDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer compteepaid;

    @NotNull
    private Integer tauxinteret;

    @NotNull
    private Integer plafond;

    @NotNull
    private Integer compteid;


    private Long compteId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCompteepaid() {
        return compteepaid;
    }

    public void setCompteepaid(Integer compteepaid) {
        this.compteepaid = compteepaid;
    }

    public Integer getTauxinteret() {
        return tauxinteret;
    }

    public void setTauxinteret(Integer tauxinteret) {
        this.tauxinteret = tauxinteret;
    }

    public Integer getPlafond() {
        return plafond;
    }

    public void setPlafond(Integer plafond) {
        this.plafond = plafond;
    }

    public Integer getCompteid() {
        return compteid;
    }

    public void setCompteid(Integer compteid) {
        this.compteid = compteid;
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
        if (!(o instanceof CompteepaDTO)) {
            return false;
        }

        return id != null && id.equals(((CompteepaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CompteepaDTO{" +
            "id=" + getId() +
            ", compteepaid=" + getCompteepaid() +
            ", tauxinteret=" + getTauxinteret() +
            ", plafond=" + getPlafond() +
            ", compteid=" + getCompteid() +
            ", compteId=" + getCompteId() +
            "}";
    }
}
