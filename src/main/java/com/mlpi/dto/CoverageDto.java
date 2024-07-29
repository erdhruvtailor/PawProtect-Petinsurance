package com.mlpi.dto;

import com.mlpi.model.Pet;
import com.mlpi.model.User;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoverageDto {
    private Long id;
    private Long policyNumber;
    private User user;
    private Pet pet;
    private Integer policyPeriod;
    private String coverage;
    private Double grandTotal;
    private String annualLimit;
    private String reimbursementRate;
    private String annualDeductible;
}
