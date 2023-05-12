<template><div>
  <b-navbar toggleable="lg" type="light" variant="light">
    <b-navbar-brand to="/">✈️ Enjoy Trip</b-navbar-brand>

    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

    <b-collapse id="nav-collapse" is-nav>
      <b-navbar-nav>
        <b-nav-item to="/trip">관광지 보기</b-nav-item>
      </b-navbar-nav>

      <!-- Right aligned nav items -->
      <b-navbar-nav class="ml-auto" v-if="member.id">
        <b-nav-item-dropdown right>
          <!-- Using 'button-content' slot -->
          <template #button-content>
            <b-avatar variant="info" src="https://placekitten.com/300/300" class="mr-2"></b-avatar> {{ member.name }} 님
          </template>
          <b-dropdown-item to="/user/info">회원 정보</b-dropdown-item>
          <b-dropdown-item to="/user/logout">로그아웃</b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>
      <b-navbar-nav class="ml-auto" v-else>
        <b-nav-item to="/member/login">로그인</b-nav-item>
        <b-nav-item to="/member/regist">회원가입</b-nav-item>
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
</div>
</template>

<script>
export default {
  data() {
    return {
      member: {
        id: "",
        name: ""
      }
    }
  }
}
</script>