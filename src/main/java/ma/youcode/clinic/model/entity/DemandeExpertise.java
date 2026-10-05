package ma.youcode.clinic.model.entity;

import jakarta.persistence.*;
import ma.youcode.clinic.model.enums.Priorite;
import ma.youcode.clinic.model.enums.StatutDemande;

import java.time.LocalDateTime;

@Entity
@Table(name = "demande_expertise")
public class DemandeExpertise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "consultation_id", nullable = false)
    private Consultation consultation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "specialiste_id", nullable = false)
    private Specialiste specialiste;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String question;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priorite priorite;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutDemande statut;

    @Column(columnDefinition = "TEXT")
    private String avis;

    @Column(columnDefinition = "TEXT")
    private String recommandations;

    @Column(name = "date_creation", nullable = false)
    private LocalDateTime dateCreation;
}
