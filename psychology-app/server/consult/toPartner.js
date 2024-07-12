import httprequest from "../httpRequest";

export default {
    getInfo: async () => {
        let res = await httprequest.post(`/consulted/consult/partner/getInfo`);
        if (res.code == 200) {
            return res;
        } else {
            return [];
        }
    },
    draft: async () => {
        let res = await httprequest.post(`/consulted/consult/partner/draft`);
        if (res.code == 200) {
            return res;
        } else {
            return [];
        }
    },
    save: async (data) => {
        let res = await httprequest.post(`/consulted/consult/partner/save`, data);
        if (res.code == 200) {
            return res;
        } else {
            return [];
        }
    },
    addItem: async (data) => {
        let res = await httprequest.post(`/consulted/consult/partner/addItem`, data);
        if (res.code == 200) {
            return res;
        } else {
            return [];
        }
    },
    editItem: async (data) => {
        let res = await httprequest.post(`/consulted/consult/partner/editItem`, data);
        if (res.code == 200) {
            return res;
        } else {
            return [];
        }
    },
    delItem: async (id) => {
        let res = await httprequest.post(`/consulted/consult/partner/delItem/` + id);
        if (res.code == 200) {
            return res;
        } else {
            return [];
        }
    },
};
