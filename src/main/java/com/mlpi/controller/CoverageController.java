package com.mlpi.controller;

import com.mlpi.dto.CoverageDto;
import com.mlpi.dto.PayPolicyDto;
import com.mlpi.model.Coverage;
import com.mlpi.model.PayPolicy;
import com.mlpi.model.Pet;
import com.mlpi.service.CoverageServiceImp;
import com.mlpi.service.PayPolicyServiceImp;
import com.mlpi.service.PetServiceImp;
import com.mlpi.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/policy")
public class CoverageController {

    @Autowired
    CoverageServiceImp coverageServiceImp;
    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    PetServiceImp petServiceImp;
    @Autowired
    private PayPolicyServiceImp payPolicyServiceImp;

    @GetMapping("/activeCovrage")
    public String activeCovrage(Model model) {

        try {

            List<Coverage> coverageList = coverageServiceImp.getCoverageByUser(userServiceImpl.getSessionUser().getId());
            model.addAttribute("coverageList", coverageList);
            model.addAttribute("username", userServiceImpl.getSessionUser().getName());

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/policy/activeCovrage/";
        }
        return "user/active_coverage";
    }

    @GetMapping("/buyInsurance")
    public String buyInsurance(Model model, @RequestParam(value = "id", required = false) Long id) {

        try {
            CoverageDto coverageDto = new CoverageDto();
            if (id != null) {
                Coverage coverage = coverageServiceImp.getCoverageById(id);
                /*coverageDto.setPetName(coverage.getPetName());
                coverageDto.setPetType(coverage.getPetType());
                coverageDto.setBreed(coverage.getBreed());
                coverageDto.setGender(coverage.getGender());
                coverageDto.setReproductiveOrgans(coverage.getReproductiveOrgans());
                coverageDto.setAge(coverage.getAge());
                coverageDto.setWeight(coverage.getWeight());*/
                coverageDto.setCoverage(coverage.getCoverage());
                coverageDto.setGrandTotal(coverage.getGrandTotal());
                coverageDto.setAnnualLimit(coverage.getAnnualLimit());
                coverageDto.setReimbursementRate(coverage.getReimbursementRate());
                coverageDto.setAnnualDeductible(coverage.getAnnualDeductible());
            }


            List<Pet> petsList = petServiceImp.getPetByUser(userServiceImpl.getSessionUser().getId());
            model.addAttribute("petsList", petsList);
            model.addAttribute("coverageDto", coverageDto);
            model.addAttribute("username", userServiceImpl.getSessionUser().getName());

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/policy/buyInsurance/";
        }

        return "user/buy_insurance";
    }

    @PostMapping("/buyInsurance")
    public String buyInsuranceUpdate(Model model, @RequestParam(value = "id", required = false) Long id, @Valid @ModelAttribute CoverageDto coverageDto, BindingResult result) {

        try {

            Coverage coverage;
            Date date = new Date();

            if (id != null) {
                coverage = coverageServiceImp.getCoverageById(id);
                coverage.setUpdateDate(date);
            } else {
                coverage = new Coverage();
                coverage.setBuyDate(date);
                coverage.setPolicyNumber(this.getRandomNumber());
                coverage.setPolicyPeriod(coverageDto.getPolicyPeriod());
            }

            coverage.setUser(userServiceImpl.getSessionUser());
            coverage.setPet(coverageDto.getPet());
            /*coverage.setPetName(coverageDto.getPetName());
            coverage.setPetType(coverageDto.getPetType());
            coverage.setBreed(coverageDto.getBreed());
            coverage.setGender(coverageDto.getGender());
            coverage.setReproductiveOrgans(coverageDto.getReproductiveOrgans());
            coverage.setAge(coverageDto.getAge());
            coverage.setWeight(coverageDto.getWeight());*/
            coverage.setCoverage(coverageDto.getCoverage());
            coverage.setGrandTotal(coverageDto.getGrandTotal());
            coverage.setAnnualLimit(coverageDto.getAnnualLimit());
            coverage.setReimbursementRate(coverageDto.getReimbursementRate());
            coverage.setAnnualDeductible(coverageDto.getAnnualDeductible());

            coverageServiceImp.saveCoverage(coverage);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/policy/buyInsurance";
        }

        return "redirect:/policy/activeCovrage";
    }

    @GetMapping("/payInsurance")
    public String payInsurance(Model model, @RequestParam(value = "id", required = false) Long id) {

        try {
            PayPolicyDto payPolicyDto = new PayPolicyDto();
            Coverage policy = coverageServiceImp.getCoverageById(id);
            model.addAttribute("policyInfo", policy);
            model.addAttribute("payPolicyDto", payPolicyDto);
            model.addAttribute("username", userServiceImpl.getSessionUser().getName());

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/policy/activeCovrage/";
        }
        return "user/pay_insurance";
    }

    @PostMapping("/payInsurance")
    public String payInsurancePost(Model model, @RequestParam(value = "id", required = false) Long id, @Valid @ModelAttribute PayPolicyDto payPolicyDto, BindingResult result) {

        try {
            Coverage policy = coverageServiceImp.getCoverageById(id);
            PayPolicy payPolicy = new PayPolicy();

            payPolicy.setPolicyNumber(policy);
            payPolicy.setUser(policy.getUser());
            payPolicy.setPet(policy.getPet());
            payPolicy.setAmount(payPolicyDto.getAmount());
            Date date = new Date();
            payPolicy.setPayDate(date);
            payPolicy.setStatus("Complete");

            payPolicyServiceImp.savePayPolicy(payPolicy);

        } catch (Exception e) {
            System.out.println("Ex  ception: " + e.getMessage());
            return "redirect:/policy/buyInsurance";
        }

        return "redirect:/policy/paidBillList";
    }

    @GetMapping("/paidBillList")
    public String paidBillList(Model model) {

        try {

            List<PayPolicy> payPolicyList = payPolicyServiceImp.getPayByUser(userServiceImpl.getSessionUser().getId());
            model.addAttribute("payPolicyList", payPolicyList);
            model.addAttribute("username", userServiceImpl.getSessionUser().getName());

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/policy/activeCovrage/";
        }
        return "user/paid_bill_list";
    }

    @GetMapping("/getMapperAttributes")
    public ResponseEntity<List<CoverageDto>> getMapperAttributes() {
        List<CoverageDto> coverageDTOs = coverageServiceImp.getCoverageDTOs(userServiceImpl.getSessionUser().getId());
        return new ResponseEntity<>(coverageDTOs, HttpStatus.OK);
    }

    public long getRandomNumber() {
        Random random = new Random();
        long randomNumber = (long) (Math.pow(10, 9) + random.nextDouble() * (Math.pow(10, 10) - Math.pow(10, 9)));
        return randomNumber;
    }
}
