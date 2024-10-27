import request from '@/utils/request'

// 查询课程列表
export function listCourses(query) {
  return request({
    url: '/yk/courses/list',
    method: 'get',
    params: query
  })
}

// 查询课程详细
export function getCourses(id) {
  return request({
    url: '/yk/courses/' + id,
    method: 'get'
  })
}

// 新增课程
export function addCourses(data) {
  return request({
    url: '/yk/courses',
    method: 'post',
    data: data,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}

// 修改课程
export function updateCourses(data) {
  return request({
    url: '/yk/courses',
    method: 'put',
    data: data
  })
}

// 删除课程
export function delCourses(id) {
  return request({
    url: '/yk/courses/' + id,
    method: 'delete'
  })
}
