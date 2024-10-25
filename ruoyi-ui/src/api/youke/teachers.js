import request from '@/utils/request'

// 查询教师列表
export function listTeachers(query) {
  return request({
    url: '/yk/teachers/list',
    method: 'get',
    params: query
  })
}

// 查询教师详细
export function getTeachers(id) {
  return request({
    url: '/yk/teachers/' + id,
    method: 'get'
  })
}

// 新增教师
export function addTeachers(data) {
  return request({
    url: '/yk/teachers',
    method: 'post',
    data: data
  })
}

// 修改教师
export function updateTeachers(data) {
  return request({
    url: '/yk/teachers',
    method: 'put',
    data: data
  })
}

// 删除教师
export function delTeachers(id) {
  return request({
    url: '/yk/teachers/' + id,
    method: 'delete'
  })
}
