import request from '@/utils/request'

// 查询套餐长期不使用预警列表
export function listNonuse(query) {
  return request({
    url: '/sim/nonuse/list',
    method: 'get',
    params: query
  })
}

// 查询套餐长期不使用预警详细
export function getNonuse(provId) {
  return request({
    url: '/sim/nonuse/' + provId,
    method: 'get'
  })
}

// 新增套餐长期不使用预警
export function addNonuse(data) {
  return request({
    url: '/sim/nonuse',
    method: 'post',
    data: data
  })
}

// 修改套餐长期不使用预警
export function updateNonuse(data) {
  return request({
    url: '/sim/nonuse',
    method: 'put',
    data: data
  })
}

// 删除套餐长期不使用预警
export function delNonuse(provId) {
  return request({
    url: '/sim/nonuse/' + provId,
    method: 'delete'
  })
}
