import httprequest from "../httpRequest";
export default {
  //获取测评详情
  getProductInfo: async (id) => {
    let res = await httprequest.post("/consulted/home/gauge/getInfo?id=" + id);
    if (res.code == 200) {
      return res.data;
    } else {
      uni.showToast({
        icon: "error",
        title: "获取详情出错",
      });
    }
  },
  orderPay: async (gaugeId, amount) => {
    let res = await httprequest.post("/consulted/wxPay/pay", { amount, gaugeId });
    if (res.code == 200) {
      return res;
    } else {
      uni.showToast({
        icon: "error",
        title: "提交订单出错",
      });
      return res.code;
    }
  },
};
