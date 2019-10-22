package com.pet.ledger.response.type.trading;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pet.ledger.response.BaseResponse;
import com.pet.ledger.response.ResponseModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TradingListResponse extends BaseResponse {

    @JsonProperty("trading_list")
    private List<TradingResponse> tradingResponses;
}
