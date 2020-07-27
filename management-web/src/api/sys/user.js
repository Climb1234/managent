import Axios from 'axios'

export function login (userform) {
  return Axios({
    method: 'post',
    url: 'auth/login',
    data: userform
  })
}

export function getUserInfo (token) {
  return Axios({
    method: 'get',
    url: 'user/info?token=' + token
  })
}

export function logout () {
  return Axios({
    method: 'post',
    url: 'user/logout'
  })
}

export function getUserList (pageNum, pageSize, params) {
  return Axios({
    url: 'user/list',
    method: 'GET',
    params: {
      pageNum: pageNum,
      pageSize: pageSize,
      ...params
    }
  })
}

export function deleteUser (id) {
  return Axios({
    url: `user/${id}`,
    method: 'delete'
  })
}

export function getUser (id) {
  return Axios({
    url: `user/${id}`,
    method: 'get'
  })
}

export function addOrUpdateUser (params) {
  return Axios({
    url: 'user',
    method: 'post',
    data: params
  })
}
