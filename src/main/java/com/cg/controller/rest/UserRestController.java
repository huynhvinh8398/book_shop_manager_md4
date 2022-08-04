package com.cg.controller.rest;


import com.cg.exception.EmailExistsException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import com.cg.service.user.IUserService;
import com.cg.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private AppUtil appUtil;

    @Autowired
    private IUserService userService;

    @GetMapping()
        public ResponseEntity<?>findAllUser(){

        List<UserDTO>userDTOS =userService.findAllUserDTO();

        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable long id){
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()){
            throw new ResourceNotFoundException("Invalid customer ID");
        }
        return new ResponseEntity<>(userOptional.get().toUserDTO(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody UserDTO userDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }

        userDTO.setId(1L);
        userDTO.getLocationRegion().setId(0L);

        Boolean exitsEmail = userService.existsByEmail(userDTO.getEmail());

        if (exitsEmail){
            throw new EmailExistsException("Email already exits");
        }

        User newUser = userService.save(userDTO.toUserr());

        return  new ResponseEntity<>(newUser.toUserDTO(), HttpStatus.CREATED);
    }
}
