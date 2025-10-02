package com.mllq.back.core.domain.core.dto.request.user;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String email;
    private String password;
}
