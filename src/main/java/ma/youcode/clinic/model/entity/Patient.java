package ma.youcode.clinic.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    @Column(name = "numero_securite_sociale", unique = true)
    private String numeroSecuriteSociale;

    @Column(name = "tension_arterielle")
    private String tensionArterielle;

    @Column(name = "frequence_cardiaque")
    private Double frequenceCardiaque;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "frequence_respiratoire")
    private Double frequenceRespiratoire;

    @Column(name = "date_arrivee")
    private LocalDateTime dateArrivee;
}
