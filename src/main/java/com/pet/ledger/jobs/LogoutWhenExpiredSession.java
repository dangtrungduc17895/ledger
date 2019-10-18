package com.pet.ledger.jobs;

import com.pet.ledger.constant.DateTimeConstant;
import com.pet.ledger.service.base.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class LogoutWhenExpiredSession {
    private final SessionService sessionService;

    @Autowired
    public LogoutWhenExpiredSession(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Scheduled(fixedRate = DateTimeConstant.TIME_CHECK_EXPIRED)
    public void autoLogout() {
        sessionService.deleteAllByExpiredSessionLessThan(DateTimeConstant.CURRENT_TIME_LONG_TYPE);
    }

}
