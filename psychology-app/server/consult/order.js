import httprequest from "../httpRequest";
export default {
  getNotice: async (id) => {
    let res = await httprequest.get(`/consulted/system/notice/` + id);
    if (res.code == 200) {
      return res.data;
    } else {
      return [];
    }
  },
  //获取订单列表
  getOrderList: async (data) => {
    let res = await httprequest.post(`/consulted/consult/order/getOrderList`, data);
    if (res.code == 200) {
      return res.data;
    } else {
      return [];
    }
  },
  getOrderDetail: async (id) => {
    let res = await httprequest.get(`/consulted/consult/order/getOrderDetail/` + id);
    if (res.code == 200) {
      return res.data;
    } else {
      return [];
    }
  },
  getOrderDetailByNo: async (id) => {
    let res = await httprequest.get(`/consulted/consult/order/getOrderDetailByNo/` + id);
    if (res.code == 200) {
      return res.data;
    } else {
      return [];
    }
  },
  getOrderInfo: async (id) => {
    let res = await httprequest.get(`/consulted/consult/order/getOrderInfo/` + id);
    if (res.code == 200) {
      return res.data;
    } else {
      return [];
    }
  },
  getConsultInfoByServe: async (cId, sId) => {
    let res = await httprequest.get(`/consulted/consult/getConsultInfoByServe/` + cId + '/' + sId);
    if (res.code == 200) {
      return res.data;
    } else {
      uni.showToast({
        icon: "error",
        title: "获取订单详情出错",
      });
    }
  },
  doConsult: async (id, workId, time) => {
    let res = await httprequest.post(`/consulted/consult/order/doConsult/` + id + '/' + workId + '/' + time);
    if (res.code == 200 && res.data !== -1) {
      return 1;
    } else {
      uni.showToast({
        icon: "error",
        title: "操作失败",
      });
    }
  },
  cancel: async (id) => {
    let res = await httprequest.get(`/consulted/consult/order/cancel/` + id);
    if (res.code == 200) {
      return 1;
    } else {
      uni.showToast({
        icon: "error",
        title: "取消失败",
      });
    }
  }
};
