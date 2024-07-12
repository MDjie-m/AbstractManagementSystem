import httprequest from "../httpRequest";
export default {
    getIntegral: async (data) => {
        let res = await httprequest.get(`/consulted/user/integral/list`, data);
        if (res.code == 200) {
            return res;
        } else {
            return [];
        }
    },

    getUserIntegral: async () => {
        let res = await httprequest.get(`/consulted/user/integral/getUserIntegral`);
        if (res.code == 200) {
            return res;
        } else {
            return [];
        }
    },

    getLikes: async (data) => {
        return await httprequest.get(`/consulted/user/liked/consult/getLikes`, data);
    },
    getLiked: async (data) => {
        let res = await httprequest.post(`/consulted/user/liked/consult/getLiked`, data);
        if (res.code == 200) {
            return res.data;
        }
    },
    doLike: async (data) => {
        let res = await httprequest.post(`/consulted/user/liked/consult`, data);
        if (res.code == 200) {
            return res.data;
        } else {
            uni.showToast({
                icon: "error",
                title: "关注失败",
            });
        }
    },
    cancelLike: async (data) => {
        let res = await httprequest.post(`/consulted/user/liked/consult/del`, data);
        if (res.code == 200) {
            return res.data;
        } else {
            uni.showToast({
                icon: "error",
                title: "取关失败",
            });
        }
    }
 
};