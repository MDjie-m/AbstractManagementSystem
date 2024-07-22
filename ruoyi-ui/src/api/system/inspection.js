import request from '@/utils/request'

// 查询考察情况列表
export function listInspection(query) {
  return request({
    url: '/system/inspection/list',
    method: 'get',
    params: query
  })
}

// 查询考察情况详细
export function getInspection(inspectionId) {
  return request({
    url: '/system/inspection/' + inspectionId,
    method: 'get'
  })
}

// 新增考察情况
export function addInspection(data) {
  return request({
    url: '/system/inspection',
    method: 'post',
    data: data
  })
}

// 修改考察情况
export function updateInspection(data) {
  return request({
    url: '/system/inspection',
    method: 'put',
    data: data
  })
}

// 删除考察情况
export function delInspection(inspectionId) {
  return request({
    url: '/system/inspection/' + inspectionId,
    method: 'delete'
  })
}
