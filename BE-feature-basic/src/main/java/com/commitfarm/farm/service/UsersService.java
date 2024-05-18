package com.commitfarm.farm.service;

import com.commitfarm.farm.domain.Users;
import com.commitfarm.farm.dto.user.CreateUserReq;
import com.commitfarm.farm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    public void login(String email, String password) throws Exception {
        Users user = userRepository.findByEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            throw new Exception("아이디 또는 비밀번호를 잘못 입력했습니다");
        }
    }

    @Transactional
    public void createUser(CreateUserReq createUserReq) throws Exception {
        // 이메일 중복 체크
        if (userRepository.findByEmail(createUserReq.getEmail()) != null) {
            throw new Exception("이미 존재하는 이메일입니다");
        }

        Users user = new Users();
        user.setUsername(createUserReq.getUsername());
        user.setPassword(createUserReq.getPassword());
        user.setEmail(createUserReq.getEmail());
        user.setAdmin(false);

        userRepository.save(user);
    }

    public Users findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }



}
