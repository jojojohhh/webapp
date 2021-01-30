# webapp
* Spring Security를 이용한 Spring boot 로그인 및 회원가입 예제
  * 각 `User`에 `Role`을 부여하여 각 `User_Role`에 대한 `page access` 권한 설정
    ```Java
    http.authorizeRequest()
              .antMatchers("/", "/login", "/join").permitAll()
              .antMatchers("/home/users").access("hasRole('ROLE_ADMIN')")
              .antMatchers("/home", "/home/**").access("hasRole('ROLE_VIEW')")
              .anyRequest().authenticated()
    ```
  * `User` 와 `org.springframework.security.core.userdetails.UserDetails`를 상속 받은 `SecurityUser`의 권한을 확인함으로 controller단에서
    redirect 처리함
  * 권한이 없는 사용자가 접근시 `org.springframework.security.web.access.AccessDeniedHandler`의 `handler`메소드를 override 하여 처리
  <details markdown="1">
  <summary>프로젝트 구조</summary>

     ```
    ├──main
    |   ├──java
    |   |   └──com
    |   |       └──swlab
    |   |           └──webapp
    |   |               ├──config
    |   |               |   ├──WebSecurityConfig.java
    |   |               |   └──handler
    |   |               |       └──WebAccessDeniedHandler.java
    |   |               ├──controller
    |   |               |   ├──HomeController.java
    |   |               |   ├──LoginController.java
    |   |               |   └──UserController.java
    |   |               ├──dto
    |   |               |  └──UserDto.java
    |   |               ├──model
    |   |               |   ├──BaseEntity.java
    |   |               |   └── user
    |   |               |       ├──SecurityUser.java
    |   |               |       ├──User.java
    |   |               |       └──UserRole.java
    |   |               ├──repository
    |   |               |   ├──UserRepository.java
    |   |               |   └──UserRoleRepository.java
    |   |               └──service
    |   |                   ├──SecurityUserService.java
    |   |                   └──UserService.java
    |   └──resources
    |       ├──profiles
    |       |   ├──dev
    |       |   |   └──application.yml
    |       |   ├──local
    |       |   |   └──application.yml
    |       |   └──prod
    |       |       └──application.yml
    |       └──static
    |           ├──css
    |           |   └──common.css
    |           ├──img
    |           └──js
    |               └──notify.min.js
    |       └──templates
    |               ├──common
    |               |   ├──block.html
    |               |   ├──layout.html
    |               |   └──layout_login.html
    |               ├──content
    |               |   ├──home.html
    |               |   ├──join.html
    |               |   ├──login.html
    |               |   └──users.html
    |               └──err
    |                   └──denied.html
    ├──build.gradle
    ├──settings.gradle
    ├──gradlew
    └──gradlew.bat
    ```
  </details>
### 개발환경
  
  |도구|Version|
  |:---:|:---:|
  |Language|Java 11|
  |IDE|IntelliJ IDE|
  |Framwork|Spring boot 2.4.1|
  |DB|MySQL 8.0.22|
  |Build|gradle 6.7.1|
  |OS|Window 10|

 
