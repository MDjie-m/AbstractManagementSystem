import request from '@/utils/request'

// 查询叶片管理列表
export function listinspection(query) {
  return request({
    url: '/windSys/inspection/list',
    method: 'get',
    params: query
  })
}

// 查询叶片管理详细
export function getinspection(bpId) {
  return request({
    url: '/windSys/inspection/' + bpId,
    method: 'get'
  })
}

// 新增叶片管理
export function addinspection(data) {
  return request({
    url: '/windSys/inspection',
    method: 'post',
    data: data
  })
}

// 修改叶片管理
export function updateinspection(data) {
  return request({
    url: '/windSys/inspection',
    method: 'put',
    data: data
  })
}

// 删除叶片管理
export function delinspection(bpId) {
  return request({
    url: '/windSys/inspection/' + bpId,
    method: 'delete'
  })
}
