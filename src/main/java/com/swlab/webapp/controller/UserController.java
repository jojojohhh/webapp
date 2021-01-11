package com.swlab.webapp.controller;

import com.swlab.webapp.domain.User;
import com.swlab.webapp.dto.UserDto;
import com.swlab.webapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

        Optional<User> User = userService.findByEmail(email);
        if(User.isPresent()) {
            response.put("result", "success");
            response.put("user", User.get());
        } else {
            response.put("result", "fail");
            response.put("reason", "일치하는 회원 정보가 없습니다. email을 확인 해 주세요.");
        }
        return response;
    }

    @PostMapping("")
    public Map<String, Object> save(@RequestBody UserDto userDto) {
        Map<String, Object> response = new HashMap<>();

        User user = userService.save(userDto);
        if (user != null) {
            response.put("result", "success");
            response.put("user", user);
        } else {
            response.put("result", "fail");
            response.put("reason", "회원 가입 실패");
        }
        return response;
    }

    @PatchMapping("/{email}")
    public Map<String, Object> patch(@PathVariable("email") String email, @RequestBody UserDto userDto) {
        Map<String, Object> response = new HashMap<>();

        if (userService.patch(email, userDto) > 0) {
            response.put("result", "success");
        } else {
            response.put("result", "fail");
            response.put("reason", "일치하는 회원 정보가 없음");
        }

        return response;
    }

    @DeleteMapping("/{email}")
    public Map<String, Object> delete(@PathVariable("email") String email) {
        Map<String, Object> response = new HashMap<>();

        if (userService.delete(email) > 0) {
            response.put("result", "success");
        } else {
            response.put("result", "fail");
            response.put("reason", "일치하는 회원 정보 없음");
        }
        return response;
    }
}
