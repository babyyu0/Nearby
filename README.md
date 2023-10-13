<div align="center">
  <h1 style="display:'inline-block'"> <img src='https://github.com/babyyu0/EnjoyTrip/blob/main/front-workspace/public/image/logo.png' width=50vw> 근처 : 관광지 정보 제공 웹사이트</h1>
</div>

'근처'는 **관광지의 정보**를 제공하고 **지도에 노출**해주는 웹사이트 입니다.<br>
사용자들은 저희 사이트를 통해 시군구별 **관광지의 위치**를 확인하고, **본인의 위치**와 관광지의 위치가 얼마나 차이 나는지 조회할 수 있습니다.
<br><br>
### 🧳 프로젝트 기간
**🛠️ 개발**: 2023. 05. 17 ~ 2023. 05. 26 &nbsp;&nbsp;&nbsp;&nbsp; **🛠️ 리팩토링**: 2023. 09. 27 ~ 진행중
<br><br>
### 🧳 구성원 및 역할
|<img src='https://avatars.githubusercontent.com/u/58788576?v=4' width=100vw>|<img src='https://avatars.githubusercontent.com/u/69618305?v=4' width=100vw>|
|:-:|:-:|
|유영 (팀장)|박승수|
|풀스택|보안|
<br>

# 기능

### API
|대분류|기능|Method|URL|
|:-:|:-:|:-:|:-:|
|회원|아이디 중복 확인|`GET`|<p align='left'>/member/exist/{id}</p>|
|회원|회원가입|`POST`|<p align='left'>/member/register</p>|
|회원|로그인|`POST`|<p align='left'>/member/login</p>|
|관광지|모든 지역구 리스트 조회|`GET`|<p align='left'>/trip/city</p>|

### URL
|대분류|화면|URL|
|:-:|:-:|:-:|
|메인|메인|<p align='left'><a href='http://localhost:3000/'>/</a></p>|
|회원|회원가입|<p align='left'><a href='http://localhost:3000/register'>/register</a></p>|
|회원|로그인|<p align='left'><a href='http://localhost:3000/login'>/login</a></p>|
