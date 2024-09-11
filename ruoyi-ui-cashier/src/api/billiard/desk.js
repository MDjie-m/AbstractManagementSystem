import request from '@/utils/request'

// 查询球桌列表
export function listDesk(query) {
  return request({
    url: '/billiard/desk/list',
    method: 'get',
    params: query
  })
}

// 查询球桌详细
export function getDesk(deskId) {
  return request({
    url: '/billiard/desk/' + deskId,
    method: 'get'
  })
}

// 新增球桌
export function addDesk(data) {
  return request({
    url: '/billiard/desk',
    method: 'post',
    data: data
  })
}

// 修改球桌
export function updateDesk(data) {
  return request({
    url: '/billiard/desk',
    method: 'put',
    data: data
  })
}

// 删除球桌
export function delDesk(deskId) {
  return request({
    url: '/billiard/desk/' + deskId,
    method: 'delete'
  })
}
