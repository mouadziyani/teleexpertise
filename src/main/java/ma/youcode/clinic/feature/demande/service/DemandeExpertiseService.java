package ma.youcode.clinic.feature.demande.service;

import ma.youcode.clinic.feature.consultation.repository.ConsultationRepository;
import ma.youcode.clinic.feature.demande.dto.CreateDemandeExpertiseRequestDTO;
import ma.youcode.clinic.feature.demande.repository.DemandeExpertiseRepository;
import ma.youcode.clinic.feature.specialiste.repository.SpecialisteRepository;
import ma.youcode.clinic.model.entity.Consultation;
import ma.youcode.clinic.model.entity.DemandeExpertise;
import ma.youcode.clinic.model.entity.Specialiste;
import ma.youcode.clinic.model.enums.Priorite;
import ma.youcode.clinic.model.enums.StatutDemande;
import org.jvnet.hk2.annotations.Service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DemandeExpertiseService {
    private final DemandeExpertiseRepository demandeExpertiseRepository = new DemandeExpertiseRepository();
    private final SpecialisteRepository specialisteRepository = new SpecialisteRepository();
    private final ConsultationRepository consultationRepository = new ConsultationRepository();

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
                    StatutDemande.valueOf(requestDTO.getPriorite()),
                    requestDTO.getAvis(),
                    requestDTO.getRecommandations(),
                    LocalDateTime.now()
            );

            demandeExpertiseRepository.save(demande);
        }
        return errors;
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

        if (requestDTO.getAvis() == null || requestDTO.getAvis().trim().isEmpty()) {
            errors.put("avis", "L'avis est obligatoire.");
        }

        if (requestDTO.getRecommandations() == null || requestDTO.getRecommandations().trim().isEmpty()) {
            errors.put("recommandations", "Les recommandations sont obligatoires.");
        }

        return errors;
    }
    
}
