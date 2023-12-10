<div align="center">
  <h1 style="display:'inline-block'"> <img src='https://github.com/babyyu0/EnjoyTrip/blob/main/front-workspace/public/image/logo.png' width=50vw> Nearby : 관광지 정보 조회 웹사이트</h1>
</div>

<h3>
    Always Nearby You!&nbsp;
    <img src="https://em-content.zobj.net/source/apple/354/globe-showing-asia-australia_1f30f.png" width=16 height=16 alt="지구본 아이콘" />
</h3>

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

#### 회원
|대분류|기능|Method|URL|권한|
|:-:|:-:|:-:|:-:|:-:|
|회원|아이디 중복 확인|`GET`|<p align='left'>/member/exist/{member-id}</p>|`관리자`  `사용자`|
|회원|회원가입|`POST`|<p align='left'>/member/register</p>|`관리자`  `사용자`|
|회원|로그인|`POST`|<p align='left'>/member/login</p>|`관리자`  `사용자`|
|회원|로그아웃|`POST`|<p align='left'>/member/logout</p>|`관리자`  `사용자`|

#### 행정구역
|대분류|기능|Method|URL|권한|
|:-:|:-:|:-:|:-:|:-:|
|행정구역|행정구역 리스트 조회|`GET`|<p align='left'>/sido</p>|`관리자`  `사용자`|
|행정구역|공공데이터 행정구역 불러오기|`GET`|<p align='left'>/sido/refresh</p>|`관리자`|
|행정구역|하위 행정구역 리스트 조회|`GET`|<p align='left'>/gugun</p>|`관리자`  `사용자`|
|행정구역|공공데이터 하위 행정구역 불러오기|`GET`|<p align='left'>/gugun/refresh</p>|`관리자`|

#### 관광지
|대분류|기능|Method|URL|권한|
|:-:|:-:|:-:|:-:|:-:|
|관광지|가까운 관광지 리스트 조회|`GET`|<p align='left'>/attraction/nearest</p>|`관리자`  `사용자`|
|관광지|인기 관광지 리스트 조회|`GET`|<p align='left'>/attraction/popular</p>|`관리자`  `사용자`|
|관광지|공공데이터 관광 콘텐츠 타입 불러오기|`GET`|<p align='left'>/attraction/content-type/refresh</p>|`관리자`|
|관광지|공공데이터 관광지 불러오기|`GET`|<p align='left'>/attraction/refresh</p>|`관리자`|

<h3>
    <img src="https://em-content.zobj.net/source/apple/354/framed-picture_1f5bc-fe0f.png" width=16 height=16 alt="퍼즐 아이콘" />
    &nbsp;API 명세
</h3>

|대분류|화면|URL|
|:-:|:-:|:-:|
|메인|메인|<p align='left'><a href='http://localhost:3000/'>/</a></p>|
|회원|회원가입|<p align='left'><a href='http://localhost:3000/register'>/register</a></p>|
|회원|로그인|<p align='left'><a href='http://localhost:3000/login'>/login</a></p>|


