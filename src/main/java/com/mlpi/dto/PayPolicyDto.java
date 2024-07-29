package com.mlpi.dto;

import com.mlpi.model.Coverage;
import com.mlpi.model.Pet;
import com.mlpi.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PayPolicyDto {
    private Long id;
    private Coverage policyNumber;
    private User user;
    private Pet pet;
    private Double amount;
    private Date payDate;
    private String status;
}
