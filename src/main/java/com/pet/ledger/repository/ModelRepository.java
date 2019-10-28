package com.pet.ledger.repository;

import com.pet.ledger.model.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ModelRepository<T extends BaseModel> extends JpaRepository<T, String> {
}
