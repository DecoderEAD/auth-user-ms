package com.ead.authuserms.repositories;

import com.ead.authuserms.models.UserCourseModel;
import com.ead.authuserms.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserCourseRepository extends JpaRepository<UserCourseModel, UUID> {

    boolean existsByUserAndCourseId(UserModel userModel, UUID courseId);
}
