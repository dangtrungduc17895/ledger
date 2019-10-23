package com.pet.ledger.response.type.statistic;

import com.pet.ledger.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TradingStatisticsResponse extends BaseResponse {

    Integer requets;
    Integer sends;
}
