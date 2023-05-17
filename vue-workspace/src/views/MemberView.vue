<template>
  <router-view @login="login" />
</template>

<script>
import axios from "axios";

export default {
  name: "MemberView",
  methods: {
    login(member) {
      axios({
        url: "http://localhost:9999/member/login",
        method: "post",
        params: member,
      }).then((response) => {
        if (response.data == "") member.id = "";
        this.$emit("setMember", {id: member.id, name: response.data});
      });
    },
  },
};
</script>
