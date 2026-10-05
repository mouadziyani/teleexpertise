package ma.youcode.clinic.feature.demande.dto;

public class DemandeExpertiseRequestDTO {
    private Long consultation_id;

    private Long specialiste_id;

    private String question;

    private String priorite;

    private String avis;

    private String recommandations;

    public Long getConsultation_id() {
        return consultation_id;
    }

    public void setConsultation_id(Long consultation_id) {
        this.consultation_id = consultation_id;
    }

    public Long getSpecialiste_id() {
        return specialiste_id;
    }

    public void setSpecialiste_id(Long specialiste_id) {
        this.specialiste_id = specialiste_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getPriorite() {
        return priorite;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
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
}
