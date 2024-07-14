package com.mlpi.dto;

import com.mlpi.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {
    private Long userProfileId;
    private User user;
    /*private String birthdate;
    private String gender;*/
    private String mobileNumber;
    private String address;
    private String city;
    private String zipCode;
    private String province;
    private String country;

}
