import request from '@/utils/request'

// 查询页面广告列表
export function listAdvert(query) {
  return request({
    url: '/system/advert/list',
    method: 'post',
    params: query ,
    data: query
  })
}

// 查询页面广告详细
export function getAdvert(advertNo) {
  return request({
    url: '/system/advert/queryByAdvertNo' ,
    method: 'post',
    params: {"advertNo":advertNo} ,
    data: {"advertNo":advertNo}
  })
}

// 新增页面广告
export function addAdvert(data) {
  return request({
    url: '/system/advert/add',
    method: 'post',
    data: data
  })
}

// 修改页面广告
export function updateAdvert(data) {
  return request({
    url: '/system/advert/edit',
    method: 'post',
    data: data
  })
}

// 删除页面广告
export function delAdvert(advertNo) {
  return request({
    url: '/system/advert/delete',
    method: 'post',
    data: {"advertNo":advertNo}
  })
}




// 查询广告条目列表
export function listItem(query) {
  return request({
    url: '/system/advertItem/list',
    method: 'get',
    params: query
  })
}

// 查询广告条目详细
export function getItem(id) {
  return request({
    url: '/system/advertItem/' + id,
    method: 'get'
  })
}

// 新增广告条目
export function addItem(data) {
  return request({
    url: '/system/advertItem/add',
    method: 'post',
    data: data
  })
}

// 修改广告条目
export function updateItem(data) {
  return request({
    url: '/system/advertItem/edit',
    method: 'post',
    data: data
  })
}

// 删除广告条目
export function delItem(id) {
  return request({
    url: '/system/advertItem/' + id,
    method: 'delete'
  })
}
