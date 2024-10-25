import request from '@/utils/request'

// 查询课程相关文件列表
export function listFile(query) {
  return request({
    url: '/workflow/file/list',
    method: 'get',
    params: query
  })
}

// 查询课程相关文件详细
export function getFile(fileId) {
  return request({
    url: '/yk/file/' + fileId,
    method: 'get'
  })
}

// 新增课程相关文件
export function addFile(data) {
  return request({
    url: '/yk/file',
    method: 'post',
    data: data
  })
}

// 修改课程相关文件
export function updateFile(data) {
  return request({
    url: '/yk/file',
    method: 'put',
    data: data
  })
}

// 删除课程相关文件
export function delFile(fileId) {
  return request({
    url: '/yk/file/' + fileId,
    method: 'delete'
  })
}
