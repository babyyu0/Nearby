import { api } from "../common/ApiService";
import Swal from "sweetalert2";
import toast from "react-hot-toast";

const login = async (data) => {
    try {
        const response = await api.post(`/member/login`, JSON.stringify(data));
        return response;
    } catch(error) {
        Swal.fire({
            icon: 'error',
            title: error.response.data
        });

        return;
    }   
}

const register = async (data, profile) => {
    try {
        const response = await api.post(`/member/register`, { "member" : data, profile }, {headers: {"Content-Type": `multipart/form-data`}});
        return response.data;
    } catch(error) {
        toast.error((error.response) ? error.response.data.message : process.env.REACT_APP_ERROR_MESSAGE, {duration: 1000});
        return;
    }
}

const existId = async (data) => {
    const response = await api.get(
        `/member/exist/${data.id}`
    ).catch((error) => {
        Swal.fire({
            icon: 'error',
            title: error.respononse.data
        });
        
        return;
    });

    if(response.status === 200) return response.data;
}

export { login, register, existId };