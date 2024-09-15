import request from '@/utils/request'
import data from "@/views/system/dict/data.vue";

// 查询球桌列表
export function listDesk(params) {
  return request({
    url: '/cashier/desk/list',
    method: 'get',
    params: params
  })
}
export function listLineUp() {
  return request({
    url: '/cashier/desk/line-up',
    method: 'get',
  })
}
export function saveLineUp(params) {
  return request({
    url: '/cashier/desk/line-up',
    method: 'post',
    data:params
  })
}
export function listDeskDashboard() {
  return request({
    url: '/cashier/desk/dashboard',
    method: 'get',
  })
}
export function getDeskBaseInfo(deskId) {
  return request({
    url: `/cashier/desk/${deskId}`,
    method: 'get',
  })
}
export function startCalcFee(deskId) {
  return request({
    url: `/cashier/desk/${deskId}/start`,
    method: 'post',
  })
}
export function pauseCalcFee(deskId) {
  return request({
    url: `/cashier/desk/${deskId}/pause`,
    method: 'post',
  })
}
export function resumeCalcFee(deskId) {
  return request({
    url: `/cashier/desk/${deskId}/resume`,
    method: 'post',
  })
}
export function swapToNewDesk(deskId,newDeskId,orderId) {
  return request({
    url: `/cashier/desk/${deskId}/swap`,
    method: 'post',
    params:{newDeskId,orderId}
  })
}
