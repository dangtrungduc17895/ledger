package com.pet.ledger.controller.subcontroller;

import com.pet.ledger.controller.BaseController;
import com.pet.ledger.model.type.User;
import com.pet.ledger.request.type.user.EditUserRequest;
import com.pet.ledger.response.BaseResponse;
import com.pet.ledger.response.ResponseModel;
import com.pet.ledger.response.type.user.UpdateUserResponse;
import com.pet.ledger.response.type.user.UserDetailResponse;
import com.pet.ledger.utils.ModelMapperUtils;
import com.pet.ledger.utils.ResponseUtils;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
@AllArgsConstructor
public class UserController extends BaseController {

    @GetMapping("/account")
    public ResponseEntity<ResponseModel> getUserDetail(@RequestHeader("token")String token) {
        User user = sessionService.getEntityById(token).getUser();
        UserDetailResponse userResponse = ModelMapperUtils.transferObject(user, UserDetailResponse.class);
        return ResponseUtils.buildResponseEntity(userResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseModel> updateUser(@RequestBody EditUserRequest userRequest, @PathVariable String id) {
        User userRequestMapper = ModelMapperUtils.transferObject(userRequest, User.class);
        boolean user = userService.updateById(id, userRequestMapper);
        UpdateUserResponse updateUserResponse  = ModelMapperUtils.transferObject(userRequest, UpdateUserResponse.class);
        return ResponseUtils.buildResponseEntity(updateUserResponse, HttpStatus.OK);
    }
}
