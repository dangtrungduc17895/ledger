package com.pet.ledger.service.impl;

import com.pet.ledger.constant.DateTimeConstant;
import com.pet.ledger.constant.MessageConstant;
import com.pet.ledger.exceptionhandler.exception.MyException;
import com.pet.ledger.model.type.Session;
import com.pet.ledger.repository.SessionRepository;
import com.pet.ledger.service.base.SessionService;
import com.pet.ledger.utils.ValidateUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;


    @Override
    public boolean insert(Session entity) {
        sessionRepository.save(entity);
        return true;
    }

    @Override
    public boolean updateById(String id, Session session) {
        return false;
    }


    @Override
    public boolean deleteById(String id) {
        if (ValidateUtils.isStringNullOrEmpty(id)){
            throw new MyException(MessageConstant.ENTITY_NOT_FOUND);
        }
        sessionRepository.deleteById(id);
        return true;
    }

    @Override
    public Session getEntityById(String id) {
        if (ValidateUtils.isStringNullOrEmpty(id)){
            throw new MyException(MessageConstant.SESSION_NOT_FOUND);
        }
        return sessionRepository.findById(id).orElse(null);
    }

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
