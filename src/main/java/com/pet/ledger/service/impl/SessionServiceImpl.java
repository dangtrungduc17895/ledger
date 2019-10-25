package com.pet.ledger.service.impl;

import com.pet.ledger.constant.DateTimeConstant;
import com.pet.ledger.model.type.Session;
import com.pet.ledger.repository.SessionRepository;
import com.pet.ledger.service.BaseService;
import com.pet.ledger.service.base.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class SessionServiceImpl extends BaseService<Session> implements SessionService<Session> {

    private final SessionRepository sessionRepository;

    @Override
    public boolean isExpiredSessionById(String id) {
        return sessionRepository.findById(id).isPresent();
    }

    @Override
    public void updateExpiredSession(String id) {
        Session session = sessionRepository.findById(id).orElse(null);
        assert session != null;
        session.setExpiredSession(DateTimeConstant.EXPIRED_SESSION);
        sessionRepository.save(session);
    }

    @Override
    public void deleteAllByExpiredSessionLessThan(long currentTime) {
        sessionRepository.deleteAllByExpiredSessionLessThan(currentTime);
    }
}
