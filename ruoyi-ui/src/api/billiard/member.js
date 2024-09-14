import request from '@/utils/request'

// 查询门店会员列表
export function listMember(query) {
  return request({
    url: '/billiard/member/list',
    method: 'get',
    params: query
  })
}

// 查询门店会员详细
export function getMember(memberId) {
  return request({
    url: '/billiard/member/' + memberId,
    method: 'get'
  })
}

// 新增门店会员
export function addMember(data) {
  return request({
    url: '/billiard/member',
    method: 'post',
    data: data
  })
}

// 修改门店会员
export function updateMember(data) {
  return request({
    url: '/billiard/member',
    method: 'put',
    data: data
  })
}

// 删除门店会员
export function delMember(memberId) {
  return request({
    url: '/billiard/member/' + memberId,
    method: 'delete'
  })
}
