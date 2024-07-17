package com.mlpi.service;

import com.mlpi.model.Pet;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PetService {

    Pet savePet(Pet pet);
    List<Pet> getPetByUser(Long id);
    Pet getPetById(Long id);

}
