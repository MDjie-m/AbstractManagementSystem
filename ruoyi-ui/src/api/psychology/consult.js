import request from '@/utils/request'

// 查询咨询师入驻申请详细
export function getAvailableUserName(name) {
  return request({
    url: '/psychology/consult/getAvailableUserName/' + name,
    method: 'get'
  })
}

// 获取配置
export function getAttrs(dictTypes) {
  return request({
    url: '/psychology/consult/getAttrs/' + dictTypes,
    method: 'get'
  })
}

export function getConsultAll(query) {
  return request({
    url: '/psychology/consult/getAll',
    method: 'get',
    params: query
  })
}

// 查询咨询类型列表
export function listConsult(query) {
  return request({
    url: '/psychology/consult/list',
    method: 'get',
    params: query
  })
}

export function adminListConsult(query) {
  return request({
    url: '/psychology/consult/adminList',
    method: 'get',
    params: query
  })
}

export function accountListConsult(query) {
  return request({
    url: '/finance/account/List',
    method: 'get',
    params: query
  })
}

// 查询咨询类型详细
export function getConsult(id) {
  return request({
    url: '/psychology/consult/' + id,
    method: 'get'
  })
}

// 新增咨询服务配置
export function refConsultServe(data) {
  return request({
    url: '/psychology/consult/refConsultServe',
    method: 'post',
    data: data
  })
}

// 新增咨询类型
export function addConsult(data) {
  return request({
    url: '/psychology/consult',
    method: 'post',
    data: data
  })
}

// 修改咨询类型
export function updateConsult(data) {
  return request({
    url: '/psychology/consult',
    method: 'put',
    data: data
  })
}

// 删除咨询类型
export function delConsult(id) {
  return request({
    url: '/psychology/consult/' + id,
    method: 'delete'
  })
}

// 初始化服务关联关系
export function addAllRelation() {
  return request({
    url: '/psychology/consult/addAllRelation',
    method: 'post',
    data: {}
  })
}
