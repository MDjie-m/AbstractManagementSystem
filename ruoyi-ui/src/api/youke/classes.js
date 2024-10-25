import request from '@/utils/request'

// 查询班级列表
export function listClasses(query) {
  return request({
    url: '/yk/classes/list',
    method: 'get',
    params: query
  })
}

// 查询班级详细
export function getClasses(id) {
  return request({
    url: '/yk/classes/' + id,
    method: 'get'
  })
}

// 新增班级
export function addClasses(data) {
  return request({
    url: '/yk/classes',
    method: 'post',
    data: data
  })
}

// 修改班级
export function updateClasses(data) {
  return request({
    url: '/yk/classes',
    method: 'put',
    data: data
  })
}

// 删除班级
export function delClasses(id) {
  return request({
    url: '/yk/classes/' + id,
    method: 'delete'
  })
}
