import request from '@/utils/request'

export function getSite() {
  return request({
    url: '/site',
    method: 'get'
  })
}

export function updateSite(data) {
  return request({
    url: '/site',
    method: 'post',
    data
  })
}
