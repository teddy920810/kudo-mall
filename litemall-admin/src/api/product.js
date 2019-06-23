import request from '@/utils/request'

export function listProduct(query) {
  return request({
    url: '/product/list',
    method: 'get',
    params: query
  })
}

export function deleteProduct(data) {
  return request({
    url: '/product/delete',
    method: 'post',
    data
  })
}

export function createProduct(data) {
  return request({
    url: '/product/create',
    method: 'post',
    data
  })
}

export function detailProduct(id) {
  return request({
    url: '/product/detail',
    method: 'get',
    params: { id }
  })
}

export function editProduct(data) {
  return request({
    url: '/product/update',
    method: 'post',
    data
  })
}

export function listCat() {
  return request({
    url: '/product/cat',
    method: 'get'
  })
}
