import request from '@/utils/request'

// 查询球桌列表
export function listDesk(params) {
  return request({
    url: '/cashier/desk/list',
    method: 'get',
    params: params
  })
}
export function listDeskDashboard() {
  return request({
    url: '/cashier/desk/dashboard',
    method: 'get',
  })
}

// 查询球桌详细
export function getDesk(deskId) {
  return request({
    url: '/billiard/desk/' + deskId,
    method: 'get'
  })
}

