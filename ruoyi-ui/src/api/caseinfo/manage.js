import request from '@/utils/request'

// 查询案件信息列表
export function listManage(query) {
  return request({
    url: '/caseinfo/manage/list',
    method: 'get',
    params: query
  })
}

// 查询案件信息详细
export function getManage(caseId) {
  return request({
    url: '/caseinfo/manage/' + caseId,
    method: 'get'
  })
}

// 新增案件信息
export function addManage(data) {
  return request({
    url: '/caseinfo/manage',
    method: 'post',
    data: data
  })
}

// 修改案件信息
export function updateManage(data) {
  return request({
    url: '/caseinfo/manage',
    method: 'put',
    data: data
  })
}

// 删除案件信息
export function delManage(caseId) {
  return request({
    url: '/caseinfo/manage/' + caseId,
    method: 'delete'
  })
}

export function getUndertakeLawyers() {
  return request({
    url: '/module/test/list',
    method: 'get',
  })
}
