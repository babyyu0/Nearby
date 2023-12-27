import { api } from "./ApiService";
import toast from "react-hot-toast";

const searchWord = async (data) => {
    try {
        const response = await api.get(`/attraction/search?word=${data.word}`);
        return response.data;
    } catch(error) {
        toast.error((error.response) ? error.response.data.message : process.env.REACT_APP_ERROR_MESSAGE);
        return;
    }   
};

export { searchWord };