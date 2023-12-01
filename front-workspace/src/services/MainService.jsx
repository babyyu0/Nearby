import { api } from "./common/ApiService";
import Swal from "sweetalert2";

const getNearestList = async (data) => {
    try {
        const response = await api.get(`/trip/nearest?my-x=${data.mapX}&my-y=${data.mapY}`);
        return response.data;
    } catch(error) {
        Swal.fire({
            icon: 'error',
            title: error.response.data
        });

        return;
    }   
}

export { getNearestList };