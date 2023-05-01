package com.ead.authuserms.controllers;

import com.ead.authuserms.clients.CourseClient;
import com.ead.authuserms.dtos.CourseDTO;
import com.ead.authuserms.dtos.UserCourseDTO;
import com.ead.authuserms.models.UserModel;
import com.ead.authuserms.services.UserCourseService;
import com.ead.authuserms.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class UserCourseController {

    private final CourseClient courseClient;
    private final UserService userService;

    private final UserCourseService userCourseService;

    @GetMapping("/users/{userId}/courses")
    public ResponseEntity<Object> getAllCoursesByUser(@PageableDefault(page = 0, size = 10, sort = "courseId", direction = Sort.Direction.ASC) Pageable pageable,
                                                               @PathVariable(value = "userId") UUID userId) {
        Optional<UserModel> userModelOptional = userService.findById(userId);
        if (userModelOptional.isEmpty()) {
            log.warn("User with id {} not found ", userId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(courseClient.getAllCoursesByUser(pageable, userId));
    }

    @PostMapping("/users/{userId}/courses/subscription")
    public ResponseEntity<Object> saveSubscriptionUserInCourse(@PathVariable(value = "userId") UUID userId,
                                                               @RequestBody @Valid UserCourseDTO userCourseDTO) {
        Optional<UserModel> userModelOptional = userService.findById(userId);
        if (userModelOptional.isEmpty()) {
            log.warn("User with id {} not found ", userId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        if (userCourseService.existsByUserAndCourseId(userModelOptional.get(), userCourseDTO.getCourseId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Subscription already exists!");
        }
        var userCourseModel = userCourseService.save(userModelOptional.get().convertToUserCourseModel(userCourseDTO.getCourseId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(userCourseModel);
    }

    @DeleteMapping("/users/courses/{courseId}")
    public ResponseEntity<Object> deleteUserCourseByCourse(@PathVariable(value = "courseId") UUID courseId) {
        if(!userCourseService.existsByCourseId(courseId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UserCourse not found");
        }
        userCourseService.deleteUserCourseByCourse(courseId);
        return ResponseEntity.status(HttpStatus.OK).body("UserCourse deleted successfully.");
    }
}
