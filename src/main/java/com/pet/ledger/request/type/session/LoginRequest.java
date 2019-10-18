package com.pet.ledger.request.type.session;

import com.pet.ledger.request.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest extends BaseRequest {

    @NotNull
    @NotBlank
    private String token;
}
