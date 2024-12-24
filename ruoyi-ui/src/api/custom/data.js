import request from "@/utils/request";

export function getColumns(tableId) {
    return request({
        url: '/custom/data/'+tableId+'/columns',
        method: 'get'
    })
}

export function getTableDataList(tableId,query) {
    return request({
        url: '/custom/data/'+tableId+'/list',
        method: 'get',
        params: query
    })
}

export function getTableDataById(tableId,id) {
    return request({
        url: '/custom/data/'+tableId+'/findById/'+id,
        method: 'get'
    })
}


export function addTableData(tableId,data) {
    return request({
        url: '/custom/data/'+tableId+'/add',
        method: 'post',
        data: data
    })
}


export function updateTableData(tableId,data) {
    return request({
        url: '/custom/data/'+tableId+'/update',
        method: 'put',
        data: data
    })
}


export function delTableData(tableId,ids) {
    return request({
        url: '/custom/data/'+tableId+'/delete/'+ids,
        method: 'delete'
    })
}

