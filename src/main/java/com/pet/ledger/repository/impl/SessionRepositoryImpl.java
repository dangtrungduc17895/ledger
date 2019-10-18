package com.pet.ledger.repository.impl;

import com.pet.ledger.repository.custom.SessionRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@AllArgsConstructor
public class SessionRepositoryImpl implements SessionRepositoryCustom {
}
