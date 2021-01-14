package com.swlab.webapp.repository;

import com.swlab.webapp.model.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
