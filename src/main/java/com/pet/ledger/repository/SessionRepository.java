package com.pet.ledger.repository;

import com.pet.ledger.model.type.Session;
import com.pet.ledger.repository.custom.SessionRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SessionRepository extends ModelRepository<Session>, SessionRepositoryCustom {
    void deleteAllByExpiredSessionLessThan(long currentTime);
}
