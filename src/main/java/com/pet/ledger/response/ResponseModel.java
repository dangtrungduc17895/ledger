package com.pet.ledger.response;

import com.pet.ledger.constant.CodeResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseModel {
    private int code;
    private BaseResponse data;

    public ResponseModel() {
        this.code = CodeResponse.SUCCESS_CODE.getCode();
    }

    public ResponseModel(CodeResponse codeResponse) {
        this.code = codeResponse.getCode();
    }

    public ResponseModel(int code) {
        this.code = code;
    }

    public ResponseModel(BaseResponse data) {
        this.code = CodeResponse.SUCCESS_CODE.getCode();
        this.data = data;
    }
}
