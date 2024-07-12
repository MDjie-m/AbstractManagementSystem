import httprequest from "../httpRequest";
export default {
  //根据字典类型获取banner图
  getClassList: async (bannerType) => {
    let res = await httprequest.get("/consulted/home/gauge/class/list");
    if (res.code == 200) {
      return res.rows;
    } else {
      return [];
    }
  },
  getProductByClassId: async (gaugeClass) => {
    let res = await httprequest.get("/consulted/home/gauge/list", { gaugeClass });
    if (res.code == 200) {
      return res.rows;
    } else {
      return [];
    }
  },
  getProductByTitle: async (title) => {
    let res = await httprequest.get("/consulted/home/gauge/list", { title });
    if (res.code == 200) {
      return res.rows;
    } else {
      return [];
    }
  },
};
