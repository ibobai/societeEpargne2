package simplon.pros.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link simplon.pros.domain.Compteepa} entity.
 */
public class CompteepaDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer tauxinteret;

    @NotNull
    private Integer plafond;


    private Long compteId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
            ", tauxinteret=" + getTauxinteret() +
            ", plafond=" + getPlafond() +
            ", compteId=" + getCompteId() +
            "}";
    }
}
