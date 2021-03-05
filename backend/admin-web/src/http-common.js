// https://bezkoder.com/react-material-ui-examples-crud/#Import_Material_UI
import axios from "axios";

export default axios.create({
  baseURL: "http://142.58.21.129/api",
  headers: {
    "Content-type": "application/json"
  }
});

