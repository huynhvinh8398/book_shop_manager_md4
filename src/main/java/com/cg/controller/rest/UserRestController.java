package com.cg.controller.rest;


import com.cg.exception.DataInputException;
import com.cg.exception.EmailExistsException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.Product;
import com.cg.model.Role;
import com.cg.model.User;
import com.cg.model.dto.UserDTO;
import com.cg.service.role.IRoleService;
import com.cg.service.user.IUserService;
import com.cg.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private AppUtil appUtil;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;


    @GetMapping()
    public ResponseEntity<?> findAllUser() {

        List<UserDTO> userDTOS = userService.findAllUserDTOAnDeletedIsFalse();

        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            throw new ResourceNotFoundException("Invalid User ID");
        }
        return new ResponseEntity<>(userOptional.get().toUserDTO(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        new UserDTO().validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }

        Boolean existsByUsername = userService.existsByUserName(userDTO.getUsername());
        if (existsByUsername) {
            throw new EmailExistsException("Username ??a?? t????n ta??i vui lo??ng nh????p la??i!");
        }

        Boolean existsByPhone = userService.existsByPhone(userDTO.getPhone());
        if (existsByPhone) {
            throw new EmailExistsException("S???? ??i????n thoa??i ??a?? t????n ta??i");
        }

        Optional<Role> role = roleService.findById(userDTO.getRole().getId());

        if (!role.isPresent()) {
            throw new EmailExistsException("ID ROLE kh??ng t????n ta??i!");
        }

        userDTO.setId(0L);
        userDTO.getLocationRegion().setId(0L);

        Boolean exitsEmail = userService.existsByUserName(userDTO.getUsername());

        if (exitsEmail) {
            throw new EmailExistsException("email ???? t???n t???i");
        }

        User newUser = userService.save(userDTO.toUserr());

        return new ResponseEntity<>(newUser.toUserDTO(), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    private ResponseEntity<?> doUpdate(@Validated @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        new UserDTO().validate(userDTO, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return appUtil.mapErrorToResponse(bindingResult);
        }

        Boolean existById = userService.existsById(userDTO.getId());
        if (!existById) {
            throw new ResourceNotFoundException("Id user kh??ng t???n t???i");
        }
        Optional<Role> role = roleService.findById(userDTO.getRole().getId());
        if (!role.isPresent()) {
            throw new EmailExistsException("ID ROLE kh??ng t????n ta??i!");
        }
        Boolean exitsEmail = userService.existsByUserName(userDTO.getUsername());
        if (exitsEmail) {
            throw new EmailExistsException("Email ???? t???n t???i");
        }
        Boolean existsByPhone = userService.existsByPhone(userDTO.getPhone());
        if (existsByPhone) {
            throw new EmailExistsException("S???? ??i????n thoa??i ??a?? t????n ta??i");
        }

        userDTO.getLocationRegion().setId(0L);
        try {
            User userUpdate = userService.save(userDTO.toUserr());

            return new ResponseEntity<>(userUpdate.toUserDTO(), HttpStatus.ACCEPTED);

        } catch (Exception e) {
            throw new DataInputException(" kh??ng th??? c???p nh???t server kh??ng th??? x??? l??");

        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> doDelete(@PathVariable Long id) {

        Optional<User> user = userService.findById(id);

        if (user.isPresent()) {

            user.get().setDeleted(true);

            userService.deleteUserSoft(user.get());

            return new ResponseEntity<>(HttpStatus.ACCEPTED);

        } else {
            throw new DataInputException("Th??ng tin kh??ng h????p l????");
        }

    }
}
