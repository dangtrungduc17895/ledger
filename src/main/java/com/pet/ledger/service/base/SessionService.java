package com.pet.ledger.service.base;

import com.pet.ledger.model.type.Session;
import com.pet.ledger.service.Service;

public interface SessionService extends Service<Session> {
    boolean isExpiredSessionById(String id);
    void updateExpiredSession(String id);
    void deleteAllByExpiredSessionLessThan(long currentTime);
}
