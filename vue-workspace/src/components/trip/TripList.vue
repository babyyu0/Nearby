<template>
	<b-container>
		<h1 class="mt-5">관광지 정보 목록</h1>
		<div class="mt-5" align="center">
			<b-row>
				<b-col>
					<b-select v-model="sidoCode" @change="changeGugun">
						<b-select-option v-for="(sido, index) in sidos" :key="index" :value="sido.sidoCode"> {{ sido.sidoName }}</b-select-option>
					</b-select>
				</b-col>
				<b-col>
					<b-select v-model="gugunCode" @change="getTripList">
						<b-select-option v-for="(gugun, index) in guguns" :key="index" :value="gugun.gugunCode"> {{ gugun.gugunName }}</b-select-option>
					</b-select>
				</b-col>
				<b-col>
					<b-select v-model="typeCode" @change="getTripList">
						<b-select-option v-for="(type, index) in types" :key="index" :value="type.contentCode"> {{ type.contentName }}</b-select-option>
					</b-select>
				</b-col>
			</b-row>
			<div class="mt-3">
				<trip-map :tripList="tripList" :latitude="selLatitude" :longitude="selLongitude"></trip-map>
			</div>
			<div class="mt-3">
				<div v-if="tripList != null && tripList.length > 0">
					<b-table id="trip-table" :fields="fields" :items="tripList" :per-page="perPage" :current-page="currentPage">
						<template #cell(title)="row">
							<span class="text-truncate" @click="$router.push(`/trip/info/${row.item.contentId}`);" style="cursor:pointer"> {{ row.item.title }} </span>
						</template>
						<template #cell(addr)="row">
							<span @click="setCenter(row.item.latitude, row.item.longitude)" style="cursor:pointer">{{ row.item.addr }} {{ row.item.addr2 }}</span>
						</template>
						<template #cell(distance)="row">
							{{ row.item.distance.toFixed(2) }} km
						</template>
					</b-table>
					<b-pagination
						v-model="currentPage"
						:total-rows="tripList.length"
						:per-page="perPage"
						aria-controls="trip-table"
						align="center"
					></b-pagination>
				</div>
				<div v-else>관광지가 존재하지 않습니다.</div>
			</div>
		</div>
	</b-container>
</template>
<script>
import TripMap from "@/components/trip/TripMap.vue";

export default {
	components: {
		TripMap
	},
	data() {
		return {
			perPage: 10,
			currentPage: 1,
			sidos: [],
			guguns: [], 
			types: [],
			sidoCode: 1,
			gugunCode: 1,
			typeCode: 12,
			latitude: 0,
			longitude: 0,
			selLatitude: 0,
			selLongitude: 0,
			fields: [
				{ key: "contentId", label: "번호" },
				{ key: "title", 	label: "이름" },
				{ key: "addr",		label: "주소" },
				{ key: "distance",	label: "거리",	sortable: true },
			],
			tripList: null
		};
	},
	methods: {
		getRegion() {
			this.$axios({
				url: "trip/get-region",
			}).then((response) => {
				this.sidos = response.data.sido;
				this.guguns = response.data.gugun;
				this.types = response.data.tripType;
			});
		},
		changeGugun() {
			this.$axios({
				url: "trip/get-gugun",
				data: { sidoCode: this.sidoCode },
			}).then((response) => {
				this.guguns = response.data;
				this.gugunCode = 1;
			});
			this.getTripList();
		},
		getTripList() {
			this.$axios({
				url: "trip/list",
				data: { sidoCode: this.sidoCode, gugunCode: this.gugunCode, contentTypeId: this.typeCode },
			}).then((response) => {
				this.tripList = response.data;
				this.getDistanceFromLatLonInKm();
			});
		},
		getDistanceFromLatLonInKm() {
			function deg2rad(deg) {
				return deg * (Math.PI/180)
			}

			var R = 6371; // Radius of the earth in km
			for (var index in this.tripList) {
				//console.log(this.tripList[index]);

				var dLat = deg2rad(this.latitude - this.tripList[index].latitude);  // deg2rad below
				var dLon = deg2rad(this.longitude - this.tripList[index].longitude);
				var a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(deg2rad(this.tripList[index].latitude)) * Math.cos(deg2rad(this.latitude)) * Math.sin(dLon/2) * Math.sin(dLon/2);
				var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
				var d = R * c; // Distance in km
				this.tripList[index].distance = d;
			}
		},
		setCenter(latitude, longitude) {
			this.selLatitude = latitude;
			this.selLongitude = longitude;
		}
	},
	async created() {
        await navigator.geolocation.getCurrentPosition(pos => {
            this.latitude = pos.coords.latitude;
            this.longitude = pos.coords.longitude;
        }, err => {
            console.log(err.message);
        });
		this.getRegion();
		this.getTripList();
	}

}
</script>