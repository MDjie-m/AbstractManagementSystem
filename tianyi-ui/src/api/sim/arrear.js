import request from '@/utils/request'

// 查询欠费停机预警列表
export function listArrear(query) {
  return request({
    url: '/sim/arrear/list',
    method: 'get',
    params: query
  })
}

// 查询欠费停机预警详细
export function getArrear(provId) {
  return request({
    url: '/sim/arrear/' + provId,
    method: 'get'
  })
}

// 新增欠费停机预警
export function addArrear(data) {
  return request({
    url: '/sim/arrear',
    method: 'post',
    data: data
  })
}

// 修改欠费停机预警
export function updateArrear(data) {
  return request({
    url: '/sim/arrear',
    method: 'put',
    data: data
  })
}

// 删除欠费停机预警
export function delArrear(provId) {
  return request({
    url: '/sim/arrear/' + provId,
    method: 'delete'
  })
}
