package simplon.pros.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Conseiller.
 */
@Entity
@Table(name = "conseiller")
public class Conseiller implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "conseillerid", nullable = false)
    private Integer conseillerid;

    @NotNull
    @Size(max = 50)
    @Column(name = "nom", length = 50, nullable = false)
    private String nom;

    @NotNull
    @Size(max = 50)
    @Column(name = "prenom", length = 50, nullable = false)
    private String prenom;

    @NotNull
    @Size(max = 50)
    @Column(name = "tel", length = 50, nullable = false)
    private String tel;

    @NotNull
    @Size(max = 150)
    @Column(name = "email", length = 150, nullable = false)
    private String email;

    @NotNull
    @Size(max = 50)
    @Column(name = "nomutilisateur", length = 50, nullable = false)
    private String nomutilisateur;

    @NotNull
    @Size(max = 100)
    @Column(name = "motdepasse", length = 100, nullable = false)
    private String motdepasse;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getConseillerid() {
        return conseillerid;
    }

    public Conseiller conseillerid(Integer conseillerid) {
        this.conseillerid = conseillerid;
        return this;
    }

    public void setConseillerid(Integer conseillerid) {
        this.conseillerid = conseillerid;
    }

    public String getNom() {
        return nom;
    }

    public Conseiller nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Conseiller prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public Conseiller tel(String tel) {
        this.tel = tel;
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public Conseiller email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomutilisateur() {
        return nomutilisateur;
    }

    public Conseiller nomutilisateur(String nomutilisateur) {
        this.nomutilisateur = nomutilisateur;
        return this;
    }

    public void setNomutilisateur(String nomutilisateur) {
        this.nomutilisateur = nomutilisateur;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public Conseiller motdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
        return this;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public User getUser() {
        return user;
    }

    public Conseiller user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Conseiller)) {
            return false;
        }
        return id != null && id.equals(((Conseiller) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Conseiller{" +
            "id=" + getId() +
            ", conseillerid=" + getConseillerid() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", tel='" + getTel() + "'" +
            ", email='" + getEmail() + "'" +
            ", nomutilisateur='" + getNomutilisateur() + "'" +
            ", motdepasse='" + getMotdepasse() + "'" +
            "}";
    }
}
