package com.swlab.webapp.repository;

import com.swlab.webapp.model.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    List<User> findAll();
    @EntityGraph(attributePaths = "userRoles")
    Optional<User> findWithUserRolesByEmailAndDel(String email, boolean del);
}
