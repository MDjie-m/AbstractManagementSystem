import httprequest from "../httpRequest";
export default {
  //根据字典类型获取banner图
  getClassList: async (bannerType) => {
    let res = await httprequest.get("/consulted/consult/class/list?status=0");
    if (res.code == 200) {
      return res.rows;
    } else {
      return [];
    }
  }
};
