import request from '@/utils/request'

// 查询门店助教列表
export function listTutor(query) {
  return request({
    url: '/billiard/tutor/list',
    method: 'get',
    params: query
  })
}

// 查询门店助教详细
export function getTutor(storeTutorId) {
  return request({
    url: '/billiard/tutor/' + storeTutorId,
    method: 'get'
  })
}

// 新增门店助教
export function addTutor(data) {
  return request({
    url: '/billiard/tutor',
    method: 'post',
    data: data
  })
}

// 修改门店助教
export function updateTutor(data) {
  return request({
    url: '/billiard/tutor',
    method: 'put',
    data: data
  })
}

// 删除门店助教
export function delTutor(storeTutorId) {
  return request({
    url: '/billiard/tutor/' + storeTutorId,
    method: 'delete'
  })
}
