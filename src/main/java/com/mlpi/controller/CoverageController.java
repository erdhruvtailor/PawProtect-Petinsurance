package com.mlpi.controller;

import com.mlpi.dto.CoverageDto;
import com.mlpi.model.Coverage;
import com.mlpi.service.CoverageServiceImp;
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
@RequestMapping("/coverage")
public class CoverageController {

    @Autowired
    CoverageServiceImp coverageServiceImp;
    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping("/activeCovrage")
    public String activeCovrage(Model model) {

        try {

            List<Coverage> coverageList = coverageServiceImp.getCoverageByUser(userServiceImpl.getSessionUser().getId());
            model.addAttribute("coverageList", coverageList);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/coverage/buyInsurance/";
        }
        return "user/active_coverage";
    }

    @GetMapping("/buyInsurance")
    public String buyInsurance(Model model, @RequestParam(value = "id", required = false) Long id) {

        try {
            CoverageDto coverageDto = new CoverageDto();
            if (id != null) {
                Coverage coverage = coverageServiceImp.getCoverageById(id);
                coverageDto.setPetName(coverage.getPetName());
                coverageDto.setPetType(coverage.getPetType());
                coverageDto.setBreed(coverage.getBreed());
                coverageDto.setGender(coverage.getGender());
                coverageDto.setReproductiveOrgans(coverage.getReproductiveOrgans());
                coverageDto.setAge(coverage.getAge());
                coverageDto.setWeight(coverage.getWeight());
                coverageDto.setCoverage(coverage.getCoverage());
                coverageDto.setGrandTotal(coverage.getGrandTotal());
                coverageDto.setAnnualLimit(coverage.getAnnualLimit());
                coverageDto.setReimbursementRate(coverage.getReimbursementRate());
                coverageDto.setAnnualDeductible(coverage.getAnnualDeductible());
            }

            System.out.print(coverageDto);

            model.addAttribute("coverageDto", coverageDto);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/coverage/buyInsurance/";
        }

        return "user/buy_insurance";
    }

    @PostMapping("/buyInsurance")
    public String buyInsuranceUpdate(Model model, @RequestParam(value = "id", required = false) Long id, @Valid @ModelAttribute CoverageDto coverageDto, BindingResult result) {

        try {

            Coverage coverage;

            if (id != null) {
                coverage = coverageServiceImp.getCoverageById(id);
            } else {
                Date date = new Date();
                coverage = new Coverage();
                coverage.setBuyDate(date);
            }

            System.out.println(userServiceImpl.getSessionUser());
            coverage.setUser(userServiceImpl.getSessionUser());
            coverage.setPetName(coverageDto.getPetName());
            coverage.setPetType(coverageDto.getPetType());
            coverage.setBreed(coverageDto.getBreed());
            coverage.setGender(coverageDto.getGender());
            coverage.setReproductiveOrgans(coverageDto.getReproductiveOrgans());
            coverage.setAge(coverageDto.getAge());
            coverage.setWeight(coverageDto.getWeight());
            coverage.setCoverage(coverageDto.getCoverage());
            coverage.setGrandTotal(coverageDto.getGrandTotal());
            coverage.setAnnualLimit(coverageDto.getAnnualLimit());
            coverage.setReimbursementRate(coverageDto.getReimbursementRate());
            coverage.setAnnualDeductible(coverageDto.getAnnualDeductible());

            coverageServiceImp.saveCoverage(coverage);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/coverage/buyInsurance";
        }

        return "user/buy_insurance";
    }

}
