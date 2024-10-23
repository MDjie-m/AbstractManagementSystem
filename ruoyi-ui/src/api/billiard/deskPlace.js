import request from '@/utils/request'

// 查询台桌区域列表
export function listDeskPlace(query) {
  return request({
    url: '/billiard/deskPlace/list',
    method: 'get',
    params: query
  })
}
export function listDeskPlaceAll(query) {
  return request({
    url: '/billiard/deskPlace/list/all',
    method: 'get',
    params: query
  })
}
// 查询台桌区域详细
export function getDeskPlace(deskPlaceId) {
  return request({
    url: '/billiard/deskPlace/' + deskPlaceId,
    method: 'get'
  })
}

// 新增台桌区域
export function addDeskPlace(data) {
  return request({
    url: '/billiard/deskPlace',
    method: 'post',
    data: data
  })
}

// 修改台桌区域
export function updateDeskPlace(data) {
  return request({
    url: '/billiard/deskPlace',
    method: 'put',
    data: data
  })
}

// 删除台桌区域
export function delDeskPlace(deskPlaceId) {
  return request({
    url: '/billiard/deskPlace/' + deskPlaceId,
    method: 'delete'
  })
}
