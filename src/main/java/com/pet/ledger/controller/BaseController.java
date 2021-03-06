package com.pet.ledger.controller;

import com.pet.ledger.constant.CodeResponse;
import com.pet.ledger.constant.MessageConstant;
import com.pet.ledger.model.BaseModel;
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

import java.util.List;

@RestController
@Getter
public abstract class BaseController {

    protected static final ResponseEntity<ResponseModel> NOT_CAPTAIN_COURSE = new ResponseEntity<>(new ResponseModel(
            CodeResponse.FAIL_CODE.getCode(), MessageConstant.NOT_CAPTAIN_COURSE), HttpStatus.OK);

    protected static final ResponseEntity<ResponseModel> ENTITY_NOT_FOUND = new ResponseEntity<>(new ResponseModel(
            CodeResponse.FAIL_CODE.getCode(), MessageConstant.ENTITY_NOT_FOUND), HttpStatus.OK);



    @Autowired
    protected SessionService sessionService;

    @Autowired
    protected UserService userService;




    protected  String getUserIdFromTokenSession(String tokenSession) {
        Session session = sessionService.getEntityById(tokenSession);
        return session.getUser().getId();
    }

    protected User getUserFromTokenSession(String tokenSession) {
        Session session = sessionService.getEntityById(tokenSession);
        return session.getUser();
    }

}
