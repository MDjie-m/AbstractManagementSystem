import request from '@/utils/request'

// 查询账户明细流水列表
export function listRecord(query) {
  return request({
    url: '/finance/record/list',
    method: 'get',
    params: query
  })
}

// 查询账户明细流水详细
export function getRecord(recordId) {
  return request({
    url: '/finance/record/' + recordId,
    method: 'get'
  })
}

// 新增账户明细流水
export function addRecord(data) {
  return request({
    url: '/finance/record',
    method: 'post',
    data: data
  })
}

// 修改账户明细流水
export function updateRecord(data) {
  return request({
    url: '/finance/record',
    method: 'put',
    data: data
  })
}

// 删除账户明细流水
export function delRecord(recordId) {
  return request({
    url: '/finance/record/' + recordId,
    method: 'delete'
  })
}

// 标记提现申请完成
export function drawFinish(data) {
  return request({
    url: '/finance/record/drawFinish',
    method: 'post',
    data: data
  })
}

