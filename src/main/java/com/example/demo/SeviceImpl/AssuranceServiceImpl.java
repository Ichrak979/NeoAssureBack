package com.example.demo.SeviceImpl;

import com.example.demo.Repository.AssuranceRepository;
import com.example.demo.Services.AssuranceService;
import com.example.demo.entities.Assurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AssuranceServiceImpl implements AssuranceService {

    @Autowired
    private AssuranceRepository assuranceRepository;

    @Override
    public Assurance createAssurance(Assurance assurance) {
        return assuranceRepository.save(assurance);
    }

    @Override
    public List<Assurance> getAllAssurances() {
        return assuranceRepository.findAll();
    }

    @Override
    public Optional<Assurance> getAssuranceById(Long id) {
        return assuranceRepository.findById(id);
    }

    @Override
    public Assurance updateAssurance(Long id, Assurance assurance) {
        if (assuranceRepository.existsById(id)) {
            assurance.setId(id);
            return assuranceRepository.save(assurance);
        }
        return null;
    }

    @Override
    public void deleteAssurance(Long id) {
        assuranceRepository.deleteById(id);
    }


    @Override
    public String verifierAssurance(String immatriculation, Date dateAccident) {
        Optional<Assurance> assuranceOpt = assuranceRepository.findByVehiculeImmatriculation(immatriculation);

        if (assuranceOpt.isPresent()) {
            Assurance assurance = assuranceOpt.get();
            Date dateFin = assurance.getDateFin();

            if (!dateAccident.after(dateFin)) { // dateAccident <= dateFin
                return "Le véhicule est assuré et l'assurance est valide.";
            } else {
                return "Le véhicule est assuré, mais l'assurance a expiré.";
            }
        } else {
            return "Le véhicule n'est pas assuré.";
        }
    }
}

