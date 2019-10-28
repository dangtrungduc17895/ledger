package com.pet.ledger.response.type.session;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("token_session")
    private String tokenSession;

}
