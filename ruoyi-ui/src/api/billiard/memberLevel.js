import request from '@/utils/request'

// 查询门店会员等级列表
export function listMemberLevel(query) {
  return request({
    url: '/billiard/memberLevel/list',
    method: 'get',
    params: query
  })
}

// 查询门店会员等级详细
export function getMemberLevel(memberLevelId) {
  return request({
    url: '/billiard/memberLevel/' + memberLevelId,
    method: 'get'
  })
}

// 新增门店会员等级
export function addMemberLevel(data) {
  return request({
    url: '/billiard/memberLevel',
    method: 'post',
    data: data
  })
}

// 修改门店会员等级
export function updateMemberLevel(data) {
  return request({
    url: '/billiard/memberLevel',
    method: 'put',
    data: data
  })
}

// 删除门店会员等级
export function delMemberLevel(memberLevelId) {
  return request({
    url: '/billiard/memberLevel/' + memberLevelId,
    method: 'delete'
  })
}

// 查询会员等级是否被使用
export function checkMemberLevel(memberLevelId) {
  return request({
    url: '/billiard/memberLevel/isUsed/' + memberLevelId,
    method: 'get'
  })
}
