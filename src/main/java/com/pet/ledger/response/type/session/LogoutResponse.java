package com.pet.ledger.response.type.session;

import com.pet.ledger.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogoutResponse extends BaseResponse {
    private String userName;
    private String tokenSession;

}
