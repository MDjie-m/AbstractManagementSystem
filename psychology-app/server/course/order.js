import httprequest from "../httpRequest";
export default {
  //根据课程信息生成订单
  orderAdd: async (userId, courseId, amount) => {
    let res = await httprequest.put("/consulted/course/order/add", {
      userId,
      courseId,
      amount,
    });
    if (res.code == 200) {
      return res;
    } else {
      uni.showToast({
        icon: "error",
        title: "生成订单出错！",
      });
    }
  },
  //根据用户ID获取订单
  getOrderList: async (userId) => {
    let res = await httprequest.post("/consulted/course/order/list", {
        userId,
    });
    if (res.code == 200) {
      return res.data;
    } else {
      return [];
    }
  },
  // 根据用户ID和订单状态获取订单
  getOrderListByStatus: async (userId, status) => {
    let res = await httprequest.post("/consulted/course/order/list", {
        userId,
		status
    });
    if (res.code == 200) {
      return res.data;
    } else {
      return [];
    }
  },
  //根据订单获取订单详情
  getOrderDetail: async (orderId) => {
    let res = await httprequest.post(`/consulted/course/order/detail?orderId=${orderId}`);
    if (res.code == 200) {
      return res.data;
    } else {
	  uni.showToast({
	    icon: "error",
	    title: "获取订单详情出错",
	  });
      return [];
    }
  },
  cancelOrder:async (orderId)=>{
    let res = await httprequest.post(`/consulted/course/order/cancel?orderId=${orderId}`);
    if (res.code == 200) {
      return res.data;
    } else {
      uni.showToast({
        icon: "error",
        title: "取消订单出错",
      });
    }
  }
};
