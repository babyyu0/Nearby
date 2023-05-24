import axios from 'axios';

function ServerApi() {
    const http = axios.create({
        baseURL: "http://localhost:9999/",
        method: "POST",
        headers: {
            "Content-Type": "application/json;charset=utf-8"
        },
        withCredentials: true
    });

    return http;
}

export { ServerApi };