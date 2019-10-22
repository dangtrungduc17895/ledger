package com.pet.ledger.request.type.trading;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pet.ledger.request.BaseRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateTradingRequest extends BaseRequest {

    @JsonProperty("money_changes")
    private float moneyChanges;

    @JsonProperty("purpose_type")
    private int purposeType;

    @JsonProperty("time")
    private long time;

    @JsonProperty("trading_type")
    private int tradingType;
}
