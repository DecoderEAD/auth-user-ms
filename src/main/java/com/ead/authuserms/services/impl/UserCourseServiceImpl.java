package com.ead.authuserms.services.impl;

import com.ead.authuserms.repositories.UserCourseRepository;
import com.ead.authuserms.services.UserCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCourseServiceImpl implements UserCourseService {

    private final UserCourseRepository userCourseRepository;
}
