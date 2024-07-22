import request from '@/utils/request'

// 查询供应商报价列表
export function listPrice(query) {
  return request({
    url: '/system/price/list',
    method: 'get',
    params: query
  })
}

// 查询供应商报价详细
export function getPrice(supplierPriceId) {
  return request({
    url: '/system/price/' + supplierPriceId,
    method: 'get'
  })
}

// 新增供应商报价
export function addPrice(data) {
  return request({
    url: '/system/price',
    method: 'post',
    data: data
  })
}

// 修改供应商报价
export function updatePrice(data) {
  return request({
    url: '/system/price',
    method: 'put',
    data: data
  })
}

// 删除供应商报价
export function delPrice(supplierPriceId) {
  return request({
    url: '/system/price/' + supplierPriceId,
    method: 'delete'
  })
}
