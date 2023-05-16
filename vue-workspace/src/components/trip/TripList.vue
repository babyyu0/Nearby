<template>
	<b-container>
		<h1 class="mt-5">관광지 정보 목록</h1>
		<div class="mt-5" align="center">
			<trip-map></trip-map>
		</div>
	</b-container>
</template>
<script>
import TripMap from "@/components/trip/TripMap.vue";

export default {
	components: {
		TripMap
	}
}
</script>