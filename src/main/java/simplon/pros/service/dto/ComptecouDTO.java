package simplon.pros.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link simplon.pros.domain.Comptecou} entity.
 */
public class ComptecouDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer comptecouid;

    @NotNull
    private Integer fraistrans;

    @NotNull
    private Double soldemin;

    @NotNull
    private Integer compteid;


    private Long compteId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getComptecouid() {
        return comptecouid;
    }

    public void setComptecouid(Integer comptecouid) {
        this.comptecouid = comptecouid;
    }

    public Integer getFraistrans() {
        return fraistrans;
    }

    public void setFraistrans(Integer fraistrans) {
        this.fraistrans = fraistrans;
    }

    public Double getSoldemin() {
        return soldemin;
    }

    public void setSoldemin(Double soldemin) {
        this.soldemin = soldemin;
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
        if (!(o instanceof ComptecouDTO)) {
            return false;
        }

        return id != null && id.equals(((ComptecouDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ComptecouDTO{" +
            "id=" + getId() +
            ", comptecouid=" + getComptecouid() +
            ", fraistrans=" + getFraistrans() +
            ", soldemin=" + getSoldemin() +
            ", compteid=" + getCompteid() +
            ", compteId=" + getCompteId() +
            "}";
    }
}
