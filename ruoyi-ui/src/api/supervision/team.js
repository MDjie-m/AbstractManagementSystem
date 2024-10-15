import request from '@/utils/request'


// 根据条件查询督导列表
export function queryTeamList(query) {
  return request({
    url: '/system/supervision-team/list',
    method: 'post',
    params: query,
    data: query
  })
}

//新增督导
export function addTeam(data) {
  return request({
    url: '/system/supervision-team/add',
    method: 'post',
    data: data
  })
}

//修改督导
export function editTeam(data) {
  return request({
    url: '/system/supervision-team/edit',
    method: 'put',
    data: data
  })
}

//查询督导详情
export function infoTeam(id) {
  return request({
    url: '/system/supervision-team/queryById/'+id,
    method: 'get'
  })
}

//修改督导
export function deleteTeam(id) {
  return request({
    url: '/system/supervision-team/deleteByIds/'+id,
    method: 'delete'
  })
}

//查询督导的排程任务清单
export function teamScheduleList(data) {
  return request({
    url: '/system/schedule/list',
    method: 'post',
    data: data
  })
}

//查询督导的排程任务清单
export function confirmSchedule(id) {
  return request({
    url: '/system/schedule/confirm',
    method: 'post',
    data: {"id":id}
  })
}



// 查询课程订单详细
export function getOrder(id) {
  return request({
    url: '/course/order/' + id,
    method: 'get'
  })
}

// 新增课程订单
export function addOrder(data) {
  return request({
    url: '/course/order',
    method: 'post',
    data: data
  })
}

// 修改课程订单
export function updateOrder(data) {
  return request({
    url: '/course/order',
    method: 'put',
    data: data
  })
}

// 删除课程订单
export function delOrder(id) {
  return request({
    url: '/course/order/' + id,
    method: 'delete'
  })
}

