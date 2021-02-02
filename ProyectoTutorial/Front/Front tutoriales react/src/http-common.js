import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:8080/tutorials/",
  headers: {
    "Content-type": "application/json"
  }
});