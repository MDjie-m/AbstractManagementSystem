import request from '@/utils/request'

// 查询产品分类列表
export function listProductType(query) {
  return request({
    url: '/system/productType/list',
    method: 'get',
    params: query
  })
}

// 查询产品分类详细
export function getProductType(productCode) {
  return request({
    url: '/system/productType/' + productCode,
    method: 'get'
  })
}

// 新增产品分类
export function addProductType(data) {
  return request({
    url: '/system/productType',
    method: 'post',
    data: data
  })
}

// 修改产品分类
export function updateProductType(data) {
  return request({
    url: '/system/productType',
    method: 'put',
    data: data
  })
}

// 删除产品分类
export function delProductType(productCode) {
  return request({
    url: '/system/productType/' + productCode,
    method: 'delete'
  })
}
