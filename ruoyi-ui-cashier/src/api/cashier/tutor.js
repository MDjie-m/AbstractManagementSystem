import request from "@/utils/request";

export function listAllTutor(params) {
  return request({
    url: '/cashier/tutor/list',
    method: 'get',
    params: params
  })
}

export function getTutorBookingMap(params) {
  return request({
    url: `/cashier/tutor/booking/map`,
    method: 'get',
    params:params
  })
}
export function getTutorBookingList(params) {
  return request({
    url: `/cashier/tutor/booking/list`,
    method: 'get',
    params:params
  })
}
export function addTutorBooking(params) {
  return request({
    url: `/cashier/tutor/booking`,
    method: 'post',
    data:params
  })
}
export function delTutorBooking(bookingId) {
  return request({
    url: `/cashier/tutor/booking/${bookingId}`,
    method: 'delete',
  })
}
export function verifyTutorBooking(bookingId) {
  return request({
    url: `/cashier/tutor/booking/${bookingId}/verify`,
    method: 'post',
  })
}
