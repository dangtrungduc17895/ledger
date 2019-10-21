package com.pet.ledger.response;

import com.pet.ledger.constant.DateTimeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class BaseResponse {
    private long currentTime;

    public BaseResponse() {
        this.currentTime = DateTimeConstant.CURRENT_TIME_LONG_TYPE;
    }
}
