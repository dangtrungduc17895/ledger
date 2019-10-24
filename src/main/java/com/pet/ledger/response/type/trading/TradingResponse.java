package com.pet.ledger.response.type.trading;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pet.ledger.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradingResponse extends BaseResponse {

    @JsonProperty("trading_id")
    private String tradingId;

    @JsonProperty("money_changes")
    private float moneyChanges;

    @JsonProperty("time")
    private long time;

    @JsonProperty("purpose_type")
    private int purposeType;

    @JsonProperty("trading_type")
    private int tradingType;
}
