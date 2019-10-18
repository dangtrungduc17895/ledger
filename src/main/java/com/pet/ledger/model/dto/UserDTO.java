package com.pet.ledger.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String userName;
    private String userPicture;
    private String userPhoneNumber;
    private String userSkype;
    private String userEmail;
}
