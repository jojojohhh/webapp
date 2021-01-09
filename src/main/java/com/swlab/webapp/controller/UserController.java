package com.swlab.webapp.controller;

import com.swlab.webapp.domain.User;
import com.swlab.webapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping(value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/{email}")
    public Map<String, Object> findByEmail(@PathVariable("email") String email) {
        Map<String, Object> response = new HashMap<>();

        Optional<User> eUser = userService.findByEmail(email);
        if(eUser.isPresent()) {
            response.put("result", "success");
            response.put("user", eUser.get());
        } else {
            response.put("result", "fail");
            response.put("reason", "일치하는 회원 정보가 없습니다. email을 확인 해 주세요.");
        }
        return response;
    }
}
