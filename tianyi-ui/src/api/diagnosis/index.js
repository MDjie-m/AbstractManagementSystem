import request from '@/utils/request'

// 业务状态查询
export function cardNetStopStateQuery(query) {
    return request({
      url: '/fault/cardNetStopStateQuery',
      method: 'get',
      params: query
    })
}