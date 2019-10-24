package com.pet.ledger.response.type.statistic;

import com.pet.ledger.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class TradingStatisticsResponse extends BaseResponse {

    Float requets;
    Float sents;
}
