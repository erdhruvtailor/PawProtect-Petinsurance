package com.mlpi.controller;

import com.mlpi.dto.CoverageDto;
import com.mlpi.dto.PetDto;
import com.mlpi.model.Coverage;
import com.mlpi.model.Pet;
import com.mlpi.service.CoverageServiceImp;
import com.mlpi.service.PetServiceImp;
import com.mlpi.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetServiceImp petServiceImp;
    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping("/listedPets")
    public String listedPets(Model model) {

        try {

            List<Pet> petsList = petServiceImp.getPetByUser(userServiceImpl.getSessionUser().getId());
            model.addAttribute("petsList", petsList);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/pet/listedPets";
        }
        return "user/listed_pets";
    }

    @GetMapping("/petForm")
    public String petForm(Model model, @RequestParam(value = "id", required = false) Long id) {

        try {
            PetDto petDto = new PetDto();
            if (id != null) {
                Pet pet = petServiceImp.getPetById(id);
                petDto.setPetName(pet.getPetName());
                petDto.setPetType(pet.getPetType());
                petDto.setBreed(pet.getBreed());
                petDto.setGender(pet.getGender());
                petDto.setReproductiveOrgans(pet.getReproductiveOrgans());
                petDto.setAge(pet.getAge());
                petDto.setWeight(pet.getWeight());
            }

            model.addAttribute("petDto", petDto);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/pet/listedPets";
        }

        return "user/pets_form";
    }

    @PostMapping("/petForm")
    public String petForm(Model model, @RequestParam(value = "id", required = false) Long id, @Valid @ModelAttribute PetDto petDto, BindingResult result) {

        try {
            Pet pet;

            if (id != null) {
                pet = petServiceImp.getPetById(id);
            } else {
                Date date = new Date();
                pet = new Pet();
                pet.setCreatedAt(date);
            }

            pet.setUser(userServiceImpl.getSessionUser());
            pet.setPetName(petDto.getPetName());
            pet.setPetType(petDto.getPetType());
            pet.setBreed(petDto.getBreed());
            pet.setGender(petDto.getGender());
            pet.setReproductiveOrgans(petDto.getReproductiveOrgans());
            pet.setAge(petDto.getAge());
            pet.setWeight(petDto.getWeight());
            petServiceImp.savePet(pet);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/pet/listedPets";
        }

        return "user/pets_form";
    }

}
