package com.mlpi.service;

import com.mlpi.model.Pet;
import com.mlpi.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImp implements PetService {

    @Autowired
    PetRepository petRepository;

    @Override
    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public List<Pet> getPetByUser(Long id) {
        return petRepository.getPetByUser(id);
    }

    @Override
    public Pet getPetById(Long id) {
        return petRepository.getPetById(id);
    }

}
