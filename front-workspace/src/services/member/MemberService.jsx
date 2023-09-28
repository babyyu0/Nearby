import { api } from "../common/ApiSevice";

const login = (data) => {
    const response = api.post(`/login`, JSON.stringify(data));
    
    return response;
}

export { login };