package com.commitfarm.farm.dto.project;

import com.commitfarm.farm.domain.enumClass.UserType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//admin이 project를 만들 때 추가할 계정
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ManageUserAccountReq {
    private String projectName;
    private String userEmail;
    private String userRole;
    private UserType userType;
}
