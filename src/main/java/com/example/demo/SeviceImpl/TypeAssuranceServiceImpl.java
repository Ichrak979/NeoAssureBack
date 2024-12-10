package com.example.demo.SeviceImpl;

import com.example.demo.Repository.TypeAssuranceRepository;
import com.example.demo.Services.TypeAssuranceService;
import com.example.demo.entities.TypeAssurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeAssuranceServiceImpl implements TypeAssuranceService {

    @Autowired
    private TypeAssuranceRepository typeAssuranceRepository;

    @Override
    public TypeAssurance createTypeAssurance(TypeAssurance typeAssurance) {
        return typeAssuranceRepository.save(typeAssurance);
    }

    @Override
    public List<TypeAssurance> getAllTypesAssurance() {
        return typeAssuranceRepository.findAll();
    }

    @Override
    public Optional<TypeAssurance> getTypeAssuranceById(Long id) {
        return typeAssuranceRepository.findById(id);
    }


    @Override
    public void deleteTypeAssurance(Long id) {
        typeAssuranceRepository.deleteById(id);
    }
}
