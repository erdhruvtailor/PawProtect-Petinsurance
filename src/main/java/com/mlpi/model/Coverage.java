package com.mlpi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "coverages")
public class Coverage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(length = 30)
    private String petType;

    @Column(length = 30)
    private String petName;

    @Column(length = 30)
    private String breed;

    @Column(length = 6)
    private String gender;

    @Column(length = 20)
    private String reproductiveOrgans;

    @Column(length = 3)
    private String age;

    @Column(length = 30)
    private String weight;

    @Column(length = 30)
    private String coverage;

    private Double grandTotal;

    private String annualLimit;

    @Column(length = 4)
    private String reimbursementRate;

    @Column(length = 4)
    private String annualDeductible;

    private Date buyDate;
}
