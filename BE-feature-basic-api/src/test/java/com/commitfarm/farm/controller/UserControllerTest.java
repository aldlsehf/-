package com.commitfarm.farm.controller;

import com.commitfarm.farm.dto.user.CreateUserReq;
import com.commitfarm.farm.repository.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsersRepository usersRepository;


    @Test
    @DisplayName("회원가입 테스트")
    void signupTest() throws Exception {

        // Given
        CreateUserReq createUserReq = new CreateUserReq();
        createUserReq.setUsername("minseok");
        createUserReq.setPassword("123");
        createUserReq.setEmail("minseok@example.com");
        createUserReq.setAdmin(false);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(createUserReq);


        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("회원가입 성공"))
                .andDo(MockMvcResultHandlers.print());
    }

}