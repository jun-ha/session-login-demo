package com.example.session.service;

import com.example.session.domain.User;
import com.example.session.exception.DuplicateUsernameException;
import com.example.session.common.Code;
import com.example.session.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterUserService implements RegisterUserUseCase {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void register(RegisterCommand request) {
        try {
            userRepository.save(
                    new User(request.getUsername(), request.getPassword())
            );
            userRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateUsernameException(Code.DUPLICATE_USERNAME, "이미 존재하는 유저 이름입니다.");
        }
    }
}
