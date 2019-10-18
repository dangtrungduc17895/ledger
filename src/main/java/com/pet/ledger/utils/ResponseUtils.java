package com.pet.ledger.utils;

import com.pet.ledger.response.BaseResponse;
import com.pet.ledger.response.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {

    private ResponseUtils() {}

    public static<T extends BaseResponse> ResponseEntity<ResponseModel> buildResponseEntity(T t, HttpStatus status) {
        return new ResponseEntity<>(new ResponseModel(t), status);
    }


}
