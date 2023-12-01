import Swal from "sweetalert2";


const getLocation = () => {
  if ("geolocation" in navigator) {
    navigator.geolocation.getCurrentPosition((position) => {
        return { myX: position.coords.latitude, myY: position.coords.longitude };
    });
  } else {
    Swal.fire({
      icon: "info",
      title: "위치 사용을 허용해 주세요.",
    });

    return;
  }
};

export { getLocation };
