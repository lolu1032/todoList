package com.test.todoList.controller;

import com.test.todoList.dto.UsersDTO;
import com.test.todoList.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class UsersController {
    private final UsersService service;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(
            @RequestBody UsersDTO usersDTO
    ) {
        log.info("usersDTO={}",usersDTO);
        Map<String, Object> map = new HashMap<>();
        map.put("email",usersDTO.getEmail());
        try {
            checkEmail(map);
            vaildateUsersDTO(usersDTO);
            service.insertUsers(usersDTO);
            return new ResponseEntity<>("회원가입 성공했습니다.", HttpStatus.CREATED); // 성공시 201보냄
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // 400 클라이언트 오류
        }catch (IllegalStateException e) {
            return new ResponseEntity<>("회원가입 실패했습니다.", HttpStatus.UNAUTHORIZED); // 401 클라이언트 오류
        }catch (Exception e) {
            log.error("서버 오류: {}", e.getMessage(), e);
            return new ResponseEntity<>("서버오류",HttpStatus.INTERNAL_SERVER_ERROR); // 500 서버오류
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody Map<String,Object> map
    ) {
        log.info("map={}",map);
        String email = (String) map.get("email");
        String password = (String) map.get("password");
        
        UsersDTO user = service.selectUsers(map);
        log.info("user={}",user);
        if(user == null) {
            return new ResponseEntity<>("회원정보가 없습니다.", HttpStatus.NOT_FOUND);
        }
        if(!user.getPassword().equals(password)) {
            return new ResponseEntity<>("비밀번호가 일치하지않습니다.",HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("로그인 성공했습니다.", HttpStatus.CREATED);
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
        String passwordRegExp = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$";
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
            throw new IllegalArgumentException("비밀번호 (숫자, 문자, 특수문자 포함 8~15자리 이내) 입력하세요.");
        }
    }
}
