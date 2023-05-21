<template>
    <b-nav tabs fill class="mt-5">
      <b-nav-item to="/board/free" v-bind="{ active: (type == 'free')}">자유 게시판</b-nav-item>
      <b-nav-item to="/board/location" v-bind="{ active: (type == 'location')}">거리별 게시판</b-nav-item>
    </b-nav>
</template>

<script>
export default {
    props: {
        type: String
    }
}
</script>