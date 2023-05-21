<template>
  <div>
    <div id="map"></div>
    <b-jumbotron header="Enjoy Your Trip ✈️" lead="PyP Trip과 함께 여행지를 찾아보세요!" style="background-color: white;">
    </b-jumbotron>
    <b-container>
      <b-carousel id="carousel-fade" style="text-shadow: 0px 0px 2px #000" fade indicators img-width="1024" img-height="480">
        <!-- 추후 이미지 추가 예정 -->
        <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=10"></b-carousel-slide>
        <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=10"></b-carousel-slide>
        <b-carousel-slide img-src="https://picsum.photos/1024/480/?image=10"></b-carousel-slide>
      </b-carousel>
      <div class="mt-4">
        <h4>
          춘천으로 갈까요?
        </h4>
          <b-button class="mt-2">여행지 보기</b-button>
      </div>
    </b-container>
  </div>
</template>

<script>
export default {
  name: 'HomeView',
}
</script>
