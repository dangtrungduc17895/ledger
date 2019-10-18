package com.pet.ledger.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class ErrorResponse {
    private int code;
    private ErrorFormat data;
    private String message;

}
