package com.swlab.webapp.dto;

import com.swlab.webapp.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String phoneNo;


    public User toEntity() {
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .name(name)
                .phoneNo(phoneNo)
                .build();
    }

    @Builder
    public UserDto(Long id, String email, String password, String name, String phoneNo) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNo = phoneNo;
    }
}
