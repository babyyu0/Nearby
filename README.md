<div align="center">
  <h1 style="display:'inline-block'"> <img src='https://github.com/babyyu0/EnjoyTrip/blob/main/front-workspace/public/image/logo.png' width=200vw></h1>
</div>

연인과의 데이트, 가족과의 나들이, 친구와의 약속...  
항상 어디갈지 고민인 당신을 위해 **Always Nearby You**, 당신의 곁에 있습니다.

Nearby는 선택한 지역의 매력적인 관광지를 다양한 조건과 함께 한눈에 보여줍니다.  
더불어, 직접 방문한 사용자들의 솔직한 리뷰를 참고하여 더욱 자세한 정보를 얻어보세요.  
지금 바로 Nearby를 경험해보세요!

<h3>
    <img src="https://em-content.zobj.net/source/apple/354/calendar_1f4c5.png" width=16 height=16 alt="캘린더 아이콘" />
    &nbsp;프로젝트 기간
</h3>

&nbsp;&nbsp;**🛠️ 개발**: 2023. 05. 17 ~ 2023. 05. 26 &nbsp;&nbsp;&nbsp;&nbsp; **🛠️ 리팩토링**: 2023. 09. 27 ~ 진행중

<h3>
    <img src="https://em-content.zobj.net/source/apple/354/gear_2699-fe0f.png" width=16 height=16 alt="톱니바퀴 아이콘" />
    &nbsp;개발 환경
</h3>
<img src='https://github.com/babyyu0/EnjoyTrip/blob/main/document/architecture.PNG' width=500vw>

**🎨 Back-end**
```
  🗲 java 17    🗲 Spring Boot 3.1.5    🗲 Spring Security
```

**💄 Front-end**
```
  🗲 ES6    🗲 React 13.4.0    🗲 Jotai 2.4.3
```

**🗃️ Database**
```
  🗲 MySQL    🗲 Redis
```

**🗣️ Conversation**
```
  🗲 Mattermost    🗲 Git    🗲 GitLab    🗲 Obsidian
```

# 기능

<h3>
    <img src="https://em-content.zobj.net/source/apple/354/puzzle-piece_1f9e9.png" width=16 height=16 alt="퍼즐 아이콘" />
    &nbsp;API 명세
</h3>

#### ➤ 회원
|대분류|기능|Method|URL|권한|
|:-:|:-:|:-:|:-:|:-:|
|회원|아이디 중복 확인|`GET`|<p align='left'>/member/exist/{member-id}</p>|`관리자`  `사용자`|
|회원|회원가입|`POST`|<p align='left'>/member/register</p>|`관리자`  `사용자`|
|회원|로그인|`POST`|<p align='left'>/member/login</p>|`관리자`  `사용자`|
|회원|로그아웃|`POST`|<p align='left'>/member/logout</p>|`관리자`  `사용자`|

#### ➤ 지역
|대분류|기능|Method|URL|권한|상세설명|
|:-:|:-:|:-:|:-:|:-:|:-:|
|지역|지역 리스트 조회|`GET`|<p align='left'>/area</p>|`관리자`  `사용자`|지역과 시군구를 통합하여 조회한다.|
|지역|지역 갱신|`GET`|<p align='left'>/area/refresh</p>|`관리자`|공공데이터포털로부터 정보를 얻어와, 지역과 시군구를 통합하여 갱신한다.|

#### ➤ 관광지
|대분류|기능|Method|URL|권한|상세설명|
|:-:|:-:|:-:|:-:|:-:|:-:|
|관광지|콘텐츠 분류 코드 갱신하기|`GET`|<p align='left'>/cat/refresh</p>|`관리자`|공공데이터포털로부터 정보를 얻어와, 콘텐츠의 대·중·소분류를 통합하여 갱신한다.|
|관광지|가까운 관광지 리스트 조회|`GET`|<p align='left'>/attraction/nearest</p>|`관리자`  `사용자`||
|관광지|인기 관광지 리스트 조회|`GET`|<p align='left'>/attraction/popular</p>|`관리자`  `사용자`||
|관광지|공공데이터 관광 콘텐츠 타입 불러오기|`GET`|<p align='left'>/attraction/content-type/refresh</p>|`관리자`||
|관광지|공공데이터 관광지 불러오기|`GET`|<p align='left'>/attraction/refresh</p>|`관리자`||

<h3>
    <img src="https://em-content.zobj.net/source/apple/354/link_1f517.png" width=16 height=16 alt="링크 아이콘" />
    &nbsp;URL
</h3>

|대분류|화면|URL|
|:-:|:-:|:-:|
|메인|메인|<p align='left'><a href='http://localhost:3000/'>/</a></p>|
|회원|회원가입|<p align='left'><a href='http://localhost:3000/register'>/register</a></p>|
|회원|로그인|<p align='left'><a href='http://localhost:3000/login'>/login</a></p>|

<h3>
    <img src="https://em-content.zobj.net/source/apple/354/desktop-computer_1f5a5-fe0f.png" width=16 height=16 alt="컴퓨터 아이콘" />
    &nbsp;기능 설명
</h3>

#### ◉ 헤더
1. 로고를 클릭하면 메인 화면으로 이동합니다.
2. 검색 창을 통해 가고 싶은 특정 관광지를 검색할 수 있습니다.
3. '관광지' 카테고리로 이동해 지역에 따른 관광지를 찾아볼 수 있습니다.
4. '로그인' 버튼을 클릭하면 로그인 화면으로 이동합니다.

<br/><img src="https://github.com/babyyu0/Nearby/assets/58788576/2ca22652-c669-4f74-beda-54e6b04e47bf" width=720 alt="메인 화면" />

#### ◉ 메인 화면
1. 현재 자신의 위치와 가장 가까운 관광지를 최대 5개 볼 수 있습니다.
2. 많은 회원들이 '좋아요'를 누른 관광지를 최대 5개 볼 수 있습니다.

<br/><img src="https://github.com/babyyu0/Nearby/assets/58788576/d9d4a73f-ae92-4a2b-93e7-5bf5a3b8d6ec" width=720 alt="회원가입" />

#### ◉ 회원가입
1. 프로필 이미지, 이메일 형식 아이디, 비밀번호, 비밀번호 확인, 이름, 관심 지역을 입력합니다.
2. 우측 슬라이드를 통해 웹사이트의 소개를 볼 수 있습니다.
3. 프로필 이미지는 필수 사항이 아닙니다.
4. 아이디 입력 후 중복 확인을 통해 '사용 가능한 아이디'인지 확인합니다.
5. 비밀번호 형식은 비밀번호 칸 오른쪽의 도움말 표시에 마우스를 올리면 확인할 수 있습니다.
6. 입력 형식에 오류가 있을 경우 경고 문구가 노출 됩니다.
7. '회원가입 완료' 버튼을 클릭하면 입력 형식 검사 이후 회원가입이 완료되어 메인 화면으로 이동합니다.

<br/><img src="https://github.com/babyyu0/Nearby/assets/58788576/9fd6f7b0-5930-4c77-b9b2-b562d0f9daf4" width=720 alt="로그인" />

#### ◉ 로그인
1. 등록된 아이디, 비밀번호를 입력합니다.
2. 우측 버튼을 클린하면 회원가입 페이지로 이동합니다.
3. '로그인' 버튼을 클릭하면 회원 존재 여부 검사 후 로그인이 성공 되어 메인 화면으로 이동합니다.
4. 로그인 실패 시 등록된 회원이 아니라는 문구가 노출됩니다.

# 실행 가이드
<h3>
    <img src="https://em-content.zobj.net/source/apple/354/teacher_1f9d1-200d-1f3eb.png" width=16 height=16 alt="선생님 아이콘" />
    &nbsp;로컬 실행 가이드
</h3>

**1. Git Clone**
```git
$ git clone https://github.com/babyyu0/Nearby
```
  
**2. 백엔드 레포지토리 내 `application-local.properties` 생성**

<details>
    <summary>application-local.properties</summary>
    <div markdown="1">
      
```properties
# DB Connection
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url={insert your DB url}
spring.datasource.username={insert your DB user name}
spring.datasource.password={insert your DB password}

# Redis Connection
spring.data.redis.host={insert your DB user url}
spring.data.redis.password={insert your DB password}
spring.data.redis.port={insert your DB port}

# JPA Settings
spring.jpa.properties.hibernate.auto_quote_keyword=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

# JWT Final Fields
token.validity.access={insert access token validity}
token.validity.refresh={insert refresh token validity}
token.secret={insert your secret key}

# URL Final Fields
url.member.profile-img={insert your image path}
url.attraction.img={insert your image path}
url.attraction.api=http://apis.data.go.kr/B551011/KorService1

# Parameter Fields
parameter.attraction.api.key={insert your API key}
parameter.attraction.os={insert your OS}
parameter.attraction.mobile-app=nearby
parameter.attraction.type=json
```

</details>
  
**3. 프론트엔드 레포지토리 내 `.env` 생성**

<details>
    <summary>.env</summary>
    <div markdown="1">
      
```env
REACT_APP_ERROR_MESSAGE = "서버 오류입니다. \n 상황이 지속되면 문의 게시판을 이용해 주세요."
```

</details>
  
**4. 개발 툴로 백엔드 실행**  
**5. 프론트엔드 필요 라이브러리 다운로드**
```node
> npm i
```

**6. 개발 툴로 프론트엔드 실행**
```node
> npm start
```

# 팀원 소개
<table>
  <tr>
    <td align="center"><img src="https://avatars.githubusercontent.com/u/58788576?v=4&size=120" /></td>
    <td align="center"><img src="https://avatars.githubusercontent.com/u/69618305?v=4&size=120" /></td>
  </tr>
  <tr>
    <td align="center"><b>유영 (본인)</b></td>
    <td align="center">박승수</td>
  </tr>
  <tr>
    <td align="center">Full Stack</td>
    <td align="center">Security</td>
  </tr>
  <tr>
    <td>
      <ul>
        <li>회원 관리</li>
        <li>관광지 정보 조회</li>
        <li>전반적인 리팩토링</li>
      </ul>
    </td>
    <td>
      <ul>
        <li>세션 회원 인증</li>
        <li>세션 탈취 및 중복 방지</li>
      </ul>
    </td>
  </tr>
</table>

# 개발 과정
* [Entity, DTO 리팩토링 과정](https://velog.io/@babyyu0/%EA%B7%BC%EC%B2%98-%EB%A6%AC%ED%8C%A9%ED%86%A0%EB%A7%81-1)
* [Entity 복합키 구현 과정](https://velog.io/@babyyu0/%EA%B7%BC%EC%B2%98-%EB%A6%AC%ED%8C%A9%ED%86%A0%EB%A7%81-2)
* [Querydsl 도입 과정](https://velog.io/@babyyu0/Nearby-%EB%A6%AC%ED%8C%A9%ED%86%A0%EB%A7%81-3-Querydsl)
