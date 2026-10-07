package ma.youcode.clinic.feature.demande.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import ma.youcode.clinic.feature.consultation.repository.ConsultationRepository;
import ma.youcode.clinic.feature.demande.dto.CreateDemandeExpertiseRequestDTO;
import ma.youcode.clinic.feature.demande.dto.RepondreDemandeDTO;
import ma.youcode.clinic.feature.demande.repository.DemandeExpertiseRepository;
import ma.youcode.clinic.feature.specialiste.repository.SpecialisteRepository;
import ma.youcode.clinic.model.entity.Consultation;
import ma.youcode.clinic.model.entity.DemandeExpertise;
import ma.youcode.clinic.model.entity.Specialiste;
import ma.youcode.clinic.model.enums.Priorite;
import ma.youcode.clinic.model.enums.StatutDemande;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class DemandeExpertiseService {
    @Inject
    private DemandeExpertiseRepository demandeExpertiseRepository;
    @Inject
    private SpecialisteRepository specialisteRepository;
    @Inject
    private ConsultationRepository consultationRepository;

    public Map<String , String> creatDemande(CreateDemandeExpertiseRequestDTO requestDTO) {
        Map<String , String> errors = validateCreateDemandeExpertise(requestDTO);

        if (errors.isEmpty()) {
            Specialiste specialiste = specialisteRepository.findById(requestDTO.getSpecialiste_id());
            Consultation consultation = consultationRepository.findById(requestDTO.getConsultation_id());

            DemandeExpertise demande = new DemandeExpertise(
                    consultation,
                    specialiste,
                    requestDTO.getQuestion(),
                    Priorite.valueOf(requestDTO.getPriorite()),
                    StatutDemande.EN_ATTENTE,
                    null,
                    null,
                    LocalDateTime.now()
            );

            DemandeExpertise createdDemande = demandeExpertiseRepository.save(demande);

            if (createdDemande == null) {
                errors.put("demande" , "Error demande ne creer pas en success reessayer une autre foit.");
            }
        }
        return errors;
    }

    public List<DemandeExpertise> getSpecialisteDemande(Long id , String status) {
        return demandeExpertiseRepository.findBySpecialisteAndStatut(id , StatutDemande.valueOf(status));
    }

    private Map<String, String> validateCreateDemandeExpertise(CreateDemandeExpertiseRequestDTO requestDTO) {
        Map<String, String> errors = new LinkedHashMap<>();

        if (requestDTO.getSpecialiste_id() == null) {
            errors.put("specialiste", "Le spécialiste est obligatoire.");
        } else {
            Specialiste specialiste = specialisteRepository.findById(requestDTO.getSpecialiste_id());

            if (specialiste == null) {
                errors.put("specialiste", "Le spécialiste n'existe pas.");
            }
        }

        if (requestDTO.getConsultation_id() == null) {
            errors.put("consultation", "La consultation est obligatoire.");
        } else {
            Consultation consultation = consultationRepository.findById(requestDTO.getConsultation_id());

            if (consultation == null) {
                errors.put("consultation", "La consultation n'existe pas.");
            }
        }

        if (requestDTO.getQuestion() == null || requestDTO.getQuestion().trim().isEmpty()) {
            errors.put("question", "La question est obligatoire.");
        }

        if (requestDTO.getPriorite() == null || requestDTO.getPriorite().trim().isEmpty()) {
            errors.put("priorite", "La priorité est obligatoire.");
        }

        return errors;
    }
    
    public void repondreDemande(Long demandeID , Long specialistID , RepondreDemandeDTO dto){
        DemandeExpertise demande = demandeExpertiseRepository.findById(demandeID);
        
        if (demande == null) {
            System.out.println("Demande non trouvée avec L'ID: " + demandeID);
        }

        if(!demande.getSpecialiste().getId().equals(specialistID)){
            System.out.println("Vous n'êtes pas autorisé à répondre à cette demande.");
        }

        demande.setAvis(dto.getAvis());
        demande.setRecommandations(dto.getRecommendation());
        demande.setStatut(StatutDemande.TERMINEE);

        demandeExpertiseRepository.update(demande);
    }
}
