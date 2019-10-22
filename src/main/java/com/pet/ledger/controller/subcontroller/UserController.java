package com.pet.ledger.controller.subcontroller;

import com.pet.ledger.model.type.User;
import com.pet.ledger.service.base.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;




}
