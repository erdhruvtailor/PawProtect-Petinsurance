package com.mlpi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "pets")
public class Pet {

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

    private Date createdAt;
}
