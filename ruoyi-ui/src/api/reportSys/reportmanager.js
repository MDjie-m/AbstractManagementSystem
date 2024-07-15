import request from '@/utils/request'

// 查询风机管理列表
export function listWind(query) {
  return request({
    url: '/windSys/wind/list',
    method: 'get',
    params: query
  })
}

// 查询风机管理详细
export function getWind(wId) {
  return request({
    url: '/windSys/wind/' + wId,
    method: 'get'
  })
}

// 新增风机管理
export function addWind(data) {
  return request({
    url: '/windSys/wind',
    method: 'post',
    data: data
  })
}

// 修改风机管理
export function updateWind(data) {
  return request({
    url: '/windSys/wind',
    method: 'put',
    data: data
  })
}

// 删除风机管理
export function delWind(wId) {
  return request({
    url: '/windSys/wind/' + wId,
    method: 'delete'
  })
}
