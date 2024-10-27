import request from '@/utils/request'

// 查询课程目录列表
export function listMenu(query) {
  return request({
    url: '/yk/menu/list',
    method: 'get',
    params: query
  })
}

// 查询课程目录详细
export function getMenu(id) {
  return request({
    url: '/yk/menu/' + id,
    method: 'get'
  })
}

// 新增课程目录
export function addMenu(data) {
  return request({
    url: '/yk/menu',
    method: 'post',
    data: data
  })
}

// 修改课程目录
export function updateMenu(data) {
  return request({
    url: '/yk/menu',
    method: 'put',
    data: data
  })
}

// 删除课程目录
export function delMenu(id) {
  return request({
    url: '/yk/menu/' + id,
    method: 'delete'
  })
}
