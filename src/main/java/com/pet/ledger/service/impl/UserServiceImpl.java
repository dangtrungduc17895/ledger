package com.pet.ledger.service.impl;

import com.pet.ledger.constant.MessageConstant;
import com.pet.ledger.exceptionhandler.exception.MyException;
import com.pet.ledger.model.type.User;
import com.pet.ledger.repository.UserRepository;
import com.pet.ledger.service.base.UserService;
import com.pet.ledger.utils.ValidateUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

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
    public boolean insert(User user) {
        if (ValidateUtils.isObjectNullOrEmpty(user)){
            return false;
        }
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean updateById(String id, User entity) {

        userRepository.findById(id).map(user -> {
            user.setName(entity.getName());
            user.setSkype(entity.getSkype());
            user.setPhoneNumber(entity.getPhoneNumber());
            user.setPicture(entity.getPicture());
            return userRepository.save(user);
        }).orElseThrow(()-> new MyException("User not find by id"));

        return true;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    @Override
    public User getEntityById(String id) {

        if (ValidateUtils.isStringNullOrEmpty(id)) {
            throw new MyException(MessageConstant.ENTITY_NOT_FOUND);
        }

        return userRepository.findById(id).orElse(null);
    }
}
