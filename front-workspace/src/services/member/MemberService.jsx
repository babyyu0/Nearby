import { api } from "../common/ApiSevice";

const login = (data) => {
    const response = api.post(`/login`, JSON.stringify(data));

    return response;
}

const existId = async (data) => {
    const response = await api.get(`/member/exist/${data.id}`);

    return response;
}

export { login, existId };