import request from '@/utils/request'

// 查询学校列表
export function listSchools(query) {
  return request({
    url: '/yk/schools/list',
    method: 'get',
    params: query
  })
}

// 查询学校详细
export function getSchools(id) {
  return request({
    url: '/yk/schools/' + id,
    method: 'get'
  })
}

// 新增学校
export function addSchools(data) {
  return request({
    url: '/yk/schools',
    method: 'post',
    data: data
  })
}

// 修改学校
export function updateSchools(data) {
  return request({
    url: '/yk/schools',
    method: 'put',
    data: data
  })
}

// 删除学校
export function delSchools(id) {
  return request({
    url: '/yk/schools/' + id,
    method: 'delete'
  })
}
