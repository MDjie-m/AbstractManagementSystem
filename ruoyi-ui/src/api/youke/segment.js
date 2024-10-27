import request from '@/utils/request'

// 查询年段列表
export function listSegment(query) {
  return request({
    url: '/yk/segment/list',
    method: 'get',
    params: query
  })
}

// 查询年段详细
export function getSegment(id) {
  return request({
    url: '/yk/segment/' + id,
    method: 'get'
  })
}

// 新增年段
export function addSegment(data) {
  return request({
    url: '/yk/segment',
    method: 'post',
    data: data
  })
}

// 修改年段
export function updateSegment(data) {
  return request({
    url: '/yk/segment',
    method: 'put',
    data: data
  })
}

// 删除年段
export function delSegment(id) {
  return request({
    url: '/yk/segment/' + id,
    method: 'delete'
  })
}
