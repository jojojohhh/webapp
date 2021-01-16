package com.swlab.webapp.service;

import com.swlab.webapp.model.user.User;
import com.swlab.webapp.dto.UserDto;
import com.swlab.webapp.model.user.UserRole;
import com.swlab.webapp.repository.UserRepository;
import com.swlab.webapp.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User save(UserDto userDto) {
        User user = User.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .name(userDto.getName())
                .phoneNo(userDto.getPhoneNo())
                .build();
        return userRepository.save(user);
    }

    @Transactional
    public int patch(String email, UserDto userDto) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            User sUser = user.get();
            if (StringUtils.isNotBlank(userDto.getEmail()))
                sUser.setEmail(userDto.getEmail());
            if (StringUtils.isNotBlank(userDto.getPassword()))
                sUser.setPassword(userDto.getPassword());
            if (StringUtils.isNotBlank(userDto.getName()))
                sUser.setName(userDto.getName());
            if (StringUtils.isNotBlank(userDto.getPhoneNo()))
                sUser.setPhoneNo(userDto.getPhoneNo());
            userRepository.save(sUser);
            return 1;
        }
        return 0;
    }

    @Transactional
    public int delete(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return 1;
        }
        return 0;
    }

    private UserRole saveUserRole(User user) {
        return userRoleRepository.save(UserRole.builder().user(user).roleName(UserRole.RoleType.ROLE_VIEW).build());
    }

    public User join(UserDto userDto) {
        User user = save(userDto);
        saveUserRole(user);
        return user;
    }
}
