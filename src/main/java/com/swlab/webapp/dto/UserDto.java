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
    private String account;
    private String password;
    private String email;

    public User toEntity() {
        return User.builder()
                .id(id)
                .account(account)
                .password(password)
                .email(email)
                .build();
    }

    @Builder
    public UserDto(Long id, String account, String password, String email) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.email = email;
    }
}
