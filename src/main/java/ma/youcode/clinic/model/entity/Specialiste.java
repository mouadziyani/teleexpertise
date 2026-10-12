package ma.youcode.clinic.model.entity;

import jakarta.persistence.*;
import ma.youcode.clinic.model.enums.Specialite;

@Entity 
@Table(name = "specialiste")
public class Specialiste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "specialite", nullable = false)
    private Specialite specialite;

    @Column(name = "tarif")
    private Double tarif;

    public Specialiste() {
    } 

    public Specialiste(User user, Specialite specialite, Double tarif) {
        this.user = user;
        this.specialite = specialite;
        this.tarif = tarif;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserId() {
        return user;
    }

    public void setUserId(User user) {
        this.user = user;
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }

    public Double getTarif() {
        return tarif;
    }

    public void setTarif(Double tarif) {
        this.tarif = tarif;
    }
}