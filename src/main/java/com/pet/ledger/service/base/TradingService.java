package com.pet.ledger.service.base;

import com.pet.ledger.model.type.Trading;
import com.pet.ledger.model.type.User;
import com.pet.ledger.service.Service;

import java.util.List;

public interface TradingService<E extends Trading> extends Service<E> {

    List<Trading> getTradingListByUser(User user);

    int getRequestTotal(User user, Integer type, String time);

    int getSendTotal(User user, Integer type, String time);
}
