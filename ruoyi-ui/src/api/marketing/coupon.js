import request from '@/utils/request'



// 查询优惠券模版列表
export function listTemplate(query) {
  return request({
    url: '/system/couponTemplate/list',
    method: 'get',
    params: query
  })
}

// 查询优惠券模版详细
export function getTemplate(id) {
  return request({
    url: '/system/couponTemplate/' + id,
    method: 'get'
  })
}

// 修改模版状态
export function switchTemplateStatus(id) {
  return request({
    url: '/system/couponTemplate/switchTemplateStatus/' + id,
    method: 'get'
  })
}

// 新增优惠券模版
export function addTemplate(data) {
  return request({
    url: '/system/couponTemplate',
    method: 'post',
    data: data
  })
}

// 修改优惠券模版
export function updateTemplate(data) {
  return request({
    url: '/system/couponTemplate',
    method: 'put',
    data: data
  })
}

// 删除优惠券模版
export function delTemplate(id) {
  return request({
    url: '/system/couponTemplate/' + id,
    method: 'delete'
  })
}



// 查询用户-优惠券发行列表
export function listCoupon(query) {
  return request({
    url: '/system/coupon/list',
    method: 'get',
    params: query
  })
}

// 查询用户-优惠券发行详细
export function getCoupon(couponNo) {
  return request({
    url: '/system/coupon/' + couponNo,
    method: 'get'
  })
}

// 新增用户-优惠券发行
export function addCoupon(data) {
  return request({
    url: '/system/coupon',
    method: 'post',
    data: data
  })
}

// 修改用户-优惠券发行
export function updateCoupon(data) {
  return request({
    url: '/system/coupon',
    method: 'put',
    data: data
  })
}

// 删除用户-优惠券发行
export function delCoupon(couponNo) {
  return request({
    url: '/system/coupon/' + couponNo,
    method: 'delete'
  })
}
