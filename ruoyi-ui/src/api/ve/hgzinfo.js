import request from '@/utils/request'

// 查询国家合格证列表
export function listHgzinfo(query) {
  return request({
    url: '/ve/hgzinfo/list',
    method: 'get',
    params: query
  })
}

// 查询国家合格证详细
export function getHgzinfo(id) {
  return request({
    url: '/ve/hgzinfo/' + id,
    method: 'get'
  })
}

// 新增国家合格证
export function addHgzinfo(data) {
  return request({
    url: '/ve/hgzinfo',
    method: 'post',
    data: data
  })
}

// 修改国家合格证
export function updateHgzinfo(data) {
  return request({
    url: '/ve/hgzinfo',
    method: 'put',
    data: data
  })
}

// 删除国家合格证
export function delHgzinfo(id) {
  return request({
    url: '/ve/hgzinfo/' + id,
    method: 'delete'
  })
}
