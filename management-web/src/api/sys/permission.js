import Axios from 'axios'

export function queryTree () {
  return Axios({
    url: 'permission/tree',
    method: 'get'
  })
}

export function listLazy (pid) {
  return Axios({
    url: 'permission/list-lazy',
    method: 'get',
    params: {
      pid: pid
    }
  })
}

export function addOrUpdate (params) {
  return Axios({
    url: 'permission',
    method: 'post',
    data: params
  })
}

export function detail (id) {
  return Axios({
    url: `permission/${id}`,
    method: 'get'
  })
}

export function remove (ids) {
  return Axios({
    url: 'permission',
    method: 'delete',
    params: {
      ids: ids
    }
  })
}
