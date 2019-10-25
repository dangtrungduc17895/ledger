package com.pet.ledger.controller.subcontroller;

import com.pet.ledger.controller.BaseController;
import com.pet.ledger.model.type.User;
import com.pet.ledger.request.type.user.EditUserRequest;
import com.pet.ledger.response.ResponseModel;
import com.pet.ledger.response.type.UploadImageResponse;
import com.pet.ledger.response.type.user.UpdateUserResponse;
import com.pet.ledger.response.type.user.UserDetailResponse;
import com.pet.ledger.utils.ModelMapperUtils;
import com.pet.ledger.utils.ResponseUtils;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

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
        userService.updateById(id, userRequestMapper);
        UpdateUserResponse updateUserResponse = ModelMapperUtils.transferObject(userRequest, UpdateUserResponse.class);
        return ResponseUtils.buildResponseEntity(updateUserResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}/avatar")
    public ResponseEntity<ResponseModel> updateUserAvatar(@PathVariable(value = "id", required = true) String userId,
                                                          @RequestParam(value = "image", required = false) @NotNull(message = "No file selected") MultipartFile image) {
        String pathFile = userService.updateAvatar(userId, image);
        UploadImageResponse updateUserResponse = new UploadImageResponse(pathFile);
        return ResponseUtils.buildResponseEntity(updateUserResponse, HttpStatus.OK);
    }
}
