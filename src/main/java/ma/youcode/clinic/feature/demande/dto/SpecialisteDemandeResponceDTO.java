package ma.youcode.clinic.feature.demande.dto;

import ma.youcode.clinic.model.enums.Priorite;
import ma.youcode.clinic.model.enums.StatutDemande;

import java.time.LocalDateTime;

public class SpecialisteDemandeResponceDTO {
    private Long id;
    private Long consultationId;
    private Long specialisteId;
    private String question;
    private Priorite priorite;
    private StatutDemande statut;
    private String avis;
    private String recommandations;
    private LocalDateTime dateCreation;

    public SpecialisteDemandeResponceDTO() {
    }

    public SpecialisteDemandeResponceDTO(
            Long id,
            Long consultationId,
            Long specialisteId,
            String question,
            Priorite priorite,
            StatutDemande statut,
            String avis,
            String recommandations,
            LocalDateTime dateCreation
    ) {
        this.id = id;
        this.consultationId = consultationId;
        this.specialisteId = specialisteId;
        this.question = question;
        this.priorite = priorite;
        this.statut = statut;
        this.avis = avis;
        this.recommandations = recommandations;
        this.dateCreation = dateCreation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(Long consultationId) {
        this.consultationId = consultationId;
    }

    public Long getSpecialisteId() {
        return specialisteId;
    }

    public void setSpecialisteId(Long specialisteId) {
        this.specialisteId = specialisteId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public void setPriorite(Priorite priorite) {
        this.priorite = priorite;
    }

    public StatutDemande getStatut() {
        return statut;
    }

    public void setStatut(StatutDemande statut) {
        this.statut = statut;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public String getRecommandations() {
        return recommandations;
    }

    public void setRecommandations(String recommandations) {
        this.recommandations = recommandations;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }
}