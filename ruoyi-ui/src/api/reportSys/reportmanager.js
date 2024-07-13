import request from '@/utils/request'

// 查询历史检测报告列表
export function listreportmanager(query) {
  return request({
    url: '/reportSys/reportmanager/list',
    method: 'get',
    params: query
  })
}

// 查询历史检测报告详细
export function getreportmanager(hId) {
  return request({
    url: '/reportSys/reportmanager/' + hId,
    method: 'get'
  })
}

// 新增历史检测报告
export function addreportmanager(data) {
  return request({
    url: '/reportSys/reportmanager',
    method: 'post',
    data: data
  })
}

// 修改历史检测报告
export function updatereportmanager(data) {
  return request({
    url: '/reportSys/reportmanager',
    method: 'put',
    data: data
  })
}

// 删除历史检测报告
export function delreportmanager(hId) {
  return request({
    url: '/reportSys/reportmanager/' + hId,
    method: 'delete'
  })
}
