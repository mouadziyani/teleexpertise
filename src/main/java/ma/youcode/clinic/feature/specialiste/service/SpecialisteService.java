package ma.youcode.clinic.feature.specialiste.service;

import java.util.ArrayList;
import java.util.List;

import ma.youcode.clinic.feature.specialiste.repository.SpecialisteRepository;
import ma.youcode.clinic.model.entity.Specialiste;
import ma.youcode.clinic.model.enums.Specialite;

public class SpecialisteService {
    public final SpecialisteRepository repository ;

    public SpecialisteService(SpecialisteRepository repository){
        this.repository = repository ;
    }

    public List<Specialiste> listSpesialiste(Specialite specialite , String tarif){

        List<Specialiste> s = new ArrayList<>();

        if (specialite!=null){
            s = repository.findBySpecialite(specialite);
        }else{
            s = repository.findAll();
        }


        if ("tarif".equalsIgnoreCase(tarif)) {
            return s.stream()
                    .sorted((s1,s2)->s1.getTarif().compareTo(s2.getTarif()))
                    .toList();
        }

        return s ;
    }
}
