package com.pet.ledger.repository;

import com.pet.ledger.model.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository<T extends BaseModel> extends JpaRepository<T, String> {
}
