package com.pet.ledger.response.type.user;

import com.pet.ledger.model.dto.UserDTO;
import com.pet.ledger.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserResponse extends BaseResponse {
    private UserDTO user;
}
