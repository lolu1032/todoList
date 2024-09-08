package com.test.todoList.controller;

import com.test.todoList.dto.UsersDTO;
import com.test.todoList.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.SessionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Authenticator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class UsersController {
    private final UsersService service;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<Map<String,Object>> signup(
            @RequestBody UsersDTO usersDTO
    ) {
        log.info("usersDTO={}",usersDTO);
        Map<String, Object> response = new HashMap<>();

        try {
            vaildateUsersDTO(usersDTO);

            Map<String, Object> map = new HashMap<>();
            map.put("email",usersDTO.getEmail());
            checkEmail(map);

            String encodePassword = bCryptPasswordEncoder.encode(usersDTO.getPassword());
            usersDTO.setPassword(encodePassword);

            service.insertUsers(usersDTO);
            response.put("message", "회원가입 성공했습니다.");
            return new ResponseEntity<>(response, HttpStatus.CREATED); // 성공시 201보냄
        } catch (IllegalArgumentException e) {
            response.put("error", "클라이언트오류");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // 400 클라이언트 오류
        }catch (IllegalStateException e) {
            response.put("message", "회원가입 실패했습니다.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED); // 401 클라이언트 오류
        }catch (Exception e) {
            log.error("서버 오류: {}", e.getMessage(), e);
            response.put("error","서버오류");
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR); // 500 서버오류
        }
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(
            @RequestBody Map<String,Object> map,
            HttpServletRequest request
    ) {
        String email = (String) map.get("email");
        String password = (String) map.get("password");
        Map<String,Object> response = new HashMap<>();
        UsersDTO user = service.selectUsers(email);
        if(user == null) {
            response.put("message","회원정보가 없습니다.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        boolean matchPw = bCryptPasswordEncoder.matches(password,user.getPassword()); // 암호화 비번 일치 확인
        log.info("matchPw={}",matchPw);

        if(matchPw==false) {
            response.put("message","비밀번호가 일치하지않습니다.");
            return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
        }
        HttpSession session = request.getSession();
        session.setAttribute("users",user.getEmail());
        session.setAttribute("username",user.getName());
        log.info("session={}",session.getId());

        response.put("message","로그인 성공했습니다.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    private void checkEmail(Map<String,Object> map) {
        String email = (String) map.get("email");
        int n = service.checkEmail(email);
        log.info("n={}",n);
        if (n > 0){
            throw new IllegalArgumentException("이미 있는 이메일입니다.");
        }
    }
    private void vaildateUsersDTO(UsersDTO usersDTO) {
        String emailRegExp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
        String passwordRegExp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&+=]).{8,15}$";
        String nameRegExp = "^[가-힣]*$";
        if(usersDTO.getName() == null || usersDTO.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("이름이 비어있습니다.");
        }
        if(!usersDTO.getName().matches(nameRegExp)) {
            throw new IllegalArgumentException("한국어로 입력해주세요.");
        }
        if(usersDTO.getEmail() == null || usersDTO.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("이메일이 비어있습니다.");
        }
        if(!usersDTO.getEmail().matches(emailRegExp)) {
            throw new IllegalArgumentException("example@naver.com 형식에 맞게 입력해주세요.");
        }
        if(usersDTO.getPassword() == null || usersDTO.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("비밀번호가 비어있습니다.");
        }
        if(!usersDTO.getPassword().matches(passwordRegExp)) {
            log.warn("비밀번호 검증 실패: {} (정규 표현식: {})", usersDTO.getPassword(), passwordRegExp);
            throw new IllegalArgumentException("비밀번호 (숫자, 문자, 특수문자 포함 8~15자리 이내) 입력하세요.");
        }
    }
}
