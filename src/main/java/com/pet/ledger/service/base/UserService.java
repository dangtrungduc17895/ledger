package com.pet.ledger.service.base;

import com.pet.ledger.model.type.User;
import com.pet.ledger.service.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService<E extends User> extends Service<E> {
    User getUserByEmail(String email);
    List<User> findBySearchTerm(String searchTerm);
    List<User> listUserByCourse(String courseId, String email);
    List<User> findAll();
    void changeAmountUser(User user, float moneyChanges);
    String updateAvatar(String userId, MultipartFile fileImage);
}
