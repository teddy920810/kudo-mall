import request from '@/utils/request'

export function listMessage(query) {
  return request({
    url: '/message/list',
    method: 'get',
    params: query
  })
}
