import request from "@/utils/request";

export function listAllTutor(params) {
  return request({
    url: '/cashier/tutor/list',
    method: 'get',
    params: params
  })
}
export function tutorPunchIn(params) {
  return request({
    url: `/cashier/tutor/${params.tutorId}/punch-in`,
    method: 'post',
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
export function addTutorPlan(params) {
  return request({
    url: `/cashier/tutor/work-plan`,
    method: 'post',
    data:params
  })
}
export function getTutorPlanMap(params) {
  return request({
    url: `/cashier/tutor/work-plan/map`,
    method: 'get',
    params:params
  })
}
export function delTutorPlan(id) {
  return request({
    url: `/cashier/tutor/work-plan/${id}`,
    method: 'delete',
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
