<template>
  <router-view />
</template>

<script>

export default {
  name: "MemberView",
  methods: {
    setMember(id) {
      this.$emit("setMember", id);
    }
  }
};
</script>
