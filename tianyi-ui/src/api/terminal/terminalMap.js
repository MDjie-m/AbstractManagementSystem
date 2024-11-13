import request from '@/utils/request'

// 1、基站终端统计信息查询
export function queryBSStatInfo(query) {
    return request({
      url: '/terminal/queryBSStatInfo',
      method: 'get',
      params: query
    })
}

// 查询IMEI的基本信息
export function queryByImeiInfo(query) {
  return request({
    url: '/terminal/queryByImeiInfo',
    method: 'get',
    params: query
  })
}
// IMEI轨迹查询接口
export function trackIMEI(query) {
  return request({
    url: '/terminal/trackIMEI',
    method: 'get',
    params: query
  })
}

// 查询基站终端基本信息
export function queryBSTerminalInfo(query) {
  return request({
    url: '/terminal/queryBSTerminalInfo',
    method: 'get',
    params: query
  })
}

// 查询基站上终端信息
export function queryImeiInfo(query) {
  return request({
    url: '/terminal/queryImeiInfo',
    method: 'get',
    params: query
  })
}
