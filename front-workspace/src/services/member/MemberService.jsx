import { api } from "../common/ApiSevice";
import Swal from "sweetalert2";

const login = (data) => {
    const response = api.post(`/login`, JSON.stringify(data));

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

export { login, existId };