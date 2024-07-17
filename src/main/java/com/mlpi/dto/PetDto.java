package com.mlpi.dto;

import com.mlpi.model.User;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PetDto {
    private Long id;
    private User user;
    private String petName;
    private String petType;
    private String breed;
    private String gender;
    private String reproductiveOrgans;
    private String age;
    private String weight;
    private Date createdAt;
}
