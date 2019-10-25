package com.pet.ledger.response.type.user;

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
    private String giveName;
    private String familyName;
    private String picture;
    private String phoneNumber;
    private String skype;
}
