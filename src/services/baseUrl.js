import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:9191/",
  headers: {
    "content-type": "application/json",
  },
});