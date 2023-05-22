<template>
    <b-container align="left">
        <b-carousel img-width="100%" img-height="480" align="center">
            <b-carousel-slide v-if="trip.firstImage">
                <template #img>
                    <img
                    style="width:100%;height:480px;"
                    :src="trip.firstImage"
                    :alt="trip.title" 
                    >
                </template>
            </b-carousel-slide>
        </b-carousel>
        <h2 class="mt-3"> {{ trip.title }} </h2>
        <span id="addr"> 🧭 {{ trip.addr }} {{ trip.addr2 }}</span>
        <b-tooltip target="addr" placement="topright">우편번호: {{ trip.zipcode }}</b-tooltip>
        <b-card class="mt-3">
            <b-card-text>{{ trip.overview }}</b-card-text>
        </b-card>
    </b-container>
</template>

<script>
import axios from "axios";

export default {
    data() {
        return {
            trip: {
                contentId: this.$route.params.contentId,
            }
        };
    },
    created() {
        console.log(this.trip.contentId);  // 관광지 정보
        
        // 관광지 정보 가져오기
        axios({
            url: "http://localhost:9999/trip/view",
            method: "POST",
            params: { contentId: this.trip.contentId }
        }).then((response) => {
            console.log(response.data);
            this.trip = response.data;
        });
    }
}
</script>

<style scoped>

</style>