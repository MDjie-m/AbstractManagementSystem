import httprequest from "../httpRequest";
export default {
	//根据搜素条件查询课程列表
	getCourseList: async (searchValue) => {
	  let res = await httprequest.post(`/consulted/course/search?searchValue=${searchValue}`);
	  if (res.code == 200) {
	    return res.data;
	  } else {
	    return [];
	  }
	},
  //获取与用户无法的课程基本信息
  getCourseBaseInfo: async (courseId) => {
    let res = await httprequest.post(`/consulted/course/getInfo?id=${courseId}`);
    if (res.code == 200) {
      return res.data;
    } else {
      uni.showToast({
        icon: "error",
        title: "获取课程基本信出错",
      });
    }
  },
    //获取课程详情
    getCourseInfo: async (userId, courseId) => {
      let res = await httprequest.post(`/consulted/course/detail/${userId}/${courseId}`);
      if (res.code == 200) {
        return res.data;
      } else {
        uni.showToast({
          icon: "error",
          title: "获取课程详情出错",
        });
      }
    },
    getSectionList: async (courseId) => {
      let res = await httprequest.post("/consulted/course/section/list",{courseId});
      if (res.code == 200) {
        console.log(res);
        return res.rows;
      } else {
        uni.showToast({
          icon: "error",
          title: "获取章节列表出错",
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
	
	// 记录用户学习课程章节的时间点
	recordEndTime: async (data) => {
      let res = await httprequest.post("/consulted/course/userSection/saveUserSectionInfo", data);
      if (res.code == 200) {
        return res.data;
      } else {
        uni.showToast({
          icon: "error",
          title: "记录用户学习课程章节出错",
        });
      }
    },
};
