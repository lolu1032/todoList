# 회원 시스템 (2024-09-03 ~ 2024-09-07)

## 시큐리티
시큐리티 설정입니다.<br>
<br>
![시큐리티](https://github.com/user-attachments/assets/db778d65-8498-4a82-8c8b-5ef2cc4ed811)

## 회원가입
회원가입 관련 기능과 유효성 검사로 확인하는 정보 그리고 유효성 검사를 통과하면 시큐리티를 통해 비밀번호 암호화를 진행합니다.<br>
<br>
![회원가입 컨트롤러](https://github.com/user-attachments/assets/a4804e75-fea7-45ce-b7b1-3801ec902062)

## 유효성 검사
회원가입 시 정보를 받아 유효성 검사를 시작합니다.<br>
<br>
![유효성 검사](https://github.com/user-attachments/assets/e7a056e1-6290-49e2-9609-1b1e899b4438)

### 이메일 존재 확인
유효성 검사를 통과한 정보의 토대로 이메일이 데이터베이스 상에 존재하는지 확인합니다.<br>
<br>
![이메일 중복 확인](https://github.com/user-attachments/assets/142231ee-832d-4ccd-b949-4ae78bd7e8ab)

## 로그인
로그인 기능과 Client에서 받은 정보인 비밀번호는 시큐리티에서 제공하는 matches를 사용하여 비밀번호를 암호화한것과 비교를 해서 일치하는지 확인합니다.<br>
<br>
![로그인 컨트롤러](https://github.com/user-attachments/assets/49a8756b-f84e-4b81-9182-699bc761c5b9)
# 2024-09-08~2024-09-09
## 투두 리스트 CRUD

### 수정
업데이트 기존에 있던 정보를 수정합니다.<br>
<br>
![업데이트](https://github.com/user-attachments/assets/4e1f6c7e-fc65-4ff0-9c8e-bfb59bf426d8)
### 추가
새로운 일정을 추가합니다.<br>
<br>
![추가](https://github.com/user-attachments/assets/28779f5a-182c-4485-9e91-8f7d657d39e5)
### 삭제
정보를 삭제합니다.<br>
<br>
![삭제](https://github.com/user-attachments/assets/6af99956-d20f-45c2-a32b-c2509a6a2eab)
### 성공
성공을 누르면 성공 컬럼이 1로 업데이트 되면서 소프트 딜리트를 사용하여 리스트에서는 사라집니다.<br>
<br>
![최종 찐 성공](https://github.com/user-attachments/assets/ab667703-d4d2-4f82-8b5e-8a9ff67b82a0)

