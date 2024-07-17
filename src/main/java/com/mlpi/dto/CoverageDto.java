package com.mlpi.dto;

import com.mlpi.model.User;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CoverageDto {
    private Long id;
    private User user;
    private String petName;
    private String petType;
    private String breed;
    private String gender;
    private String reproductiveOrgans;
    private String age;
    private String weight;
    private String coverage;
    private Double grandTotal;
    private String annualLimit;
    private String reimbursementRate;
    private String annualDeductible;
}
