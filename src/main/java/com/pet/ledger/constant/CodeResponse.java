package com.pet.ledger.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public enum CodeResponse {

    SUCCESS_CODE(1,"Success"),
    FAIL_CODE(0,"Failed"),
    EXPIRED_CODE(-1,"Expired session");

    private int code;
    private String message;

}
