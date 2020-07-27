import Axios from 'axios'

export function getPageList (pageNum, pageSize, params) {
  return Axios({
    url: 'excel/page',
    method: 'GET',
    params: {
      pageNum: pageNum,
      pageSize: pageSize,
      ...params
    }
  })
}

export function remove (ids) {
  return Axios({
    url: 'excel',
    method: 'delete',
    params: {
      ids: ids
    }
  })
}

/*
export function excelUpload (params) {
  return Axios({
    url: 'excel/upload',
    method: 'post',
    data: params
  })
}
*/

export function downloadModel () {
  return Axios({
    url: 'excel/model',
    method: 'get'
  })
}
