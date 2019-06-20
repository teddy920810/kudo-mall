import request from '@/utils/request'

export function listCustomized(query) {
  return request({
    url: '/customized/list',
    method: 'get',
    params: query
  })
}

export function createCustomized(data) {
  return request({
    url: '/customized/create',
    method: 'post',
    data
  })
}

export function readCustomized(data) {
  return request({
    url: '/customized/read',
    method: 'get',
    data
  })
}

export function updateCustomized(data) {
  return request({
    url: '/customized/update',
    method: 'post',
    data
  })
}

export function deleteBrand(data) {
  return request({
    url: '/brand/delete',
    method: 'post',
    data
  })
}
