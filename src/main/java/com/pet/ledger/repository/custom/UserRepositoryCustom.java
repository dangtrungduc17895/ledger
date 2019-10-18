package com.pet.ledger.repository.custom;


import com.pet.ledger.model.type.User;

import java.io.Serializable;
import java.util.List;

@FunctionalInterface
public interface UserRepositoryCustom extends Serializable {
    List<User> findUserByEmail(String email);
}
