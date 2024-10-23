import request from '@/utils/request'

// 查询台桌类型列表
export function listDeskType(query) {
  return request({
    url: '/billiard/deskType/list',
    method: 'get',
    params: query
  })
}
export function listDeskTypeAll(query) {
  return request({
    url: '/billiard/deskType/list/all',
    method: 'get',
    params: query
  })
}

// 查询台桌类型详细
export function getDeskType(deskTypeId) {
  return request({
    url: '/billiard/deskType/' + deskTypeId,
    method: 'get'
  })
}

// 新增台桌类型
export function addDeskType(data) {
  return request({
    url: '/billiard/deskType',
    method: 'post',
    data: data
  })
}

// 修改台桌类型
export function updateDeskType(data) {
  return request({
    url: '/billiard/deskType',
    method: 'put',
    data: data
  })
}

// 删除台桌类型
export function delDeskType(deskTypeId) {
  return request({
    url: '/billiard/deskType/' + deskTypeId,
    method: 'delete'
  })
}
