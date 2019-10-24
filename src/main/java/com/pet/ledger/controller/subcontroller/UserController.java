package com.pet.ledger.controller.subcontroller;

import com.pet.ledger.controller.BaseController;
import com.pet.ledger.model.type.User;
import com.pet.ledger.response.ResponseModel;
import com.pet.ledger.response.type.user.UserDetailResponse;
import com.pet.ledger.response.type.user.UserResponse;
import com.pet.ledger.service.base.SessionService;
import com.pet.ledger.service.base.UserService;
import com.pet.ledger.utils.ResponseUtils;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
@AllArgsConstructor
public class UserController extends BaseController {


    @GetMapping()
    public ResponseEntity<ResponseModel> getUserDetail(@RequestHeader("token")String token) {
        User user = sessionService.getEntityById(token).getUser();
        UserDetailResponse userResponse = new UserDetailResponse(user);
        return ResponseUtils.buildResponseEntity(userResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> updateUser(@RequestBody User userRequest, @PathVariable String id) {
        boolean user = userService.updateById(id, userRequest);
        if (user) {
            return ResponseUtils.buildResponseEntity(null, HttpStatus.OK);
        }
        return null;
    }
}
