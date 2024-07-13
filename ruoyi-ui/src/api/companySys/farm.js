import request from '@/utils/request'

// 查询风场管理列表
export function listFarm(query) {
  return request({
    url: '/companySys/farm/list',
    method: 'get',
    params: query
  })
}

// 查询风场管理详细
export function getFarm(wId) {
  return request({
    url: '/companySys/farm/' + wId,
    method: 'get'
  })
}

// 新增风场管理
export function addFarm(data) {
  return request({
    url: '/companySys/farm',
    method: 'post',
    data: data
  })
}

// 修改风场管理
export function updateFarm(data) {
  return request({
    url: '/companySys/farm',
    method: 'put',
    data: data
  })
}

// 删除风场管理
export function delFarm(wId) {
  return request({
    url: '/companySys/farm/' + wId,
    method: 'delete'
  })
}
