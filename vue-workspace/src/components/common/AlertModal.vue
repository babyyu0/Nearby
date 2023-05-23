<template>
  <b-modal id="bv-modal" title="알림" ok-only centered>
    <p class="my-4">{{ modalMsg }}</p>
  </b-modal>
</template>

<script>
export default {
  props: {
    modalMsg: String,
  },
};
</script>
