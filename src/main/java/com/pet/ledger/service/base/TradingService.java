package com.pet.ledger.service.base;

import com.pet.ledger.model.type.Trading;
import com.pet.ledger.model.type.User;
import com.pet.ledger.service.Service;

import java.util.List;
import java.util.Map;

public interface TradingService<E extends Trading> extends Service<E> {

    List<Trading> getTradingListByUser(User user);

    Map<String, Float> getStatisticByUser(User user, int type, long time);
}
