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
	//根据搜素条件查询测评列表
	getGaugeList: async (searchValue) => {
	  let res = await httprequest.post(`/consulted/home/gauge/search?searchValue=${searchValue}`);
	  if (res.code == 200) {
	    return res.data;
	  } else {
	    return [];
	  }
	},
}