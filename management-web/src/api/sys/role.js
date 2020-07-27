import Axios from 'axios'

export function getRoleList (param) {
  return Axios({
    url: 'role/list',
    method: 'get',
    params: param
  })
}

export function getPageList (pageNum, pageSize, params) {
  return Axios({
    url: 'role/page',
    method: 'GET',
    params: {
      pageNum: pageNum,
      pageSize: pageSize,
      ...params
    }
  })
}

export function addOrUpdate (params) {
  return Axios({
    url: 'role',
    method: 'post',
    data: params
  })
}

export function detail (id) {
  return Axios({
    url: `role/${id}`,
    method: 'get'
  })
}

export function remove (ids) {
  return Axios({
    url: 'role',
    method: 'delete',
    params: {
      ids: ids
    }
  })
}
