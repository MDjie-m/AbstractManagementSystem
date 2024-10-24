import request from "@/utils/request";

export function listMembers(params) {
  return request({
    url: `/cashier/member/list`,
    method: 'get',
    params:params
  })
}
export function updatePayPwd(params) {
  return request({
    url: `/cashier/member/pwd`,
    method: 'post',
    data:params
  })
}
export function listMemberDetail(memberId) {
  return request({
    url: `/cashier/member/${memberId}`,
    method: 'get',
    params: {}
  })
}
export function listMemberRecharges(params) {
  return request({
    url: `/cashier/member/${params.memberId}/recharge/list`,
    method: 'get',
    params:params
  })
}
export function listMemberDeduct(params) {
  return request({
    url: `/cashier/member/${params.memberId}/deduct/list`,
    method: 'get',
    params:params
  })
}
export function getPreRecharge(memberId,recharge) {
  return request({
    url: `/cashier/member/${memberId}/recharge`,
    method: 'get',
    params: {recharge:recharge}
  })
}
export function postRecharge(params) {
  return request({
    url: `/cashier/member/recharge`,
    method: 'post',
    data:params
  })
}
export function getAllMemberLevel(){
  return request({
    url: `/cashier/member/level/list`,
    method: 'get',
  })

}
export function registerMember(params) {
  return request({
    url: `/cashier/member/register`,
    method: 'post',
    data:params
  })
}
export function updateMember(params) {
  return request({
    url: `/cashier/member/edit`,
    method: 'post',
    data:params
  })
}
