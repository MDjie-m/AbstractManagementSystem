import request from '@/utils/request'

// 查询叶片管理列表
export function listPart(query) {
  return request({
    url: '/windSys/part/list',
    method: 'get',
    params: query
  })
}

// 查询叶片管理详细
export function getPart(bpId) {
  return request({
    url: '/windSys/part/' + bpId,
    method: 'get'
  })
}

// 新增叶片管理
export function addPart(data) {
  return request({
    url: '/windSys/part',
    method: 'post',
    data: data
  })
}

// 修改叶片管理
export function updatePart(data) {
  return request({
    url: '/windSys/part',
    method: 'put',
    data: data
  })
}

// 删除叶片管理
export function delPart(bpId) {
  return request({
    url: '/windSys/part/' + bpId,
    method: 'delete'
  })
}
