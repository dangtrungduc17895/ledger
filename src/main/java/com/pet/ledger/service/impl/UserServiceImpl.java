package com.pet.ledger.service.impl;

import com.pet.ledger.constant.MessageConstant;
import com.pet.ledger.exceptionhandler.exception.MyException;
import com.pet.ledger.model.type.User;
import com.pet.ledger.repository.UserRepository;
import com.pet.ledger.service.BaseService;
import com.pet.ledger.request.type.user.EditUserRequest;
import com.pet.ledger.service.base.UserService;
import com.pet.ledger.utils.ValidateUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl extends BaseService<User> implements UserService<User> {

    private final UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {

        if (ValidateUtils.isStringNullOrEmpty(email)) {
            throw new MyException(MessageConstant.ENTITY_NOT_FOUND);
        }

        return userRepository.findAllByEmail(email);
    }

    @Override
    public List<User> findBySearchTerm(String searchTerm) {
        return userRepository.findUserByEmail(searchTerm);
    }

    @Override
    public List<User> listUserByCourse(String courseId, String email) {
        return userRepository.listUserByCourse(courseId,email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void changeAmountUser(User user, float moneyChanges) {
        if (user.getAmount()==null) {
            user.setAmount(0.0f);
        }
        Float newAmount = user.getAmount() + moneyChanges;
        user.setAmount(newAmount);
        userRepository.save(user);
    }


//    @Override
//    public boolean updateById(String id, User entity) {
//        return true;
//    }

//    @Override
    public boolean updateById(String id, EditUserRequest entity) {

        userRepository.findById(id).map(user -> {
            user.setName(entity.getUserName());
            user.setSkype(entity.getUserSkype());
            user.setPhoneNumber(entity.getUserPhoneNumber());
            user.setPicture(entity.getUserPicture());
            return userRepository.save(user);
        }).orElseThrow(()-> new MyException("User not find by id"));

        return true;
    }

}
