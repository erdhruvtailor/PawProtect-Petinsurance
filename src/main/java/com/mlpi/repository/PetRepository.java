package com.mlpi.repository;

import com.mlpi.model.Coverage;
import com.mlpi.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet,Long> {

    @Query("FROM Pet pet where pet.user.id = :id")
    public List<Pet> getPetByUser(Long id);

    public Pet getPetById(Long id);

    @Query("SELECT COUNT(p) FROM Pet p WHERE p.user.id = :userId")
    long countPetsByUserId(Long userId);
}
