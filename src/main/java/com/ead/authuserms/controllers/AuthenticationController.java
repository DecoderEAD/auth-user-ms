package com.ead.authuserms.controllers;

import com.ead.authuserms.dtos.UserDTO;
import com.ead.authuserms.models.UserModel;
import com.ead.authuserms.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.ead.authuserms.enums.UserStatusEnum.ACTIVE;
import static com.ead.authuserms.enums.UserTypeEnum.STUDENT;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody
                                               @Validated(UserDTO.UserView.RegistrationPost.class)
                                               @JsonView(UserDTO.UserView.RegistrationPost.class) UserDTO userDTO) {
        log.debug("POST registerUser userDTO received {} ", userDTO.toString());
        if (userService.existsByUserName(userDTO.getUserName())) {
            log.warn("Username {} is already taken ", userDTO.getUserName());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Username is already taken!");
        }
        if (userService.existsByEmail(userDTO.getEmail())) {
            log.warn("Email {} is already taken! ", userDTO.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Email is already taken!");
        }
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDTO, userModel);
        userModel.setStatus(ACTIVE);
        userModel.setUserType(STUDENT);
        userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        userService.save(userModel);
        log.debug("POST registerUser userModel saved {} ", userModel.toString());
        log.info("User saved succesfully userId {} ", userModel.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }

    @GetMapping("/")
    public String index() {
        log.trace("TRACE");
        log.debug("DEBUG");
        log.info("INFO");
        log.warn("WARN");
        log.error("ERROR");
        return "Loggin Spring Boot...";
    }
}
