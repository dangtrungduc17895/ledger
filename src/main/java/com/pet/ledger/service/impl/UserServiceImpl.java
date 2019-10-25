package com.pet.ledger.service.impl;

import com.pet.ledger.constant.MessageConstant;
import com.pet.ledger.exceptionhandler.exception.MyException;
import com.pet.ledger.model.type.User;
import com.pet.ledger.repository.UserRepository;
import com.pet.ledger.service.BaseService;
import com.pet.ledger.service.base.UserService;
import com.pet.ledger.utils.ValidateUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class UserServiceImpl extends BaseService<User> implements UserService<User> {

    private final UserRepository userRepository;
    private final String pathParent = "C:\\Users\\NTQ\\Desktop\\file";

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
        if (user.getAmount() == null) {
            user.setAmount(0.0f);
        }
        Float newAmount = user.getAmount() + moneyChanges;
        user.setAmount(newAmount);
        userRepository.save(user);
    }

    @Override
    public String updateAvatar(String userId, MultipartFile fileImage) {
        try{
            File file = new File(pathParent, fileImage.getOriginalFilename());
            file.getParentFile().mkdirs();
            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream boss = new BufferedOutputStream(fos);
            boss.write(fileImage.getBytes());
            boss.flush();
            boss.close();

            userRepository.findById(userId).map(user -> {
                user.setPicture(file.getPath());
                return userRepository.save(user);
            }).orElseThrow(()-> new MyException("Save avatar error!"));;

            return file.getPath();

        } catch (FileNotFoundException ex){
            log.info(ex.toString());
        } catch (IOException ex){
            log.info(ex.toString());
        }

        return " ácc";
    }

    @Override
    public void updateById(String id, User entity) {
        userRepository.findById(id).map(user -> {
            user.setName(entity.getName());
            user.setSkype(entity.getSkype());
            user.setPhoneNumber(entity.getPhoneNumber());
            user.setFamilyName(entity.getFamilyName());
            user.setEmail(entity.getEmail());
            return userRepository.save(user);
        }).orElseThrow(()-> new MyException("User not find by id"));
    }

}
