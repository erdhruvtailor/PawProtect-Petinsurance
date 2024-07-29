package com.mlpi.controller;

import com.mlpi.dto.CoverageDto;
import com.mlpi.model.Coverage;
import com.mlpi.model.Pet;
import com.mlpi.service.CoverageServiceImp;
import com.mlpi.service.PayPolicyServiceImp;
import com.mlpi.service.PetServiceImp;
import com.mlpi.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    CoverageServiceImp coverageServiceImp;
    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    PetServiceImp petServiceImp;

    @Autowired
    PayPolicyServiceImp payPolicyServiceImp;

    @GetMapping("/")
    public String dashboard(Model model) {

        try {
            List<Coverage> coverageList = coverageServiceImp.getCoverageByUser(userServiceImpl.getSessionUser().getId());
            Long totalPets = petServiceImp.getPetsCountByUser(userServiceImpl.getSessionUser().getId());
            Long totalCoverages = coverageServiceImp.getCoveragesCountByUser(userServiceImpl.getSessionUser().getId());
            Double sumUserWiseGrandTotal = coverageServiceImp.sumUserWiseGrandTotal(userServiceImpl.getSessionUser().getId());
            Double sumTotalPaid = payPolicyServiceImp.sumUserWisePay(userServiceImpl.getSessionUser().getId());

            model.addAttribute("coverageList", coverageList);
            model.addAttribute("totalPets", totalPets);
            model.addAttribute("totalCoverages", totalCoverages);
            model.addAttribute("sumTotalPaid", sumTotalPaid);
            model.addAttribute("sumUserWiseGrandTotal", sumUserWiseGrandTotal);
            model.addAttribute("username", userServiceImpl.getSessionUser().getName());

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return "redirect:/coverage/buyInsurance/";
        }
        return "user/user_dashboard";
    }

}
