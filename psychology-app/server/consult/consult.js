import httprequest from "../httpRequest";

export default {
    //获取课程详情
    getConsultInfo: async (consultId) => {
        let res = await httprequest.get(`/consulted/consult/` + consultId);
        if (res.code == 200) {
            return res.data;
        } else {
            uni.showToast({
                icon: "error",
                title: "获取咨询师详情出错",
            });
        }
    },
    getConsultWorksById: async (id) => {
        let res = await httprequest.get(`/consulted/consult/getConsultWorksById/` + id);
        if (res.code == 200) {
            return res.data;
        } else {
            uni.showToast({
                icon: "error",
                title: "获取咨询师信息出错",
            });
        }
    },
    getConsultCourseByName: async (name) => {
        let res = await httprequest.post("/consulted/course/list", {userName: name});
        if (res.code == 200) {
            return res.rows;
        } else {
            return [];
        }
    },
    getConsultServe: async (consultId) => {
        let res = await httprequest.get(`/consulted/consult/serve/` + consultId);
        if (res.code == 200) {
            return res.data;
        } else {
            uni.showToast({
                icon: "error",
                title: "获取咨询师服务出错",
            });
        }
    },
    getConsultColumn: async (cat, consultId, data) => {
        let res = await httprequest.get(`/consulted/consult/getConsultColumn/${cat}/${consultId}`, data);
        if (res.code == 200) {
            return res.rows;
        } else {
            return [];
        }
    }
};
