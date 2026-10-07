package ma.youcode.clinic.config;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import jakarta.inject.Singleton;
import ma.youcode.clinic.feature.consultation.repository.ConsultationRepository;
import ma.youcode.clinic.feature.demande.repository.DemandeExpertiseRepository;
import ma.youcode.clinic.feature.demande.service.DemandeExpertiseService;
import ma.youcode.clinic.feature.specialiste.repository.SpecialisteRepository;
import ma.youcode.clinic.feature.specialiste.service.SpecialisteService;

public class ApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(DemandeExpertiseRepository.class)
            .to(DemandeExpertiseRepository.class)
            .in(Singleton.class);
            
        bind(ConsultationRepository.class)
            .to(ConsultationRepository.class)
            .in(Singleton.class);

        bind(SpecialisteRepository.class)
            .to(SpecialisteRepository.class)
            .in(Singleton.class);

        bind(DemandeExpertiseService.class)
            .to(DemandeExpertiseService.class)
            .in(Singleton.class);

        bind(SpecialisteService.class)
            .to(SpecialisteService.class)
            .in(Singleton.class);
    }
}
