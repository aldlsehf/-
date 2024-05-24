package com.commitfarm.farm.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRes {
    private String username;
    private boolean admin;
    private String email;
}
