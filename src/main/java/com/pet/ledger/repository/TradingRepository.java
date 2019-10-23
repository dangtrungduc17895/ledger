package com.pet.ledger.repository;

import com.pet.ledger.model.type.Trading;
import com.pet.ledger.model.type.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TradingRepository extends  ModelRepository<Trading> {

    List<Trading> getAllByUser(User user);

}
