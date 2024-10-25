import request from '@/utils/request'

// 查询年级列表
export function listGrades(query) {
  return request({
    url: '/yk/grades/list',
    method: 'get',
    params: query
  })
}

// 查询年级详细
export function getGrades(id) {
  return request({
    url: '/yk/grades/' + id,
    method: 'get'
  })
}

// 新增年级
export function addGrades(data) {
  return request({
    url: '/yk/grades',
    method: 'post',
    data: data
  })
}

// 修改年级
export function updateGrades(data) {
  return request({
    url: '/yk/grades',
    method: 'put',
    data: data
  })
}

// 删除年级
export function delGrades(id) {
  return request({
    url: '/yk/grades/' + id,
    method: 'delete'
  })
}
