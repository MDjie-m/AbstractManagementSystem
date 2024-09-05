import request from '@/utils/request'

// 查询门店员工列表
export function listUser(query) {
  return request({
    url: '/billiard/user/list',
    method: 'get',
    params: query
  })
}

// 查询门店员工详细
export function getUser(storeUserId) {
  return request({
    url: '/billiard/user/' + storeUserId,
    method: 'get'
  })
}

// 新增门店员工
export function addUser(data) {
  return request({
    url: '/billiard/user',
    method: 'post',
    data: data
  })
}

// 修改门店员工
export function updateUser(data) {
  return request({
    url: '/billiard/user',
    method: 'put',
    data: data
  })
}

// 删除门店员工
export function delUser(storeUserId) {
  return request({
    url: '/billiard/user/' + storeUserId,
    method: 'delete'
  })
}
