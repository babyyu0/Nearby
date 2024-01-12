import { api } from "../common/ApiService";
import Swal from "sweetalert2";

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

const register = (data, profile) => {
    const response = api.post(
        `/member/register`,
        { "member" : data, profile },
        {headers : {
            "Content-Type": `multipart/form-data`
        }}).catch((error) => {
            Swal.fire({
                icon: 'error',
                title: error.response.data
            });
            
            return;
        });
    
        return response;
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