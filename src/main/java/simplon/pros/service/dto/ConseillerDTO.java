package simplon.pros.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link simplon.pros.domain.Conseiller} entity.
 */
public class ConseillerDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 50)
    private String nom;

    @NotNull
    @Size(max = 50)
    private String prenom;

    @NotNull
    @Size(max = 50)
    private String tel;

    @NotNull
    @Size(max = 150)
    private String email;

    @NotNull
    @Size(max = 50)
    private String nomutilisateur;

    @NotNull
    @Size(max = 100)
    private String motdepasse;


    private Long userId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomutilisateur() {
        return nomutilisateur;
    }

    public void setNomutilisateur(String nomutilisateur) {
        this.nomutilisateur = nomutilisateur;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConseillerDTO)) {
            return false;
        }

        return id != null && id.equals(((ConseillerDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ConseillerDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", tel='" + getTel() + "'" +
            ", email='" + getEmail() + "'" +
            ", nomutilisateur='" + getNomutilisateur() + "'" +
            ", motdepasse='" + getMotdepasse() + "'" +
            ", userId=" + getUserId() +
            "}";
    }
}
