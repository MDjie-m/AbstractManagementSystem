import request from '@/utils/request'

// 查询球桌列表
export function listDesk(params) {
  return request({
    url: '/cashier/desk/list',
    method: 'get',
    params: params
  })
}

// 查询球桌详细
export function getDesk(deskId) {
  return request({
    url: '/billiard/desk/' + deskId,
    method: 'get'
  })
}

