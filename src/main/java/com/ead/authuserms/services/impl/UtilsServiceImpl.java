package com.ead.authuserms.services.impl;

import com.ead.authuserms.services.UtilsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtilsServiceImpl implements UtilsService {

    @Value("${ead.api.url.course}")
    String REQUEST_URL_COURSE;

    @Override
    public String createUrlGetAllCoursesByUser(UUID userId, Pageable pageable) {
        return REQUEST_URL_COURSE + "/courses?userId=" + userId + "&page=" + pageable.getPageNumber() + "&size="
                + pageable.getPageSize() + "&sort=" + pageable.getSort().toString().replace(": ", ",");
    }

    @Override
    public String createUrlDeleteUserInCourse(UUID userId) {
        return REQUEST_URL_COURSE + "/courses/users/" + userId;
    }
}
