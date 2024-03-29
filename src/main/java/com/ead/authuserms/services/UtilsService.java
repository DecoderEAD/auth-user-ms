package com.ead.authuserms.services;

import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UtilsService {

    String createUrlGetAllCoursesByUser(UUID userId, Pageable pageable);

    String createUrlDeleteUserInCourse(UUID userId);
}
