package com.pet.ledger.controller;

import com.pet.ledger.constant.CodeResponse;
import com.pet.ledger.model.type.Session;
import com.pet.ledger.model.type.User;
import com.pet.ledger.response.ResponseModel;
import com.pet.ledger.service.base.SessionService;
import com.pet.ledger.service.base.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Getter
public abstract class BaseController {

    protected static final ResponseEntity<ResponseModel> NOT_CAPTAIN_COURSE = new ResponseEntity<>(new ResponseModel(
            CodeResponse.FAIL_CODE.getCode()), HttpStatus.OK);

    protected static final ResponseEntity<ResponseModel> ENTITY_NOT_FOUND = new ResponseEntity<>(new ResponseModel(
            CodeResponse.FAIL_CODE.getCode()), HttpStatus.OK);

    @Autowired
    protected SessionService<Session> sessionService;

    @Autowired
    protected UserService<User> userService;

    protected  String getUserIdFromTokenSession(String tokenSession) {
        Session session = sessionService.getEntityById(tokenSession);
        return session.getUser().getId();
    }

    protected User getUserFromTokenSession(String tokenSession) {
        Session session = sessionService.getEntityById(tokenSession);
        return session.getUser();
    }
}
