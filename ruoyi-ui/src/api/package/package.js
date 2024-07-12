import request from '@/utils/request'


// 根据条件查询督导列表
export function queryPackageList(query) {
  return request({
    url: '/system/package/list',
    method: 'post',
    params: query,
    data: query
  })
}

//新增套餐
export function addPackage(data) {
  return request({
    url: '/system/package/add',
    method: 'post',
    data: data
  })
}

//修改套餐
export function editPackage(data) {
  return request({
    url: '/system/package/edit',
    method: 'put',
    data: data
  })
}

//删除套餐
export function deletePackage(id) {
  return request({
    url: '/system/package/deleteByIds/' + id,
    method: 'delete'
  })
}

//查询督导详情
export function infoPackage(id) {
  return request({
    url: '/system/package/queryById/'+id,
    method: 'get'
  })
}

