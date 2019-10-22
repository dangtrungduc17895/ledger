package com.pet.ledger.interception;

import com.pet.ledger.service.base.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
class RequestValidator {

    private final SessionService sessionService;

    @Autowired
    public RequestValidator(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    boolean isValid(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (sessionService.isExpiredSessionById(token)) {
            sessionService.updateExpiredSession(token);
            return true;
        }
        return false;
    }
}
