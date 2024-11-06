import request from '@/utils/request'

// 查询套餐到期预警列表
export function listPack_exp(query) {
  return request({
    url: '/sim/pack_exp/list',
    method: 'get',
    params: query
  })
}

// 查询套餐到期预警详细
export function getPack_exp(provId) {
  return request({
    url: '/sim/pack_exp/' + provId,
    method: 'get'
  })
}

// 新增套餐到期预警
export function addPack_exp(data) {
  return request({
    url: '/sim/pack_exp',
    method: 'post',
    data: data
  })
}

// 修改套餐到期预警
export function updatePack_exp(data) {
  return request({
    url: '/sim/pack_exp',
    method: 'put',
    data: data
  })
}

// 删除套餐到期预警
export function delPack_exp(provId) {
  return request({
    url: '/sim/pack_exp/' + provId,
    method: 'delete'
  })
}
