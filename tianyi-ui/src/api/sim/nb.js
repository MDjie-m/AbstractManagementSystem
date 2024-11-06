import request from '@/utils/request'

// 查询NB异常清单列表
export function listNb(query) {
  return request({
    url: '/sim/nb/list',
    method: 'get',
    params: query
  })
}

// 查询NB异常清单详细
export function getNb(provId) {
  return request({
    url: '/sim/nb/' + provId,
    method: 'get'
  })
}

// 新增NB异常清单
export function addNb(data) {
  return request({
    url: '/sim/nb',
    method: 'post',
    data: data
  })
}

// 修改NB异常清单
export function updateNb(data) {
  return request({
    url: '/sim/nb',
    method: 'put',
    data: data
  })
}

// 删除NB异常清单
export function delNb(provId) {
  return request({
    url: '/sim/nb/' + provId,
    method: 'delete'
  })
}
