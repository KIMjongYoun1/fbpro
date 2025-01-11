# fbpro 프로젝트

**fbpro**는 근로자의 **근무 관리**와 **스케줄링 시스템**을 위한 웹 애플리케이션입니다. 현재 개발 중이며, 사용자는 **관리자**와 **근로자**로 구분되어 각자의 역할에 맞는 권한을 가집니다. 프로젝트는 **Spring Boot**와 **Vue.js**를 사용하여 **백엔드**와 **프론트엔드**를 구현하고 있으며, **MyBatis**를 통해 **MariaDB**와 연결됩니다.

---

## 프로젝트 개요

- **근로자 스케줄 관리**: 관리자는 근로자의 근무 일정을 수정할 수 있으며, 근로자는 자신의 일정을 확인하고 수정할 수 있습니다.
- **사용자 관리**: 관리자만 사용자(근로자) 정보를 추가/수정/삭제할 수 있으며, 근로자는 자신의 정보만 조회할 수 있습니다.
- **웹 애플리케이션**: Vue.js를 사용하여 프론트엔드의 UI를 구현하고, Spring Boot로 API 서버를 구축합니다.

---

## 주요 기능 (현재 개발 중)

1. **사용자 관리**
   - 로그인 및 회원가입 기능을 통해 사용자를 관리합니다.
   - 관리자는 사용자 정보를 관리하고, 근로자는 자신의 정보만 조회할 수 있습니다.

2. **근무 스케줄 관리**
   - 관리자는 근로자의 근무 일정을 추가, 수정, 삭제할 수 있습니다.
   - 근로자는 자신의 근무 일정을 조회할 수 있습니다.
   - **오늘 업데이트 내용**:
     - **풀캘린더 연동**: Vue.js FullCalendar를 백엔드 API(`/schedule/List`)와 연동하여 근무 스케줄 데이터를 캘린더에 렌더링.
     - **근무 유형 관리**: 스케줄과 `shift_types` 테이블을 조인하여 근무 유형(`shiftCode`, `shiftName`) 데이터를 추가로 표시.
     - 날짜 형식 변환(`yyyy-MM-dd`) 및 FullCalendar와 호환 가능한 이벤트 형식으로 데이터 가공 처리.

3. **역할 관리**
   - 관리자는 시스템 관리 및 근로자 스케줄을 관리할 수 있는 권한을 가집니다.
   - 근로자는 자신의 스케줄만 조회하고 수정할 수 있는 권한을 가집니다.

4. **API 설계**
   - RESTful API를 사용하여 프론트엔드와 백엔드를 연동하고 있습니다.
   - **오늘 업데이트 내용**:
     - Axios를 사용하여 API 호출 후 데이터를 FullCalendar에서 사용할 수 있는 `start`, `title` 형식으로 매핑.
     - 백엔드 서비스 로직에서 날짜 형식 변환 및 데이터 처리 추가.

5. **근무 유형 관리 (Shift Types)**
   - 근무 유형 테이블(`shift_types`)을 통해 각 스케줄에 시작/종료 시간과 근무 유형 정보를 매핑.
   - 근무 유형을 기반으로 스케줄 데이터를 조회 및 시각화.



---

## 설계된 테이블

### users 테이블
- **용도**: 사용자 정보를 저장하는 테이블입니다. 각 사용자는 고유한 `user_id`를 가지며, 이름, 비밀번호, 이메일, 역할 등의 정보가 저장됩니다.
- **컬럼**:
  - `user_id`: 사용자 고유 ID (로그인 ID)
  - `full_name`: 사용자 실명
  - `password`: 암호화된 비밀번호
  - `email`: 이메일 주소
  - `role_code`: 사용자 역할 (예: ADMIN, WORKER)
  - `created_at`: 계정 생성 시간 (자동으로 기록)

### roles 테이블
- **용도**: 사용자의 역할을 관리하는 테이블입니다. 각 역할은 특정 권한을 가집니다.
- **컬럼**:
  - `role_code`: 역할 코드 (예: ADMIN, WORKER)
  - `role_name`: 역할 이름 (예: 관리자, 근로자)

### schedules 테이블
- **용도**: 사용자의 근무 일정을 관리하는 테이블입니다. 각 일정은 특정 날짜와 시간에 대한 정보를 포함하고 있으며, 해당 일정은 사용자의 역할에 따라 추가, 수정, 삭제가 가능합니다.
- **컬럼**:
  - `schedule_id`: 스케줄 고유 ID
  - `user_id`: 사용자의 ID 
  - `start_time`: 근무 시작 시간
  - `end_time`: 근무 종료 시간
  - `shift_type`: 근무 유형 (예: 오전, 오후)

### shift_types 테이블
- **용도**: 근무 유형(Shift Type)을 저장하는 테이블입니다. 각 근무 유형은 특정 시작 시간과 종료 시간, 그리고 설명을 포함합니다.
- **컬럼**:
  - `shift_code`: 근무 유형 코드 (예: A1, B2)
  - `start_time`: 근무 시작 시간
  - `end_time`: 근무 종료 시간
  - `description`: 근무 유형 설명 (예: Morning Shift)

---

## 기술 스택

- **Backend**: Spring Boot, MyBatis, Java
- **Frontend**: Vue.js
- **Database**: MariaDB
- **기타**: RESTful API, Maven, Git

---

## 현재 진행 상황

1. **풀캘린더 연동 및 스케줄 관리 기능**
   - Vue.js FullCalendar와 백엔드 API(`/schedule/List`)를 연동하여 근무 스케줄 데이터를 캘린더에 렌더링.
   - 프론트엔드에서 Axios를 사용하여 API 호출 및 이벤트 데이터를 캘린더에 매핑.
   - 백엔드 서비스 로직에서 날짜 형식(`yyyy-MM-dd`) 변환 및 데이터 처리 완료.

2. **스케줄 코드 및 근무 유형 관리**
   - `shift_types` 테이블과의 조인으로 근무 유형(`shiftCode`, `shiftName`) 데이터를 포함하도록 조회 쿼리 수정.
   - API 호출 결과를 FullCalendar에 적합한 형식(`start`, `title`)으로 변환.

3. **SQL 및 데이터베이스 작업**
   - 테이블 및 필드 이름 불일치로 발생한 SQL 오류 해결.
   - 날짜 형식 변환 로직 추가.

---

## 오류 관리 내역

1. **데이터 바인딩 문제**
   - **오류 내용**: Vue.js에서 FullCalendar에 전달된 데이터가 `null`로 나타나 렌더링되지 않는 문제.
   - **원인**: Axios 호출 결과를 FullCalendar 이벤트 형식(`start`, `title`)에 맞게 변환하지 않아 발생.
   - **조치 사항**: Axios 호출 후 데이터를 FullCalendar 형식에 맞게 변환하여 해결.

2. **SQLSyntaxErrorException**
   - **오류 내용**: 백엔드에서 스케줄 조회 시 테이블 또는 필드 이름 불일치로 SQL 구문 오류 발생.
   - **원인**: 테이블 명과 필드 이름 불일치(`shift_type` → `shift_types`).
   - **조치 사항**: 테이블 이름과 쿼리를 정확히 일치하도록 수정하여 문제 해결.

3. **날짜 형식 변환 오류**
   - **오류 내용**: API 호출 결과로 반환된 `createdAt` 값이 FullCalendar의 `start` 필드 형식과 맞지 않아 오류 발생.
   - **조치 사항**: 날짜 데이터를 `yyyy-MM-dd` 형식으로 변환하여 FullCalendar에서 정상적으로 렌더링되도록 처리.

4. **CORS(Cross-Origin Resource Sharing) 오류**
   - **오류 내용**: 프론트엔드에서 API 호출 시 CORS 정책으로 인해 요청이 차단되는 문제.
   - **조치 사항**: 백엔드에 `@CrossOrigin` 어노테이션 추가로 CORS 정책을 허용하여 해결.

---

## 최종 진행 사항

1. **사용자 관리 기능**
   - 사용자 정보 등록, 조회, 수정, 삭제 기능 구현 중.
   - 관리자는 사용자 정보를 관리하고, 근로자는 자신의 정보만 조회할 수 있습니다.
   
2. **근무 스케줄 관리**
   - 관리자는 근로자의 근무 일정을 관리할 수 있으며, 근로자는 자신의 근무 일정을 조회할 수 있는 기능을 추가 중.

3. **API 설계**
   - RESTful API를 사용하여 프론트엔드와 백엔드를 연동하고 있습니다.
