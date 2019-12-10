/* eslint-disable */
import Axios from "axios";
export default {
  getUserList: function (params) {
    return Axios.get('/api/user/list', params);
  }
}
