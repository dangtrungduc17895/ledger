package com.pet.ledger.controller.subcontroller;

import com.pet.ledger.controller.BaseController;
import com.pet.ledger.model.type.User;
import com.pet.ledger.response.ResponseModel;
import com.pet.ledger.response.type.user.UserResponse;
import com.pet.ledger.service.base.SessionService;
import com.pet.ledger.service.base.UserService;
import com.pet.ledger.utils.ResponseUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@AllArgsConstructor
public class UserController extends BaseController {


    @GetMapping()
    public ResponseEntity<ResponseModel> getUserDetail(@RequestHeader String token) {
        User user = sessionService.getEntityById(token).getUser();
        UserResponse userResponse = new UserResponse(user);
        return ResponseUtils.buildResponseEntity(userResponse, HttpStatus.OK);
    }


}
