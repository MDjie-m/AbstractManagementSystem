import request from '@/utils/request'

// 查询球桌价格列表
export function listDeskPrice(query) {
  return request({
    url: '/billiard/deskPrice/list',
    method: 'get',
    params: query
  })
}

// 查询球桌价格详细
export function getDeskPrice(deskPriceId) {
  return request({
    url: '/billiard/deskPrice/' + deskPriceId,
    method: 'get'
  })
}

// 新增球桌价格
export function addDeskPrice(data) {
  return request({
    url: '/billiard/deskPrice',
    method: 'post',
    data: data
  })
}

// 修改球桌价格
export function updateDeskPrice(data) {
  return request({
    url: '/billiard/deskPrice',
    method: 'put',
    data: data
  })
}

// 删除球桌价格
export function delDeskPrice(deskPriceId) {
  return request({
    url: '/billiard/deskPrice/' + deskPriceId,
    method: 'delete'
  })
}
