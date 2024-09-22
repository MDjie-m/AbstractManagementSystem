import request from '@/utils/request'

// 查询门店班次列表
export function listSchedule(query) {
  return request({
    url: '/billiard/schedule/list',
    method: 'get',
    params: query
  })
}

// 查询门店班次详细
export function getSchedule(storeScheduleId) {
  return request({
    url: '/billiard/schedule/' + storeScheduleId,
    method: 'get'
  })
}

// 新增门店班次
export function addSchedule(data) {
  return request({
    url: '/billiard/schedule',
    method: 'post',
    data: data
  })
}

// 修改门店班次
export function updateSchedule(data) {
  return request({
    url: '/billiard/schedule',
    method: 'put',
    data: data
  })
}

// 删除门店班次
export function delSchedule(storeScheduleId) {
  return request({
    url: '/billiard/schedule/' + storeScheduleId,
    method: 'delete'
  })
}
