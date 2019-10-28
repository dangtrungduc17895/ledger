package com.pet.ledger.response.type.user;

import com.pet.ledger.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse extends BaseResponse {
    private String name;
    private String picture;
}
