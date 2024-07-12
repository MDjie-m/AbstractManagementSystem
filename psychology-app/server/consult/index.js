import httprequest from "../httpRequest";
export default {
  //根据字典类型获取banner图
  getBannerList: async (type) => {
    let res = await httprequest.get("/consulted/consult/banner/list", {
      type,
    });
    if (res.code == 200) {
      return res.rows;
    } else {
      return [];
    }
  },
  //获取咨询师
  getConsult: async (data, pageNum, pageSize) => {
    let res = await httprequest.post(`/consulted/consult/search?pageNum=${pageNum}&pageSize=${pageSize}`, data);
    return res
  },
  //获取配置
  getConfigByType: async (dictType) => {
    let res = await httprequest.get("/consulted/consult/config/getConfigByType/" + dictType);
    if (res.code == 200) {
      return res.data;
    } else {
      return [];
    }
  },
  getTypes: async () => {
    let res = await httprequest.get("/consulted/consult/config/getTrees");
    if (res.code == 200) {
      return res.data;
    } else {
      return [];
    }
  },
  getAttrs: async (dictTypes) => {
    let res = await httprequest.get("/consulted/consult/config/getConfigByTypes/" + dictTypes);
    if (res.code == 200) {
      return res.data;
    } else {
      return [];
    }
  },
  getDates: async (num) => {
    let res = await httprequest.get("/consulted/consult/config/getDateNum/" + num);
    if (res.code == 200) {
      return res.data;
    } else {
      return [];
    }
  },
  getNotices: async () => {
    let res = await httprequest.get("/consulted/consult/config/getNotices");
    if (res.code == 200) {
      return res.data;
    } else {
      return [];
    }
  },
};
