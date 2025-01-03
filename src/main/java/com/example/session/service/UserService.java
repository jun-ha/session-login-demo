package com.example.session.service;

import com.example.session.config.SessionConfig;
import com.example.session.domain.User;
import com.example.session.exception.DuplicateUsernameException;
import com.example.session.common.Code;
import com.example.session.exception.UserNotFoundException;
import com.example.session.exception.WrongPasswordException;
import com.example.session.repository.UserRepository;
import com.example.session.service.session.LoginSession;
import com.example.session.service.session.SessionId;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final RedisTemplate<String, Object> redisTemplate;

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

    @Transactional(readOnly = true)
    public SessionId login(LoginCommand request) {
        User findUser = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException(Code.USER_NOT_FOUND, "유저를 찾을 수 없습니다."));

        if(!findUser.getPassword().equals(request.getPassword())) {
            throw new WrongPasswordException(Code.WRONG_PASSWORD, "아이디 혹은 비밀번호가 잘못되었습니다.");
        }

        SessionId sid = new SessionId(UUID.randomUUID().toString());

        redisTemplate.opsForValue().set(String.valueOf(sid), new LoginSession(sid), SessionConfig.MAX_AGE);

        return sid;
    }

    public void logout(SessionId sid) {
        redisTemplate.delete(String.valueOf(sid));
    }
}
