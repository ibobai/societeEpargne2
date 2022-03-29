package simplon.pros.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Client.
 */
@Entity
@Table(name = "client")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "clientid", nullable = false)
    private Integer clientid;

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
    @Size(max = 250)
    @Column(name = "adresse", length = 250, nullable = false)
    private String adresse;

    @NotNull
    @Size(max = 100)
    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @NotNull
    @Column(name = "conseillerid", nullable = false)
    private Integer conseillerid;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @OneToOne
    @JoinColumn(unique = true)
    private Compte compte;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getClientid() {
        return clientid;
    }

    public Client clientid(Integer clientid) {
        this.clientid = clientid;
        return this;
    }

    public void setClientid(Integer clientid) {
        this.clientid = clientid;
    }

    public String getNom() {
        return nom;
    }

    public Client nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Client prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public Client tel(String tel) {
        this.tel = tel;
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public Client adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public Client email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getConseillerid() {
        return conseillerid;
    }

    public Client conseillerid(Integer conseillerid) {
        this.conseillerid = conseillerid;
        return this;
    }

    public void setConseillerid(Integer conseillerid) {
        this.conseillerid = conseillerid;
    }

    public User getUser() {
        return user;
    }

    public Client user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Compte getCompte() {
        return compte;
    }

    public Client compte(Compte compte) {
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
        if (!(o instanceof Client)) {
            return false;
        }
        return id != null && id.equals(((Client) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Client{" +
            "id=" + getId() +
            ", clientid=" + getClientid() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", tel='" + getTel() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", email='" + getEmail() + "'" +
            ", conseillerid=" + getConseillerid() +
            "}";
    }
}
