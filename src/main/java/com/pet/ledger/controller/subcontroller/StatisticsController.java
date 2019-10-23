package com.pet.ledger.controller.subcontroller;

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

@RestController
@AllArgsConstructor
@RequestMapping("/v1/statistics")
public class StatisticsController extends BaseController {

    @Autowired
    private TradingService<Trading> tradingService;

    @GetMapping()
    public ResponseEntity<ResponseModel> getTradingStatistics(@RequestHeader("token")String token,
                                                              @RequestParam("type")Integer type,
                                                              @RequestParam("time")String time) {

        User user = getUserFromTokenSession(token);
        int requests = tradingService.getRequestTotal(user, type, time);
        int sends = tradingService.getSendTotal(user, type, time);
        TradingStatisticsResponse tradingStatisticsResponse = new TradingStatisticsResponse(requests, sends);
        return ResponseUtils.buildResponseEntity(tradingStatisticsResponse, HttpStatus.OK);
    }
}
