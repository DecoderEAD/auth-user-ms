package com.ead.authuserms.services.impl;

import com.ead.authuserms.repositories.UserRepository;
import com.ead.authuserms.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

}
