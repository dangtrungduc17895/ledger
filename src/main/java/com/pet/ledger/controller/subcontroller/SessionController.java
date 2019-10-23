package com.pet.ledger.controller.subcontroller;

import com.pet.ledger.constant.CodeResponse;
import com.pet.ledger.constant.FormatConstant;
import com.pet.ledger.exceptionhandler.exception.MyException;
import com.pet.ledger.model.type.GoogleUser;
import com.pet.ledger.model.type.Session;
import com.pet.ledger.model.type.User;
import com.pet.ledger.request.type.session.LoginRequest;
import com.pet.ledger.response.ResponseModel;
import com.pet.ledger.response.type.session.LoginResponse;
import com.pet.ledger.response.type.session.LogoutResponse;
import com.pet.ledger.service.base.GoogleUserService;
import com.pet.ledger.service.base.SessionService;
import com.pet.ledger.service.base.UserService;
import com.pet.ledger.utils.ResponseUtils;
import com.pet.ledger.utils.ValidateUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1/sessions")
public class SessionController  {

    protected UserService userService;
    protected SessionService sessionService;

    private final GoogleUserService googleUserService;

    @PostMapping()
    public ResponseEntity<ResponseModel> getLogin(@Valid @RequestBody LoginRequest loginRequest) throws IOException, MyException {

        log.info("REQUEST: "+loginRequest.toString());
        GoogleUser googleUser = googleUserService.getGoogleUserInfo(loginRequest.getToken());
        if (ValidateUtils.isNotNTQMail(googleUser.getEmail(), FormatConstant.FORMAT_NTQ_MAIL)){
            return new ResponseEntity<>(new ResponseModel(CodeResponse.FAIL_CODE.getCode()), HttpStatus.OK);

        }
        User user = googleUserService.getUserFromGoogleUser(googleUser);
        User findUserByEmail = userService.getUserByEmail(user.getEmail());
        if (findUserByEmail==null) {
            user.setAmount(0.0f);
            userService.insert(user);
        }
        Session session = new Session(findUserByEmail);
        sessionService.insert(session);
        LoginResponse loginResponse = new LoginResponse(session.getId(),user.getName(),user.getPicture());
        log.info("RESPOND: "+loginResponse.toString());
        return ResponseUtils.buildResponseEntity(loginResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{session_id}")
    public ResponseEntity<ResponseModel> getLogout(@PathVariable(name = "session_id") String sessionId) throws MyException {
        Session session = sessionService.getEntityById(sessionId);
        if (session == null) {
            return new ResponseEntity<>(new ResponseModel(CodeResponse.FAIL_CODE.getCode()), HttpStatus.OK);
        }
        LogoutResponse logoutResponse = new LogoutResponse(session.getUser().getName(),sessionId);
        sessionService.deleteById(sessionId);
        return ResponseUtils.buildResponseEntity(logoutResponse, HttpStatus.OK);
    }
}
