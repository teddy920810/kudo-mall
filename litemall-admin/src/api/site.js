import request from '@/utils/request'

export function getSite() {
  return request({
    url: '/site/list',
    method: 'get'
  })
}

export function updateSite(data) {
  return request({
    url: '/site/update',
    method: 'post',
    data
  })
}
