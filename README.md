![](https://github.com/NNECT/TeamProject-Kiosk-SNU/blob/main/tmp/icon.png?raw=true) Kiosk SNU
===
Study Cafe and U: 중앙정보처리학원 401호 팀프로젝트

## 1. 프로젝트 개요
* 프로젝트 프레젠테이션 [링크](https://docs.google.com/presentation/d/e/2PACX-1vTu4oToQbzVPA8zW4qt6ZVcFCFWSkIGhRTDyNLz2-ySAlMbe5AynfeNr4oTqCj4Gg/pub?start=false&loop=false&delayms=10000)
* 시연 영상 [링크](https://drive.google.com/file/d/1b7Z_kZXGpU9ZvlUQ1016UOK3xjGmkfcW/view?usp=sharing)

## 2. 사용 툴
* IntelliJ
* Java JDK 11.0.18
* Apache Tomcat 8.5.88
* MySQL 8.0.33
* Spring 5.3.28

## 3. Git 미포함 파일
### 3.1. database.properties
```properties
# src\main\resources\config\database.properties
jdbc.driverClassName=<DriverClassName>
jdbc.url=<URL>
jdbc.username=<Username>
jdbc.password=<Password>
jdbc.initialSize=<InitialSize>
jdbc.maxTotal=<MaxTotal>
```
* 데이터베이스 연결을 위한 프로퍼티 파일
* 데이터베이스 암호가 포함되어있으므로 git 저장소에 넣지 않음
