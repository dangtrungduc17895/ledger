package com.pet.ledger.controller.subcontroller;

import com.pet.ledger.controller.BaseController;
import com.pet.ledger.model.type.Trading;
import com.pet.ledger.model.type.User;
import com.pet.ledger.request.type.trading.CreateTradingRequest;
import com.pet.ledger.response.ResponseModel;
import com.pet.ledger.response.type.trading.CreateTradingResponse;
import com.pet.ledger.response.type.trading.TradingListResponse;
import com.pet.ledger.response.type.trading.TradingResponse;
import com.pet.ledger.service.base.SessionService;
import com.pet.ledger.service.base.TradingService;
import com.pet.ledger.utils.ModelMapperUtils;
import com.pet.ledger.utils.ResponseUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/tradings")
public class TradingController extends BaseController {

    @Autowired
    private TradingService tradingService;
    @Autowired
    private SessionService sessionService;

    @PostMapping()
    public ResponseEntity<ResponseModel> insertTrading(@RequestHeader("token")String token,
                                                       @RequestBody CreateTradingRequest createTradingRequest) {
        User user = sessionService.getEntityById(token).getUser();
        System.out.println("LALALALA1");
        Trading trading = ModelMapperUtils.transferObject(createTradingRequest, Trading.class);
        System.out.println("LALALALA2");
        trading.setUser(user);
        System.out.println("LALALALA3");
        tradingService.insert(trading);
        System.out.println("LALALALA4");
        return ResponseUtils.buildResponseEntity(new CreateTradingResponse(), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<ResponseModel> getTradingList(@RequestHeader("token")String token) {

        User user = sessionService.getEntityById(token).getUser();
        List<Trading> tradings = tradingService.getTradingListByUser(user);
        List<TradingResponse> tradingResponses = ModelMapperUtils.transferListObject(tradings, TradingResponse.class);
        TradingListResponse tradingListResponse = new TradingListResponse(tradingResponses);
        return ResponseUtils.buildResponseEntity(tradingListResponse, HttpStatus.OK);
    }

}