package com.commitfarm.farm.repository;

import com.commitfarm.farm.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
}
