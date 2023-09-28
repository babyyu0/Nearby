import Axios from "axios";

const BASE_URL = 'http://localhost:8080';

const api = Axios.create({
    baseURL: BASE_URL,
    headers: {
      "Content-Type": "application/json;charset=UTF-8",
      "Access-Control-Allow-Origin": 'http://localhost:3000/',
    }
  });

export { api };