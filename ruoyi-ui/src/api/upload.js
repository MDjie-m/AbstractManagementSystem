import request from '@/utils/request'

// 删除文件
export function deleteFile(fileName, module) {
  return request({
    url: `/common/upload/delete?fileKey=${fileName}&module=${module}`,
    method: 'post'
  })
}

// 上传图片
export function uploadFile(data, module, type) {
  return request({
    url: '/common/upload',
    method: 'post',
    headers: {
      'module': module,
      'type': type,
    },
    data: data
  })
}

// 获取cos临时密钥
export function getCredential(headData) {
  return request({
    url: '/common/getCredential',
    method: 'get',
    headers: headData
  })
}
