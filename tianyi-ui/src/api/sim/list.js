import request from '@/utils/request'

// 查询停机清单列表
export function listList(query) {
  return request({
    url: '/sim/list/list',
    method: 'get',
    params: query
  })
}

// 查询停机清单详细
export function getList(provId) {
  return request({
    url: '/sim/list/' + provId,
    method: 'get'
  })
}

// 新增停机清单
export function addList(data) {
  return request({
    url: '/sim/list',
    method: 'post',
    data: data
  })
}

// 修改停机清单
export function updateList(data) {
  return request({
    url: '/sim/list',
    method: 'put',
    data: data
  })
}

// 删除停机清单
export function delList(provId) {
  return request({
    url: '/sim/list/' + provId,
    method: 'delete'
  })
}
