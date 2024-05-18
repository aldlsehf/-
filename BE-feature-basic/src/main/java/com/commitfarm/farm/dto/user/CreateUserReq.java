package com.commitfarm.farm.dto.user;

import com.commitfarm.farm.domain.enumClass.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 회원 가입
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserReq {
    @Schema(description = "사용자 이름")
    @NotBlank(message = "Username is required")
    private String username;

    @Schema(description = "사용자 비밀번호")
    @NotBlank(message = "Password is required")
    private String password;

    @Schema(description = "사용자 이메일")
    @NotBlank(message = "Email is required")
    private String email;
}
