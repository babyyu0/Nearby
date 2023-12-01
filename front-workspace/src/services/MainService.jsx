import { api } from "./common/ApiService";
import Swal from "sweetalert2";

const getNearestList = async (data) => {
    try {
        const response = await api.get(`/attraction/nearest?my-x=${data.mapX}&my-y=${data.mapY}`);
        return response.data;
    } catch(error) {
        Swal.fire({
            icon: 'error',
            title: (error.response) ? error.response.data : process.env.REACT_APP_ERROR_MESSAGE
        });

        return;
    }   
}

const getPopularList = async () => {
    try {
        const response = await api.get(`/attraction/popular`);
        return response.data;
    } catch (error) {
        console.log("error: ", process.env.ERROR_MESSAGE);
        Swal.fire({
            icon: 'error',
            title: (error.response) ? error.response.data : process.env.REACT_APP_ERROR_MESSAGE
        });

        return;
    }   
}

export { getNearestList, getPopularList };