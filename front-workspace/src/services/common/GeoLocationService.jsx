import Swal from "sweetalert2";

const getLocation = async () => {
  if ("geolocation" in navigator) {
    return new Promise((resolve, rejected) => {
      navigator.geolocation.getCurrentPosition((position) => {
        resolve({ mapX: position.coords.latitude, mapY: position.coords.longitude });
      }, rejected);

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
