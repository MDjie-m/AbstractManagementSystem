import httprequest from "./httpRequest";

export default {
	getQrcode: async (qrCodeParam) => {
    let res = await httprequest.post(
      "/consulted/gauge/qr/getBase64Code",
	  {
		qrCodeParam
	  }
    );
    if (res.code == 200) {
      return res;
    } else {
      uni.showToast({
        icon: "error",
        title: "获取二维码出错",
      });
    }
  },
}