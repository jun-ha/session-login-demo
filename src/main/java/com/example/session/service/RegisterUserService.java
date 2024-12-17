package com.example.session.service;

import com.example.session.domain.User;
import com.example.session.exception.DuplicateUsernameException;
import com.example.session.exception.ErrorCode;
import com.example.session.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterUserService implements RegisterUserUseCase {

    private final UserRepository userRepository;

    @Override
    public void register(RegisterCommand request) {
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateUsernameException(ErrorCode.DUPLICATE_USERNAME, "이미 존재하는 유저 이름입니다.");
        }

        User user = new User(request.getUsername(), request.getPassword());

        userRepository.save(user);
    }
}
