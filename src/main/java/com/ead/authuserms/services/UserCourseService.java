package com.ead.authuserms.services;

import com.ead.authuserms.models.UserCourseModel;
import com.ead.authuserms.models.UserModel;

import java.util.UUID;

public interface UserCourseService {
    boolean existsByUserAndCourseId(UserModel userModel, UUID courseId);

    UserCourseModel save(UserCourseModel userCourseModel);
}
