import { api } from "./common/ApiService";
import toast from "react-hot-toast";

const getNearestList = async (data) => {
    try {
        const response = await api.get(`/attraction/nearest?latitude=${data.mapX}&longitude=${data.mapY}`);
        return response.data;
    } catch(error) {
        toast.error((error.response) ? error.response.data.message : process.env.REACT_APP_ERROR_MESSAGE, {duration: 1000});
        return;
    }   
}

const getPopularList = async (data) => {
    try {
        const response = await api.get(`/attraction/popular?latitude=${data.mapX}&longitude=${data.mapY}`);
        return response.data;
    } catch (error) {
        toast.error((error.response) ? error.response.data.message : process.env.REACT_APP_ERROR_MESSAGE, {duration: 1000});
        return;
    }   
}

export { getNearestList, getPopularList };