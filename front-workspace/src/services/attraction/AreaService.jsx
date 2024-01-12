import { api } from "../common/ApiService";
import toast from "react-hot-toast";

const getArea = async () => {
    try {
        const sido = await getSido();
        const gugun = await getGugun();
        return { sido, gugun };
    } catch(error) {
        toast.error((error.response) ? error.response.data.message : process.env.REACT_APP_ERROR_MESSAGE, {duration: 1000});
        return;
    } 
}
const getSido = async () => {
    try {
        const response = await api.get(`/sido`);
        return response.data;
    } catch(error) {
        toast.error((error.response) ? error.response.data.message : process.env.REACT_APP_ERROR_MESSAGE, {duration: 1000});
        return;
    } 
}
const getGugun = async () => {
    try {
        const response = await api.get(`/gugun`);
        return response.data;
    } catch(error) {
        toast.error((error.response) ? error.response.data.message : process.env.REACT_APP_ERROR_MESSAGE, {duration: 1000});
        return;
    } 
}

export { getArea, getSido, getGugun };