package com.pet.ledger.response.type.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pet.ledger.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 *
 * @author SonNX
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserResponse extends BaseResponse {
    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

    @JsonProperty("family_name")
    private String familyName;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("skype")
    private String skype;
}
