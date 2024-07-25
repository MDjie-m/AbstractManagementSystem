import request from '@/utils/request'

// 查询风机管理列表
export function listWind(query) {
  return request({
    url: '/windSys/wind/list',
    method: 'get',
    params: query
  })
}

// 查询风机管理详细
export function getReportManager(wIds) {
  //axios.post('http://localhost:81/reportSys/reportmanager/queryBlade', wIds)
  return request({
    url: '/reportSys/reportmanager/queryBlade/'+wIds,
    method: 'get'

  })
}

// 新增风机管理
export function addWind(data) {
  return request({
    url: '/windSys/wind',
    method: 'post',
    data: data
  })
}

// 修改风机管理
export function updateWind(data) {
  return request({
    url: '/windSys/wind',
    method: 'put',
    data: data
  })
}
// 检测报告
export function selectReport(wId) {
  return request({
    url: '/reportSys/reportmanager/' + wId,
    method: 'get'
  })
}

// 删除风机管理
export function delWind(wId) {
  return request({
    url: '/windSys/wind/' + wId,
    method: 'delete'
  })
}
