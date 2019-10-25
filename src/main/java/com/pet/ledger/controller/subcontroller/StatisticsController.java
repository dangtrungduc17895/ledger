package com.pet.ledger.controller.subcontroller;

import com.pet.ledger.constant.TypeConstant;
import com.pet.ledger.controller.BaseController;
import com.pet.ledger.model.type.Trading;
import com.pet.ledger.model.type.User;
import com.pet.ledger.response.ResponseModel;
import com.pet.ledger.response.type.statistic.TradingStatisticsResponse;
import com.pet.ledger.service.base.TradingService;
import com.pet.ledger.utils.ResponseUtils;
import com.pet.ledger.utils.ValidateUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.ManyToOne;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/statistics")
public class StatisticsController extends BaseController {

    @Autowired
    private TradingService<Trading> tradingService; 

    @GetMapping()
    public ResponseEntity<ResponseModel> getTradingStatistics(@RequestHeader("token")String token,
                                                              @RequestParam("type")Integer type,
                                                              @RequestParam("time")Long time) {

        User user = getUserFromTokenSession(token);
        Map<String, Float> statisticTrading = tradingService.getStatisticByUser(user, type, time);
        float requests = statisticTrading.get(TypeConstant.REQUEST_TRADING_KEY);
        float sents = statisticTrading.get(TypeConstant.SENT_TRADING_KEY);
        TradingStatisticsResponse tradingStatisticsResponse = new TradingStatisticsResponse(requests, sents);
        return ResponseUtils.buildResponseEntity(tradingStatisticsResponse, HttpStatus.OK);
    }
}
