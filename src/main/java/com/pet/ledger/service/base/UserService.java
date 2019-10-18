package com.pet.ledger.service.base;


import com.pet.ledger.model.type.User;
import com.pet.ledger.service.Service;

import java.util.List;

public interface UserService extends Service<User> {
    User getUserByEmail(String email);
    List<User> findBySearchTerm(String searchTerm);
    List<User> listUserByCourse(String courseId, String email);
    List<User> findAll();
}
