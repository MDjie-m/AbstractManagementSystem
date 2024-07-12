import httprequest from "../httpRequest";
export default {
  //获取测评问题
  getQuestionList: async (gaugeId, orderId) => {
    let res = await httprequest.get("/consulted/gauge/questions/list", {
      gaugeId,
      orderId,
    });
    if (res.code == 200) {
      return res.data;
    } else {
      uni.showToast({
        icon: "error",
        title: "获取题目出错",
      });
    }
  },
  //获取测评问题
  getWrongList: async (gaugeId, orderId) => {
    let res = await httprequest.get(`/consulted/gauge/questions/wrongs/${orderId}/${gaugeId}`);
    if (res.code == 200) {
      return res.data;
    } else {
      uni.showToast({
        icon: "error",
        title: "获取题目出错",
      });
    }
  },
  //获取测评问题
  getLats: async (gaugeId) => {
    let res = await httprequest.get("/consulted/gauge/questions/lats", {
      gaugeId
    });
    if (res.code == 200) {
      return res.data;
    } else {
      uni.showToast({
        icon: "error",
        title: "获取题目出错",
      });
    }
  },
  //提交答案
  setAnswer: async (data) => {
    let res = await httprequest.post("/consulted/gauge/result", data);
    if (res.code == 200) {
      return res;
    } else {
      uni.showToast({
        icon: "error",
        title: "保存题目出错",
      });
    }
  },
  setResult: async (data) => {
    let res = await httprequest.post("/consulted/gauge/result/commit", data);
    if (res.code == 200) {
      return res;
    } else {
      uni.showToast({
        icon: "error",
        title: "保存题目出错",
      });
    }
  },
  //获取测评报告
  getReport: async (orderId) => {
    let res = await httprequest.get("/consulted/gauge/result/getReport/" + orderId);
    if (res.code == 200) {
      return res.data;
    } else {
      uni.showToast({
        icon: "error",
        title: "获取报告出错",
      });
    }
  },
  getOrder: async (orderId) => {
    let res = await httprequest.get("/consulted/gauge/order/" + orderId);
    if (res.code == 200) {
      return res.data;
    } else {
      uni.showToast({
        icon: "error",
        title: "获取报告出错",
      });
    }
  },
};
