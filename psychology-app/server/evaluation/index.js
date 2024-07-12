import httprequest from "../httpRequest";
export default {
  //根据字典类型获取banner图
  getBannerList: async (bannerType) => {
    let res = await httprequest.get("/consulted/home/banner/config/list", {
      bannerType,
    });
    if (res.code == 200) {
      return res.rows;
    } else {
      return [];
    }
  },
  //获取热门推荐/精选测评
  getProductByLabel: async (labelCode) => {
    let res = await httprequest.get("/consulted/home/gauge/label/list", {
      labelCode,
    });
    if (res.code == 200) {
      return res.rows;
    } else {
      return [];
    }
  },
};
