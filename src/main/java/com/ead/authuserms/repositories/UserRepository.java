package com.ead.authuserms.repositories;

import com.ead.authuserms.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
}
