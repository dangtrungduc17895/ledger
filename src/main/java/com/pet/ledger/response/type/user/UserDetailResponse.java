package com.pet.ledger.response.type.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pet.ledger.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDetailResponse extends BaseResponse {
    private String email;

    private String name;

    @JsonProperty("give_name")
    private String giveName;

    @JsonProperty("family_name")
    private String familyName;

    private String picture;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String skype;
}
