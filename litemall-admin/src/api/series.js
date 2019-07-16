import request from '@/utils/request'

export function listSeries(query) {
  return request({
    url: '/series/list',
    method: 'get',
    params: query
  })
}

export function listCatL1() {
  return request({
    url: '/series/l1',
    method: 'get'
  })
}

export function createSeries(data) {
  return request({
    url: '/series/create',
    method: 'post',
    data
  })
}

export function readSeries(data) {
  return request({
    url: '/series/read',
    method: 'get',
    data
  })
}

export function updateSeries(data) {
  return request({
    url: '/series/update',
    method: 'post',
    data
  })
}

export function deleteSeries(data) {
  return request({
    url: '/series/delete',
    method: 'post',
    data
  })
}
