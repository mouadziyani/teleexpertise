package ma.youcode.clinic.model.entity;

import jakarta.persistence.*;
import ma.youcode.clinic.model.enums.StatutConsultation;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultations")
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private User doctor;

    @Column(nullable = false)
    private String motif;

    @Column(columnDefinition = "TEXT")
    private String observations;

    @Column(columnDefinition = "TEXT")
    private String diagnostic;

    @Column(columnDefinition = "TEXT")
    private String treatment;

    private Double cout;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutConsultation statut;

    @Column(name = "date_consultation", nullable = false)
    private LocalDateTime dateConsultation;
}
