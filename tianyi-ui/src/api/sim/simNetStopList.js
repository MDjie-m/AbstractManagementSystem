import request from '@/utils/request'

// 查询断网清单列表
export function listSimNetStopList(query) {
  return request({
    url: '/sim/simNetStopList/list',
    method: 'get',
    params: query
  })
}

// 查询断网清单详细
export function getSimNetStopList(provId) {
  return request({
    url: '/sim/simNetStopList/' + provId,
    method: 'get'
  })
}

// 新增断网清单
export function addSimNetStopList(data) {
  return request({
    url: '/sim/simNetStopList',
    method: 'post',
    data: data
  })
}

// 修改断网清单
export function updateSimNetStopList(data) {
  return request({
    url: '/sim/simNetStopList',
    method: 'put',
    data: data
  })
}

// 删除断网清单
export function delSimNetStopList(provId) {
  return request({
    url: '/sim/simNetStopList/' + provId,
    method: 'delete'
  })
}
