import request from '@/utils/request'

// 查询库存列表
export function listStock(query) {
  return request({
    url: '/billiard/stock/list',
    method: 'get',
    params: query
  })
}
export function listStoreGoods(storeId) {
  return request({
    url: `/billiard/stock/${storeId}/goods/list`,
    method: 'get',
  })
}
export function listStockLogList(query) {
  return request({
    url: `/billiard/stock/log/list`,
    method: 'get',
    params:query
  })
}
// 查询库存详细
export function getStock(stockId) {
  return request({
    url: '/billiard/stock/' + stockId,
    method: 'get'
  })
}

// 新增库存
export function addStock(data) {
  return request({
    url: '/billiard/stock',
    method: 'post',
    data: data
  })
}

// 修改库存
export function updateStock(data) {
  return request({
    url: '/billiard/stock',
    method: 'put',
    data: data
  })
}
export function changeStock(data) {
  return request({
    url: '/billiard/stock/change',
    method: 'post',
    data: data
  })
}
// 删除库存
export function delStock(stockId) {
  return request({
    url: '/billiard/stock/' + stockId,
    method: 'delete'
  })
}
