import request from '@/utils/request'

// 查询教练价格列表
export function listTutorPrice(query) {
  return request({
    url: '/billiard/tutorPrice/list',
    method: 'get',
    params: query
  })
}

// 查询教练价格详细
export function getTutorPrice(tutorPriceId) {
  return request({
    url: '/billiard/tutorPrice/' + tutorPriceId,
    method: 'get'
  })
}

// 新增教练价格
export function addTutorPrice(data) {
  return request({
    url: '/billiard/tutorPrice',
    method: 'post',
    data: data
  })
}

// 修改教练价格
export function updateTutorPrice(data) {
  return request({
    url: '/billiard/tutorPrice',
    method: 'put',
    data: data
  })
}

// 删除教练价格
export function delTutorPrice(tutorPriceId) {
  return request({
    url: '/billiard/tutorPrice/' + tutorPriceId,
    method: 'delete'
  })
}
