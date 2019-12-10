import Axios from "axios"

export function login(userform) {
  return Axios({
   method: 'post',
   url: '/user/login',
   data: userform
   })
}
