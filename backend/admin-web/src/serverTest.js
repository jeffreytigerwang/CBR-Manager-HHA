const axios = require("axios");

let req = axios.create({
  baseURL: "http://142.58.21.129/api",
  headers: {
    "Content-type": "application/json"
  }
});

req.get('/visits/').then(res => {
  console.log(res.data);
});
