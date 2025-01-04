# fbpro 프로젝트

**fbpro**는 근로자의 **근무 관리**와 **스케줄링 시스템**을 위한 웹 애플리케이션입니다. 현재 개발 중이며, 사용자는 **관리자**와 **근로자**로 구분되어 각자의 역할에 맞는 권한을 가집니다. 프로젝트는 **Spring Boot**와 **Vue.js**를 사용하여 **백엔드**와 **프론트엔드**를 구현하고 있으며, **MyBatis**를 통해 **MariaDB**와 연결됩니다.

## 프로젝트 개요

- **근로자 스케줄 관리**: 관리자는 근로자의 근무 일정을 수정할 수 있으며, 근로자는 자신의 일정을 확인하고 수정할 수 있습니다.
- **사용자 관리**: 관리자만 사용자(근로자) 정보를 추가/수정/삭제할 수 있으며, 근로자는 자신의 정보만 조회할 수 있습니다.
- **웹 애플리케이션**: Vue.js를 사용하여 프론트엔드의 UI를 구현하고, Spring Boot로 API 서버를 구축합니다.

## 주요 기능 (현재 개발 중)

- **사용자 관리**
  - **로그인** 및 **회원가입** 기능을 통해 사용자를 관리합니다.
  - **관리자**는 사용자 정보를 관리하고, **근로자**는 자신의 정보만 조회할 수 있습니다.

- **근무 스케줄 관리**
  - **관리자**는 근로자의 근무 일정을 추가, 수정, 삭제할 수 있습니다.
  - **근로자**는 자신의 근무 일정을 조회하고 수정할 수 있습니다.

- **역할 관리**
  - **관리자**는 시스템 관리 및 근로자 스케줄을 관리할 수 있는 권한을 가집니다.
  - **근로자**는 자신의 스케줄만 (수정하고) 조회할 수 있는 권한을 가집니다.

## 설계된 테이블

### 1. **users 테이블**
- **용도**: 사용자 정보를 저장하는 테이블입니다. 각 사용자는 고유한 `user_id`를 가지며, 이름, 비밀번호, 이메일, 역할 등의 정보가 저장됩니다.
- **컬럼**:
  - `user_id`: 사용자 고유 ID (로그인 ID)
  - `full_name`: 사용자 실명
  - `password`: 암호화된 비밀번호
  - `email`: 이메일 주소
  - `role_code`: 사용자 역할 (예: ADMIN, WORKER)
  - `created_at`: 계정 생성 시간 (자동으로 기록)

### 2. **roles 테이블**
- **용도**: 사용자의 역할을 관리하는 테이블입니다. 각 역할은 특정 권한을 가집니다.
- **컬럼**:
  - `role_code`: 역할 코드 (예: ADMIN, WORKER)
  - `role_name`: 역할 이름 (예: 관리자, 근로자)

### 3. **schedules 테이블**
- **용도**: 사용자의 근무 일정을 관리하는 테이블입니다. 각 일정은 특정 날짜와 시간에 대한 정보를 포함하고 있으며, 해당 일정은 사용자의 역할에 따라 추가, 수정, 삭제가 가능합니다.
- **컬럼**:
  - `schedule_id`: 스케줄 고유 ID
  - `user_id`: 사용자의 ID 
  - `start_time`: 근무 시작 시간
  - `end_time`: 근무 종료 시간
  - `shift_type`: 근무 유형 (예: 오전, 오후)

### 4. **tasks 테이블**
- **용도**: 스케줄에 포함된 작업을 관리하는 테이블입니다. 각 근무 시간 동안 수행할 작업이 기록됩니다.
- **컬럼**:
  - `task_id`: 작업 고유 ID
  - `schedule_id`: 스케줄 ID 
  - `task_description`: 작업 내용
  - `status`: 작업 상태 (예: 완료, 미완료)

### 5. **change_logs 테이블**
- **용도**: 사용자와 관련된 모든 변경 이력을 저장하는 테이블입니다. 사용자의 정보 수정, 삭제 등 중요한 변경 사항이 이 테이블에 기록됩니다.
- **컬럼**:
  - `change_id`: 변경 이력 고유 ID
  - `user_id`: 사용자 ID 
  - `change_type`: 변경 유형 (예: 수정, 삭제)
  - `change_description`: 변경된 내용
  - `change_date`: 변경 일시

## 기술 스택

- **Backend**: Spring Boot, MyBatis, Java
- **Frontend**: Vue.js(진행예정)
- **Database**: MariaDB
- **기타**: RESTful API, Maven, Git

## 현재 진행 상황

1. **사용자 관리 기능**:
   - 사용자 정보 등록, 조회, 수정, 삭제 기능 구현 중.
   - **관리자**는 사용자 정보를 관리하고, **근로자**는 자신의 정보만 수정/조회 가능.

2. **근무 스케줄 관리**:
   - **관리자**는 근로자의 근무 일정을 관리할 수 있으며, 근로자는 자신의 근무 일정을 조회할 수 있는 기능을 추가 중.

3. **API 설계**:
   - RESTful API를 사용하여 프론트엔드와 백엔드를 연동하고 있습니다.

## 오류 관리 내역
Vue.js에서 캘린더 관련 오류

  오류: Cannot read properties of undefined (reading 'Calendar')
  조치 사항: FullCalendar와 관련된 패키지 임포트 문제를 해결하고, ref를 통해 캘린더 DOM을 정상적으로 참조하여 오류를 해결했습니다.
  Vue Router 경로 문제
  
  오류: 경로 /users에 대한 매칭 오류
  조치 사항: 라우터 설정에서 올바른 경로를 지정하여 정상적인 라우팅을 할 수 있도록 수정했습니다.
  기본적인 스타일 문제
  
  오류: Unhandled error during execution of mounted hook
  조치 사항: 스타일링 문제를 해결하여 화면 레이아웃과 관련된 오류를 수정했습니다.



## 향후 계획
  더미 데이터 활용 및 테스트
  
    현재 데이터는 더미 데이터를 사용하고 있습니다. 향후, 더미 데이터를 바탕으로 시스템을 테스트하고, 필요한 기능들이 정상적으로 작동하는지 점검할 예정입니다.
    기본적인 CRUD 기능 완성
    
    사용자 관리 기능에서 관리자는 사용자 정보를 추가, 수정, 삭제할 수 있으며, 근로자는 자신의 정보를 조회만 할 수 있습니다.
    근로자의 스케줄 관리에서 근로자는 자신의 스케줄을 조회할 수 있으며, 일정 추가 및 삭제만 가능합니다. 수정 기능은 제공되지 않습니다.
  
  간단한 UI/UX 개선
  
    간단한 스타일을 적용하여 프로젝트의 사용자 인터페이스를 개선할 예정입니다. 이를 통해 더 직관적인 웹 애플리케이션을 만들 계획입니다.
  
  로그인 기능 추가
  
    기본적인 로그인 기능을 추가하여, 사용자가 본인 정보를 조회하고 스케줄을 확인할 수 있도록 합니다. (회원가입 및 로그인 시 간단한 유효성 검사 포함)
    API 테스트 및 디버깅
    
  RESTful API를 통해 백엔드와 프론트엔드 간의 연동을 완료한 후, API가 잘 동작하는지 테스트하고, 발견된 오류를 수정할 예정입니다.
