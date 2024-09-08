import request from '@/utils/request'

// 查询商品分类列表
export function listCategory(query) {
  return request({
    url: '/billiard/category/list',
    method: 'get',
    params: query
  })
}
export function listAllCategory(query) {
  return request({
    url: '/billiard/category/list/all',
    method: 'get',
    params: query
  })
}
// 查询商品分类详细
export function getCategory(goodsCategoryId) {
  return request({
    url: '/billiard/category/' + goodsCategoryId,
    method: 'get'
  })
}

// 新增商品分类
export function addCategory(data) {
  return request({
    url: '/billiard/category',
    method: 'post',
    data: data
  })
}

// 修改商品分类
export function updateCategory(data) {
  return request({
    url: '/billiard/category',
    method: 'put',
    data: data
  })
}

// 删除商品分类
export function delCategory(goodsCategoryId) {
  return request({
    url: '/billiard/category/' + goodsCategoryId,
    method: 'delete'
  })
}
