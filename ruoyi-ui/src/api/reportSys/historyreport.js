import request from '@/utils/request'

// 查询历史检测报告列表
export function listHistoryreport(query) {
  return request({
    url: '/reportSys/historyreport/list',
    method: 'get',
    params: query
  })
}

// 查询历史检测报告详细
export function getHistoryreport(hId) {
  return request({
    url: '/reportSys/historyreport/' + hId,
    method: 'get'
  })
}

// 新增历史检测报告
export function addHistoryreport(data) {
  return request({
    url: '/reportSys/historyreport',
    method: 'post',
    data: data
  })
}

// 修改历史检测报告
export function updateHistoryreport(data) {
  return request({
    url: '/reportSys/historyreport',
    method: 'put',
    data: data
  })
}

// 删除历史检测报告
export function delHistoryreport(hId) {
  return request({
    url: '/reportSys/historyreport/' + hId,
    method: 'delete'
  })
}
