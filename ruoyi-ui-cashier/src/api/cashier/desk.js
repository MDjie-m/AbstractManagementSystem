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
export function listDeskTypeAll() {
  return request({
    url: '/cashier/desk/deskType/list',
    method: 'get',
  })
}
export function listPlaceTypeAll() {
  return request({
    url: '/cashier/desk/placeType/list',
    method: 'get',
  })
}
export function saveLineUp(params) {
  return request({
    url: '/cashier/desk/line-up',
    method: 'post',
    data: params
  })
}
export function deskAddScore(params) {
  return request({
    url: '/cashier/desk/score',
    method: 'post',
    data: params
  })
}
export function deskCapture(params) {
  return request({
    url: '/cashier/desk/capture',
    method: 'post',
    data: params
  })
}
export function listDeskDashboard() {
  return request({
    url: '/cashier/desk/dashboard',
    method: 'get',
  })
}
export function queryLightStatus() {
  return request({
    url: '/cashier/store/light/status',
    method: 'get',
  })
}
export function listLightTimer(time) {
  return request({
    headers: {noTip: true},
    url: '/cashier/desk/light-timer/list',
    method: 'get',
    params: {time}
  })
}
export function removeTimerById(timerId) {
  return request({
    headers: {noTip: true},
    url: `/cashier/desk/light-timer/${timerId}`,
    method: 'delete',
  })
}

export function createLightTimer(data) {
  return request({
    url: '/cashier/desk/light-timer',
    method: 'post',
    data: data
  })
}

export function removeLightTimer(time) {
  return request({
    url: '/cashier/desk/light-timer/remove',
    method: 'post',
    params: {time}
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

export function swapToNewDesk(deskId, newDeskId, orderId) {
  return request({
    url: `/cashier/desk/${deskId}/swap`,
    method: 'post',
    params: {newDeskId, orderId}
  })
}

export function mergeToNewDesk(deskId, newDeskId, orderId) {
  return request({
    url: `/cashier/desk/${deskId}/merge`,
    method: 'post',
    params: {newDeskId, orderId}
  })
}
export function getBookingMap(params) {
  return request({
    url: `/cashier/desk/booking/map`,
    method: 'get',
    params:params
  })
}
export function getDeskBookingList(params) {
  return request({
    url: `/cashier/desk/booking/list`,
    method: 'get',
    params:params
  })
}
export function addDeskBooking(params) {
  return request({
    url: `/cashier/desk/booking`,
    method: 'post',
    data:params
  })
}
export function delDeskBooking(bookingId) {
  return request({
    url: `/cashier/desk/booking/${bookingId}`,
    method: 'delete',
  })
}
export function verifyDeskBooking(bookingId) {
  return request({
    url: `/cashier/desk/booking/${bookingId}/verify`,
    method: 'post',
  })
}
