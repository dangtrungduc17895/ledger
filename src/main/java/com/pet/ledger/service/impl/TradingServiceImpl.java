package com.pet.ledger.service.impl;

import com.pet.ledger.model.type.Trading;
import com.pet.ledger.model.type.User;
import com.pet.ledger.repository.TradingRepository;
import com.pet.ledger.service.BaseService;
import com.pet.ledger.service.base.TradingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class TradingServiceImpl extends BaseService<Trading> implements TradingService<Trading> {

    private TradingRepository tradingRepository;

    @Override
    public List<Trading> getTradingListByUser(User user) {
        return tradingRepository.getAllByUser(user);
    }
}
