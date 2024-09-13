import request from '@/utils/request'

// 查询门店列表
export function listStore(query) {
  return request({
    url: '/billiard/store/list',
    method: 'get',
    params: query
  })
}

// 查询所有门店列表
export function listStoreAll() {
  return request({
    url: '/billiard/store/list/all',
    method: 'get',
  })
}
export function listAllStore() {
  return request({
    url: '/billiard/store/list/all',
    method: 'get',
    params: null
  })
}
// 查询门店详细
export function getStore(storeId) {
  return request({
    url: '/billiard/store/' + storeId,
    method: 'get'
  })
}

// 新增门店
export function addStore(data) {
  return request({
    url: '/billiard/store',
    method: 'post',
    data: data
  })
}

// 修改门店
export function updateStore(data) {
  return request({
    url: '/billiard/store',
    method: 'put',
    data: data
  })
}

// 删除门店
export function delStore(storeId) {
  return request({
    url: '/billiard/store/' + storeId,
    method: 'delete'
  })
}
